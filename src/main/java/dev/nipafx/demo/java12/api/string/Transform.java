package dev.nipafx.demo.java12.api.string;

import java.util.function.Function;
import java.util.stream.Stream;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;

public class Transform {

	public static void main(String[] args) {
		setIndentation();
		endOfCallChain();
	}

	private static void setIndentation() {
	/*
			// imagine raw string literals where a thing:
			String html = ```
				<body>
					<h1>Header</h1>
				</body>```
				.transform(s -> setIndentationToDepth(s, 1));
	*/
		String html = (""
				+ "				<body>\n"
				+ "					<h1>Header</h1>\n"
				+ "				</body>")
				// we can use `transform` to apply a custom indentation change
				.transform(s -> setIndentationToDepth(s, 1));
		System.out.println(html);
	}

	private static String setIndentationToDepth(String input, int depth) {
		int currentDepth= determineIndentationDepth(input);
		Function<String, String> changeIndentation = determineIndentationChange(depth, currentDepth);
		return input
				.lines()
				.map(changeIndentation)
				.collect(joining(lineSeparator()));
	}

	private static int determineIndentationDepth(String input) {
		return input
				.lines()
				.findFirst()
				.map(firstLine -> {
					int i = 0;
					while (firstLine.charAt(i) == '\t')
						i++;
					return i;
				})
				.orElse(0);
	}

	private static Function<String, String> determineIndentationChange(int depth, int currentDepth) {
		if (depth < currentDepth)
			// reduceIndentation
			return line -> line.substring(currentDepth - depth);
		else if (depth > currentDepth)
			// increaseIndentation
			return line -> "\t".repeat(depth - currentDepth) + line;
		else
			// keep indentation
			return Function.identity();
	}

	private static void endOfCallChain() {
		Stream
				.of(1, 3, 12)
				.map(no -> "Task #" + no)
				.reduce("", (result, task) -> result.length() > task.length() ? result : task)
				// we can use `transform` to do something at the end (or in the middle) of a call chain
				.transform(task -> task + " [DONE]");
	}

}
