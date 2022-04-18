package dev.nipafx.demo.java12.api.format;

import java.text.NumberFormat;
import java.text.NumberFormat.Style;
import java.util.Locale;

public class CompactFormat {

	public static void main(String[] args) {
		followers();
		decimalPowers();
	}

	private static void followers() {
		NumberFormat followers = NumberFormat
				.getCompactNumberInstance(new Locale("en", "US"), Style.SHORT);
		followers.setMaximumFractionDigits(1);
		System.out.println(followers.format(5412) + " followers");
	}

	private static void decimalPowers() {
		NumberFormat shortened = NumberFormat
				.getCompactNumberInstance(new Locale("en", "US"), Style.SHORT);
		NumberFormat shortenedWithFraction = NumberFormat
				.getCompactNumberInstance(new Locale("en", "US"), Style.SHORT);
		shortenedWithFraction.setMaximumFractionDigits(1);

		for (int exp = 0; exp <= 12; exp++)
			for (int secondDigit : new int[]{ 4, 5 }) {
				double number = Math.pow(10, exp) + secondDigit * Math.pow(10, exp - 1);
				System.out.printf(
						"%,f ~> %s / %s%n",
						number,
						shortened.format(number),
						shortenedWithFraction.format(number));
			}
	}

}
