package org.codefx.demo.java15.api.string;

public class FormatString {

	public static void main(String[] args) {
		if (args.length == 0)
			args = new String[] { "World "};

		// static `String::format` is pretty neat:
		String message1 = String.format("Hello, %s!", args[0]);
		System.out.println(message1);

		// but it's not great with text blocks because of the additional indentation
		// and distance from `format` to the parameters
		String message2 = String.format(
    			"""
				This is
				a clumsily lengthy
				way to say:
				Hello, %s!
				""",
				args[0]);
		System.out.println(message2);

		// enter the new non-static method `String::formatted` (behaves exactly like `format`):
		String message3 = """
				This is
				a clumsily lengthy
				way to say:
				Hello, %s!
				""".formatted(args[0]);
		System.out.println(message3);
	}

}
