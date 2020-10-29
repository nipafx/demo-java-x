package org.codefx.demo.java16.api.stream;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class MapMultiSimple {

	public void mapMulti() {
		List<Integer> numbers = Stream.of(1, 2, 3, 4)
				.<Integer> mapMulti((number, lift) -> lift.accept(number))
				.collect(toList());
	}

}
