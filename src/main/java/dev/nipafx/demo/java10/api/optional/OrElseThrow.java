package dev.nipafx.demo.java10.api.optional;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class OrElseThrow {

	public static void main(String[] args) {
		Optional<String> randomOptional = createRandomOptional();
		if (randomOptional.isPresent()) {
			// `orElseThrow` works exactly like `get`
			String value = randomOptional.orElseThrow();
			System.out.println(value);
		}
	}

	private static Optional<String> createRandomOptional() {
		return ThreadLocalRandom.current().nextInt() < 0
				? Optional.empty()
				: Optional.of("not empty");
	}

}
