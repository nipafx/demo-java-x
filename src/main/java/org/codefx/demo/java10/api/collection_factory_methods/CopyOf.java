package org.codefx.demo.java10.api.collection_factory_methods;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CopyOf {

	public static void main(String[] args) {
		// given collections...
		List<String> list = List.of("A", "B", "C");
		Set<String> set = Set.of("A", "B", "C");
		Map<Integer, String> map = Map.of(1, "one", 2, "two", 3, "three");

		// ...it is super easy to create copies:
		List<String> listCopy = List.copyOf(list);
		Set<String> setCopy = Set.copyOf(set);
		Map<Integer, String> mapCopy = Map.copyOf(map);

		// the `copyOf` collections have the same properties as the `of` collections
		// (unmodifiable, no null, etc.)

		// but they're smart, they only copy if needed
		//
		List<String> arrayList = new ArrayList<>(list);
		System.out.println("Copy of `ArrayList` is new instance: " + (arrayList != List.copyOf(arrayList)));
		System.out.println("Copy of `List.of` is new instance: " + (list != List.copyOf(list)));
	}

}