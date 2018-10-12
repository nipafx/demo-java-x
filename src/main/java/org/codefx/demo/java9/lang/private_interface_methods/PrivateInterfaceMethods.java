package org.codefx.demo.java9.lang.private_interface_methods;

import java.util.stream.IntStream;

class PrivateInterfaceMethods {

	interface InJava8 {

		default boolean evenSum(int... numbers) {
			return sum(numbers) % 2 == 0;
		}

		default boolean oddSum(int... numbers) {
			return sum(numbers) % 2 == 1;
		}

		// we don't want this to be public;
		// but how else do we reuse in Java 8?
		default int sum(int[] numbers) {
			return IntStream.of(numbers).sum();
		}

	}

	interface InJava9 {

		default boolean evenSum(int... numbers) {
			return sum(numbers) % 2 == 0;
		}

		default boolean oddSum(int... numbers) {
			return sum(numbers) % 2 == 1;
		}

		// in Java 9 just make it private
		private int sum(int[] numbers) {
			return IntStream.of(numbers).sum();
		}

	}

}
