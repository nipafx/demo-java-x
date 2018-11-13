package org.codefx.demo.java11.api.string;

public class Strip {

	public static void main(String[] args) {
		stripCharacters();
		stripLeadingTrailing();
		isBlank();
	}

	private static void stripCharacters() {
		tableHeader();

		tableEntry("NULL", "\u0000");
		tableEntry("START OF HEADING", "\u0001");
		tableEntry("START OF TEXT", "\u0002");
		tableEntry("END OF TEXT", "\u0003");
		tableEntry("END OF TRANSMISSION", "\u0004");
		tableEntry("ENQUIRY", "\u0005");
		tableEntry("ACKNOWLEDGE", "\u0006");
		tableEntry("BELL", "\u0007");
		tableEntry("BACKSPACE", "\u0008");
		tableEntry("CHARACTER TABULATION", "\u0009");
		tableEntry("LINE FEED", "\n"); // U+000A leads to compile error
		tableEntry("LINE TABULATION", "\u000B");
		tableEntry("FORM FEED", "\u000C");
		tableEntry("CARRIAGE RETURN", "\r"); // U+000D leads to compile error
		tableEntry("SHIFT OUT", "\u000E");
		tableEntry("SHIFT IN", "\u000F");

		tableEntry("DATA LINK ESCAPE", "\u0010");
		tableEntry("DEVICE CONTROL ONE", "\u0011");
		tableEntry("DEVICE CONTROL TWO", "\u0012");
		tableEntry("DEVICE CONTROL THREE", "\u0013");
		tableEntry("DEVICE CONTROL FOUR", "\u0014");
		tableEntry("NEGATIVE ACKNOWLEDGE", "\u0015");
		tableEntry("SYNCHRONOUS IDLE", "\u0016");
		tableEntry("END OF TRANSMISSION BLOCK", "\u0017");
		tableEntry("CANCEL", "\u0018");
		tableEntry("END OF MEDIUM", "\u0019");
		tableEntry("SUBSTITUTE", "\u001A");
		tableEntry("ESCAPE", "\u001B");
		tableEntry("FILE SEPARATOR", "\u001C");
		tableEntry("GROUP SEPARATOR", "\u001D");
		tableEntry("RECORD SEPARATOR", "\u001E");
		tableEntry("UNIT SEPARATOR", "\u001F");
		tableEntry("SPACE", "\u0020");

		tableEntry("NO_BREAK SPACE", "\u00A0");
		// Unicode space characters as mentioned in Character::isWhitespace
		// (SPACE_SEPARATOR, LINE_SEPARATOR, or PARAGRAPH_SEPARATOR)
		// should be stripped, but aren't - did I pick the wrong unicodes?
		tableEntry("PARAGRAPH SEPARATOR", "\u00B6");
		tableEntry("SPACE SEPARATOR", "\u200B");
		tableEntry("LINE SEPARATOR", "\u2028");
	}

	private static void tableHeader() {
		tableLine("CHARACTER", "TRIM", "STRIP", " ");
		tableLine("", "", "", "-");
	}

	private static void tableEntry(String name, String string) {
		var trimmed = string.trim().length() == 0;
		// the following is equivalent to `name.isBlank()`
		var stripped = string.strip().length() == 0;

		tableLine(name, trimmed ? "✔" : "✘", stripped ? "✔" : "✘", " ");
	}

	private static void tableLine(String name, String trimmed, String stripped, String pad) {
		var paddedCharacter = rightPad(name, 26, pad);
		var paddedTrimmed = outerPad(trimmed, 6, pad);
		var paddedStripped = outerPad(stripped, 7, pad);
		System.out.println(paddedCharacter + "|" + paddedTrimmed + "|" + paddedStripped);
	}

	private static String rightPad(String string, int size, String pad) {
		int pads = size - string.length();
		return pads <= 0 ? string : string + pad.repeat(pads);
	}

	private static String outerPad(String string, int size, String pad) {
		int pads = size - string.length();
		return pads <= 0 ? string : pad.repeat(pads/2) + string + pad.repeat(pads - pads/2);
	}

	private static void stripLeadingTrailing() {
		System.out.println("Strip leading with `stripLeading`: [" + "   |   ".stripLeading() + "]");
		System.out.println("Strip trailing with `stripTrailing`: [" + "   |   ".stripTrailing() + "]");
		System.out.println("Strip leading and trainling with `strip`: [" + "   |   ".strip() + "]");
	}

	private static void isBlank() {
		System.out.println(" ".isBlank()); // space
		System.out.println(" ".isBlank()); // non-breaking space
	}

}
