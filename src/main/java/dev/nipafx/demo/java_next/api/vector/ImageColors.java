package dev.nipafx.demo.java_next.api.vector;

import jdk.incubator.vector.ByteVector;
import jdk.incubator.vector.VectorMask;
import jdk.incubator.vector.VectorOperators;
import jdk.incubator.vector.VectorShuffle;
import jdk.incubator.vector.VectorSpecies;

import javax.imageio.ImageIO;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.io.IOException;
import java.nio.file.Path;
import java.util.function.UnaryOperator;

/**
 * After running this demo, compare the city images in `src/main/resources` with those
 * created in `target`.
 */
public class ImageColors {

	private static final VectorSpecies<Byte> RGB_SPECIES = ByteVector.SPECIES_PREFERRED;
	/**
	 * Some color manipulations that are implemented here are easier to understand
	 * if they compute full pixels, i.e. always three RGB bytes at a time. So we limit
	 * the steps taken for each vector to the largest multiple of three (e.g. 30 instead of 32).
	 */
	private static final int RGB_STEPS = RGB_SPECIES.length() - RGB_SPECIES.length() % 3;

	private static final VectorShuffle<Byte> COLOR_SHUFFLE = VectorShuffle.fromOp(RGB_SPECIES, ImageColors::rotateRgbValues);
	private static final VectorMask<Byte> PURPLE_SHIFT = VectorShuffle
			// Colors appear in image byte array in order BLUE, GREEN, RED. Only
			// BLUE and RED with indices 0, 3, 6, ... and 2, 5, 8, ... respectively
			// should be set, so indices 1, 4, 7, etc... should be unset in the mask.
			.fromOp(RGB_SPECIES, index -> index % 3 == 1 ? -1 : 1)
			.laneIsValid();

	public static void main(String[] args) throws IOException {
		logVectorInformation();
		convertAll(new Conversion("Inverting", "inverted-%s", ImageColors::invertColors));
		convertAll(new Conversion("Inverting (with vectors)", "inverted-%s-vector", ImageColors::invertColors_vectorized));
		convertAll(new Conversion("Rotating", "rotated-%s", ImageColors::rotateColors));
		convertAll(new Conversion("Rotating (with vectors)", "rotated-%s-vector", ImageColors::rotateColors_vectorized));
		convertAll(new Conversion("Purple-shifting", "shifted-%s", ImageColors::purpleShift));
		convertAll(new Conversion("Purple-shifting (with vectors)", "shifted-%s-vector", ImageColors::purpleShift_vectorized));
	}

	private static void logVectorInformation() {
		System.out.println("Number of lanes: " + RGB_SPECIES.length());
		System.out.println("Number of RGB values per loop: " + RGB_STEPS);
		System.out.println();
	}

	private static void convertAll(Conversion conversion) throws IOException {
		convert("hcmc", conversion);
		convert("hk", conversion);
		convert("tokyo", conversion);
		System.out.println();
	}

	private static void convert(String imageName, Conversion conversion) throws IOException {
		var imageUrl = ImageColors.class.getClassLoader().getResource(imageName + ".jpg");
		var image = ImageIO.read(imageUrl);
		byte[] imageData = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();

		System.out.printf("%s %s ...", conversion.logMessageVerb(), imageName);
		long startTime = System.currentTimeMillis();
		var newImageData = conversion.converter().apply(imageData);
		long runTime = System.currentTimeMillis() - startTime;
		System.out.printf(" %.3fms%n", (runTime / 1_000d));

		var newImageBuffer = new DataBufferByte(newImageData, newImageData.length);
		var newImageRaster = Raster.createRaster(image.getSampleModel(), newImageBuffer, new Point(0, 0));
		var newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		newImage.setData(newImageRaster);

		var newImageName = conversion.fileNameFormat().formatted(imageName) + ".jpg";
		var newImageFile = Path.of("target", newImageName).toAbsolutePath().toFile();
		ImageIO.write(newImage, "JPG", newImageFile);
	}

	interface ImageConverter extends UnaryOperator<byte[]> { }

	record Conversion(String logMessageVerb, String fileNameFormat, ImageConverter converter) { }

	/*
	 * INVERTING COLORS
	 */

	private static byte[] invertColors(byte[] image) {
		byte[] newImage = new byte[image.length];

		for (int pixel = 0; pixel + 2 < image.length; pixel += 3) {
			byte blue = image[pixel];
			byte green = image[pixel + 1];
			byte red = image[pixel + 2];

			byte newRed = (byte) -red;
			byte newGreen = (byte) -green;
			byte newBlue = (byte) -blue;

			newImage[pixel] = newBlue;
			newImage[pixel + 1] = newGreen;
			newImage[pixel + 2] = newRed;
		}

		return newImage;
	}

	private static byte[] invertColors_vectorized(byte[] image) {
		byte[] newImage = new byte[image.length];

		int loopBound = RGB_SPECIES.loopBound(image.length);
		int pixel = 0;
		// vectorized loop:
		// to invert colors, we can ignore how three color values form one pixel
		// and advance in steps of species length
		for (; pixel < loopBound; pixel += RGB_SPECIES.length()) {
			var rgbValues = ByteVector.fromArray(RGB_SPECIES, image, pixel);
			var newRgbValues = rgbValues.neg();
			newRgbValues.intoArray(newImage, pixel);
		}
		// remainder
		for (; pixel + 2 < image.length; pixel += 3) {
			byte blue = image[pixel];
			byte green = image[pixel + 1];
			byte red = image[pixel + 2];

			byte newRed = (byte) -red;
			byte newGreen = (byte) -green;
			byte newBlue = (byte) -blue;

			newImage[pixel] = newBlue;
			newImage[pixel + 1] = newGreen;
			newImage[pixel + 2] = newRed;
		}

		return newImage;
	}

