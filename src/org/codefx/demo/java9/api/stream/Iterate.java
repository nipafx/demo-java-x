package org.codefx.demo.java9.api.stream;

import java.util.stream.Stream;

public class Iterate {

	public static void main(String[] args) {
		Stream.iterate(0, i -> i < 5, i -> i + 1)
				.forEach(System.out::println);
	}

}
