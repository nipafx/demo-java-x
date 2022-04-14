package org.codefx.demo.java10.api.stream;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectToUnmodifiable {

	public static void main(String[] args) {
		List<String> letterList = Stream
				.of("A", "B", "C")
				// collect to an immutable lists just like the `List.of` ones
				.collect(Collectors.toUnmodifiableList());
		// it's even the same classes, but that's an implementation detail
		System.out.println("Same class as `List.of`: " + (letterList.getClass() == List.of("A", "B", "C").getClass()));

		// same for sets
		Set<String> letterSet = Stream
				.of("A", "B", "C")
				// collect to an immutable lists just like the `List.of` ones
				.collect(Collectors.toUnmodifiableSet());
		// it's even the same classes, but that's an implementation detail
		System.out.println("Same class as `Set.of`: " + (letterSet.getClass() == Set.of("A", "B", "C").getClass()));
	}

}