	/*
	 * ROTATING COLORS
	 */

	private static byte[] rotateColors(byte[] image) {
		byte[] newImage = new byte[image.length];

		for (int pixel = 0; pixel + 2 < image.length; pixel += 3) {
			byte blue = image[pixel];
			byte green = image[pixel + 1];
			byte red = image[pixel + 2];

			newImage[pixel] = red;
			newImage[pixel + 1] = blue;
			newImage[pixel + 2] = green;
		}

		return newImage;
	}

	private static byte[] rotateColors_vectorized(byte[] image) {
		byte[] newImage = new byte[image.length];

		// Because the loop advances in `RGB_STEPS`, not the number of lanes
		// as intended by the vector API, the loop bound it computes can
		// be too high.
		// E.g. for 32 lanes and image length 64, `RGB_SPECIES.loopBound(image.length)`
		// is 64 for the two iterations [0..31] and [32..63]. But if we only
		// take steps of 30, the loop condition would lead to iterations [0..29],
		// [30..59], [60..💥]. Subtracting `RGB_STEPS` prevents that.
		int loopBound = RGB_SPECIES.loopBound(image.length) - RGB_STEPS;
		int pixel = 0;
		// vectorized loop:
		// for rotating colors, it's helpful to always deal in color value triples
		// (i.e. full pixels), so advance in `RGB_STEPS`
		for (; pixel < loopBound; pixel += RGB_STEPS) {
			var rgbValues = ByteVector.fromArray(RGB_SPECIES, image, pixel);
			var newRgbValues = rgbValues.rearrange(COLOR_SHUFFLE);
			newRgbValues.intoArray(newImage, pixel);
		}
		// remainder
		for (; pixel + 2 < image.length; pixel += 3) {
			byte blue = image[pixel];
			byte green = image[pixel + 1];
			byte red = image[pixel + 2];

			newImage[pixel] = red;
			newImage[pixel + 1] = blue;
			newImage[pixel + 2] = green;
		}

		return newImage;
	}

	/**
	 * Rotate RGB values within each triple, but not across triples
	 * @param newIndex the index in the shuffled vector
	 * @return the index in the old vector mapped to the new one
	 */
	private static int rotateRgbValues(int newIndex) {
		if (newIndex >= RGB_STEPS)
			return newIndex;

		int newValueIndexInTriple = newIndex % 3;
		int tripleStartIndex = newIndex - newValueIndexInTriple;

		int oldValueIndexInTriple = Math.floorMod(newValueIndexInTriple - 1, 3);
		return tripleStartIndex + oldValueIndexInTriple;
	}

	/*
	 * SHIFTING COLORS
	 */

	private static byte[] purpleShift(byte[] image) {
		byte[] newImage = new byte[image.length];

		double imageLength = image.length;
		for (int pixel = 0; pixel + 2 < image.length; pixel += 3) {
			double purpleQuotient = (pixel / imageLength);
			// ignores one-complement and maps [0d...127d; 128d...255d] to [0...127, -128...-1]
			byte purpleIndex = (byte) (255 * purpleQuotient);

			byte blue = image[pixel];
			byte green = image[pixel + 1];
			byte red = image[pixel + 2];

			// boost blue and red to tint purple
			byte newBlue = maxByte(purpleIndex, blue);
			byte newRed = maxByte(purpleIndex, red);

			newImage[pixel] = newBlue;
			newImage[pixel + 1] = green;
			newImage[pixel + 2] = newRed;
		}

		return newImage;
	}

	private static byte[] purpleShift_vectorized(byte[] image) {
		byte[] newImage = new byte[image.length];

		double imageLength = image.length;
		// see comment in `rotateColors_vectorized`
		int loopBound = RGB_SPECIES.loopBound(image.length) - RGB_STEPS;
		int pixel = 0;
		// vectorized loop:
		// for shifting the color, it's helpful to always deal in color value triples
		// (i.e. full pixels), so advance in `RGB_STEPS`
		for (; pixel < loopBound; pixel += RGB_STEPS) {
			// Deviating from the classic loop, the quotient is not computed for each pixel,
			// but for each "pixel block" of length `RGB_STEPS`. This means the resulting image
			// differs from the one produced by the classic loop and also across different
			// CPU architectures with different species lengths.
			double purpleQuotient = (pixel / imageLength);
			byte purpleIndex = (byte) (255 * purpleQuotient);

			var rgbValues = ByteVector.fromArray(RGB_SPECIES, image, pixel);
			var purpleRgbValues = ByteVector.zero(RGB_SPECIES).blend(purpleIndex, PURPLE_SHIFT);
			var purpleMask = rgbValues.compare(VectorOperators.UNSIGNED_LT, purpleRgbValues);
			var newRgbValues = rgbValues.blend(purpleRgbValues, purpleMask);

			newRgbValues.intoArray(newImage, pixel);
		}
		// remainder
		for (; pixel + 2 < image.length; pixel += 3) {
			double purpleQuotient = (pixel / imageLength);
			byte purpleIndex = (byte) (255 * purpleQuotient);

			byte blue = image[pixel];
			byte green = image[pixel + 1];
			byte red = image[pixel + 2];

			// boost blue and red to tint purple
			byte newBlue = maxByte(purpleIndex, blue);
			byte newRed = maxByte(purpleIndex, red);

			newImage[pixel] = newBlue;
			newImage[pixel + 1] = green;
			newImage[pixel + 2] = newRed;
		}

		return newImage;
	}

	private static byte maxByte(byte x, byte y) {
		return Byte.compareUnsigned(x, y) > 0 ? x : y;
	}

}
