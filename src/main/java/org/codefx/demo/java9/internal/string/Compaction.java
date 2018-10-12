package org.codefx.demo.java9.internal.string;

import java.lang.reflect.Field;

public class Compaction {

	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
		process("Hello, World.");
		process("Hello, World. 😎");
	}

	private static void process(String string) throws NoSuchFieldException, IllegalAccessException {
		printInternals(string);
		System.out.println(string);
	}

	private static void printInternals(String hello) throws NoSuchFieldException, IllegalAccessException {
		String javaVersion = System.getProperty("java.version");
		switch (javaVersion.charAt(0)) {
			case '1' : printInternalsInJava8(hello); break;
			case '9' : printInternalsInJava9(hello); break;
			default: throw new IllegalStateException("Fancy Java version " + javaVersion);
		}
	}

	private static void printInternalsInJava8(String hello) throws NoSuchFieldException, IllegalAccessException {
		char[] value = extractValues(hello, "value", char[].class);
		for (char c : value)
			System.out.print(c + " ");
		System.out.println();
	}

	private static void printInternalsInJava9(String hello) throws NoSuchFieldException, IllegalAccessException {
		byte[] value = extractValues(hello, "value", byte[].class);
		for (byte b : value)
			System.out.print(b + " ");
		System.out.println();
	}

	private static <T> T extractValues(String instance, String fieldName, Class<T> fieldType) throws NoSuchFieldException, IllegalAccessException {
		Field values = instance.getClass().getDeclaredField(fieldName);
		values.setAccessible(true);
		return fieldType.cast(values.get(instance));
	}

}
