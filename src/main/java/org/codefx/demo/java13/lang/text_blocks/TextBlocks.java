package org.codefx.demo.java13.lang.text_blocks;

public class TextBlocks {

	public static void main(String[] args) {
		System.out.println("""
				Hello,
				multiline
				text blocks!""");

		whiteSpace();
		openingNewlines();
		closingNewlines();
		stripIndent();

		indentity();
		nested();
		translateEscapes();
		noInterpolation();
	}

	private static void whiteSpace() {
		String html = """
				<html>
					<body>
						<p>"Hello, text blocks!"</p>
					</body>
				</html>
				""";
		System.out.println("----");
		System.out.println(html);
		System.out.println("----");
	}

	private static void openingNewlines() {
		System.out.println("ADDITIONAL NEWLINES AT START ARE POSSIBLE:");

		System.out.println("----");
		System.out.println("""
				Hello, multiline text blocks!""");

		System.out.println("----");
		System.out.println("""

				Hello, multiline text blocks!""");

		System.out.println("----");
				System.out.println("""


				Hello, multiline text blocks!""");

		System.out.println("----");
		System.out.println();
	}

	private static void closingNewlines() {
		System.out.println("CLOSING DELIMITER POSITION MATTERS!");

		System.out.println("----");
		System.out.println("""
				Hello, multiline text blocks!""");

		System.out.println("----");
		System.out.println("""
				Hello, multiline text blocks!
				""");

		System.out.println("----");
		System.out.println("""
				Hello, multiline text blocks!
			""");

			System.out.println("----");
		System.out.println("""
				Hello, multiline text blocks!
		""");

		System.out.println("----");
		System.out.println();
	}

	private static void stripIndent() {
		String html = ""
				+ "				<html>\n"
				+ "					<body>\n"
				+ "						<p>\"Hello, text blocks!\"</p>\n"
				+ "					</body>\n"
				+ "				</html>\n"
				+ "				";
		System.out.println("----");
		System.out.println(html.stripIndent());
		System.out.println("----");
	}

	private static void indentity() {
		String hello = """
				Hello, "text blocks"!
				""";
		String literal = "Hello, \"text blocks\"!\n";
		System.out.println("equal: " + hello.equals(literal));
		System.out.println("identical: " + (hello == literal));
	}

	private static void nested() {
		String text = """
				String text = \"""
					A text block inside a text block
				\""";
				""";
		System.out.println(text);
	}

	private static void translateEscapes() {
		String literal = ".\t.";
		String composed = ".\\t.";
		System.out.println(literal);
		System.out.println(composed);
		System.out.println("Equal literals: "
				+ literal.equals(composed));
		System.out.println("Equal translations: "
				+ literal.translateEscapes()
				.equals(composed.translateEscapes()));
	}

	private static void noInterpolation() {
		String greeting = "Hello";
		String hello = """
				${greeting}, text blocks!
				""";
		System.out.println(hello);
		hello = """
				$greeting, text blocks!
				"""
				.replace("$greeting", greeting);
		System.out.println(hello);
		hello = """
				%s, text blocks!
				"""
				.formatted(greeting);
		System.out.println(hello);
	}

}
