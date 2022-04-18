package dev.nipafx.demo.java11.api.predicate;

import java.util.List;
import java.util.regex.Pattern;

public class RegExp {

	public static void main(String[] args) {
		var nonWordCharacter = Pattern.compile("\\W");
		var containsNonWordCharacter = Pattern.compile("\\w*\\W\\w*");
		var bandNames = List.of("Metallica", "Motörhead");

		System.out.println("FIND 'containsNonWordCharacter'");
		bandNames.stream()
				.filter(containsNonWordCharacter.asPredicate())
				.forEach(System.out::println);

		System.out.println("MATCH 'containsNonWordCharacter'");
		bandNames.stream()
				.filter(containsNonWordCharacter.asMatchPredicate())
				.forEach(System.out::println);

		System.out.println("FIND 'nonWordCharacter'");
		bandNames.stream()
				.filter(nonWordCharacter.asPredicate())
				.forEach(System.out::println);

		System.out.println("MATCH 'nonWordCharacter'");
		bandNames.stream()
				.filter(nonWordCharacter.asMatchPredicate())
				.forEach(System.out::println);
	}

}
