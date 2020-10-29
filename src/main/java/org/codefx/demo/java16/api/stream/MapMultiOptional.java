package org.codefx.demo.java16.api.stream;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class MapMultiOptional {

	public void mapMulti() {
		Stream<String> numberWords = Stream
				.of(0, 1, 2, 1, 0)
				.map(this::toWord)
				.mapMulti(Optional::ifPresent);
		Stream<Integer> numberWordLengths = Stream
				.of(0, 1, 2, 1, 0)
				.map(this::toWord)
				.<String>mapMulti(Optional::ifPresent)
				.map(String::length);
		Stream<String> numberWordsDuplicated = Stream
				.of(0, 1, 2, 1, 0)
				.mapMulti((Integer number, Consumer<String> result) -> toWord(number).ifPresent(result))
				.map(s -> s + s);
	}

	private Optional<String> toWord(int number) {
		return switch (number) {
			case 0 -> Optional.of("zero");
			case 1 -> Optional.of("one");
			default -> Optional.empty();
		};
	}

}
