package org.codefx.demo.java9.internal.version;

import java.lang.Runtime.Version;

public class VersionSchema {

	public static void main(String[] args) {
		printSystemProperty("java.version");
		printSystemProperty("java.runtime.version");
		printSystemProperty("java.vm.version");
		printSystemProperty("java.specification.version");
		printSystemProperty("java.vm.specification.version");

		Version version = Runtime.version();

		System.out.println();
		System.out.println("Reported by runtime: " + version);

		switch (version.major()) {
			case 9:
				System.out.println("Modularity!");
				break;
			case 10:
				System.out.println("Value Types!");
				break;
		}
	}

	private static void printSystemProperty(String property) {
		String propertyValue = System.getProperty(property);
		System.out.println("System property \"" + property + "\": " + propertyValue);
	}

}
