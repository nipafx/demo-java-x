package org.codefx.demo.java12.api.string;

public class Indent {

	public static void main(String[] args) {
		variousIndentations();
		setIndentation();
	}

	private static void variousIndentations() {
	/*
			// imagine raw string literals where a thing:
            String html = ```
                <body>
                    <h1>Header</h1>
                </body>```;
	*/
		String html = ""
				+ "            <body>\n"
				+ "                <h1>Header</h1>\n"
				+ "            </body>";

		// then it would be helpful to manipulate indentation depth
		System.out.println("org:\n" + html + "\n");
		System.out.println("0:\n" + html.indent(0) + "\n");
		System.out.println("2:\n" + html.indent(2) + "\n");
		System.out.println("4:\n" + html.indent(4) + "\n");
		System.out.println("-2:\n" + html.indent(-2) + "\n");
		System.out.println("-4:\n" + html.indent(-4) + "\n");
		System.out.println("-8:\n" + html.indent(-8) + "\n");
		System.out.println("-12:\n" + html.indent(-12) + "\n");
		System.out.println("-16:\n" + html.indent(-16) + "\n");
	}

	private static void setIndentation() {
	/*
			// imagine raw string literals where a thing:
            String html = ```
                <body>
                    <h1>Header</h1>
                </body>```;
				.indent(-16);
	*/
		String html = (""
				+ "                <body>\n"
				+ "                    <h1>Header</h1>\n"
				+ "                </body>")
				.indent(-16);
		System.out.println(html);
	}

}
