package org.codefx.demo.java11.api.collection;

import java.util.List;

public class ToArray {

	public static void main(String[] args) {
		// before Java 11
		List<String> list = List.of("a", "b", "c");
		Object[] objects = list.toArray();
		String[] strings_0 = list.toArray(new String[0]);
		String[] strings_size = list.toArray(new String[list.size()]);

		// with Java 11
		String[] strings_fun = list.toArray(String[]::new);
	}

}
