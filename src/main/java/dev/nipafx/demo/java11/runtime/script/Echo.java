package dev.nipafx.demo.java11.runtime.script;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Stream;

// run with:
//     cat echo-haiku.txt | java src/main/java/dev/nipafx/demo/java11/runtime/script/Echo.java
// plus options `--sort`, `--unique`, or `--reverse`
public class Echo {

	public static void main(String[] args) throws IOException {
		var lines = readInput();
		for (var arg : args)
			lines = modifyStream(arg, lines);
		lines.forEach(System.out::println);
	}

	private static Stream<String> modifyStream(String arg, Stream<String> input) {
		switch (arg){
			case "--sort": return input.sorted();
			case "--unique": return input.distinct();
			case "--reverse": return reverse(input);
			default: {
				System.out.println("Unknown argument '" + arg + "'.");
				return input;
			}
		}
	}

	private static Stream<String> reverse(Stream<String> input) {
		var reversed = new ArrayList<String>();
		input.forEach(line -> reversed.add(0, line));
		return reversed.stream();
	}

	private static Stream<String> readInput() throws IOException {
		var reader = new BufferedReader(new InputStreamReader(System.in));
		if (!reader.ready())
			return Stream.empty();
		else
			return reader.lines();
	}

}
