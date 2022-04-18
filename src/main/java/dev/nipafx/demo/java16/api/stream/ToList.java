package dev.nipafx.demo.java16.api.stream;

import java.util.List;
import java.util.stream.Stream;

public class ToList {

	public static void main(String[] args) {
		// create a list directly from stream, without collectors
		List<String> letters = Stream
				// these lists accept null
				.of("A", "B", "C", null)
				.toList();

		// they are immutable, though
		try {
			letters.clear();
			System.exit(1);
		} catch (UnsupportedOperationException ex) {
			System.out.println("Immutable stream-to-list: " + letters);
		}
	}

}
