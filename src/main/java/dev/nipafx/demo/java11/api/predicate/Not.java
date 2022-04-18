package dev.nipafx.demo.java11.api.predicate;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.function.Predicate.not;

public class Not {

	public static void main(String[] args) {
		// want to print non-blank strings
		Stream.of("a", "b", "", "c")
				// ugh, lambda
				.filter(string -> !string.isBlank())
				// compiler has no target for method reference ~> error
//				.filter((String::isBlank).negate())
				// ugh, cast
				.filter(((Predicate<String>) String::isBlank).negate())
				// there you go!
				.filter(not(String::isBlank))
				.forEach(System.out::println);

	}

}
