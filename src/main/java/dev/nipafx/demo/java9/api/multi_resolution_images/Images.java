package dev.nipafx.demo.java9.api.multi_resolution_images;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BaseMultiResolutionImage;
import java.awt.image.MultiResolutionImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class Images {

	static final String[] IMAGE_URLS = {
			"https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/Shibuya_Night_%28HDR%29.jpg/320px-Shibuya_Night_%28HDR%29.jpg",
			"https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/Shibuya_Night_%28HDR%29.jpg/640px-Shibuya_Night_%28HDR%29.jpg",
			"https://upload.wikimedia.org/wikipedia/commons/thumb/4/4e/Shibuya_Night_%28HDR%29.jpg/800px-Shibuya_Night_%28HDR%29.jpg",
			"https://upload.wikimedia.org/wikipedia/commons/4/4e/Shibuya_Night_%28HDR%29.jpg" };

	public static void main(String[] args) throws IOException {
		MultiResolutionImage tokio = loadTokio();
		int desiredImageWidth = ThreadLocalRandom.current().nextInt(1500);
		Image variant = tokio.getResolutionVariant(desiredImageWidth, 1);

		System.out.printf("Width of image for %d: %d%n", desiredImageWidth, variant.getWidth(null));
	}

	private static MultiResolutionImage loadTokio() throws IOException {
		List<Image> tokios = new ArrayList<>();
		for (String url : IMAGE_URLS) {
			tokios.add(ImageIO.read(new URL(url)));
		}
		return new BaseMultiResolutionImage(tokios.toArray(new Image[0]));
	}

}
