package org.codefx.demo.java9.internal.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.stream.StreamSupport;

public class ResourceFileEncoding {

	private static final String PATH_PREFIX = "out/";

	public static void main(String[] args) {
		openFileAndPrint("config.properties", ResourceFileEncoding::printWithPropertyResourceBundle);
		openFileAndPrint("config.properties", ResourceFileEncoding::printWithProperties);
	}

	private static void openFileAndPrint(String propertyFileName, PrintProperties print) {
		try (InputStream propertyFile = new FileInputStream(PATH_PREFIX + propertyFileName)) {
			print.print(propertyFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void printWithPropertyResourceBundle(InputStream propertyFile) throws IOException {
		System.out.println("\nWith `PropertyResourceBundle`...");
		PropertyResourceBundle properties = new PropertyResourceBundle(propertyFile);
		properties.getKeys().asIterator().forEachRemaining(key -> {
			String value = properties.getString(key);
			System.out.println(key + " = " + value);
		});
	}

	private static void printWithProperties(InputStream propertyFile) throws IOException {
		System.out.println("\nWith `Properties`...");
		Properties properties = new Properties();
		properties.load(propertyFile);
		properties.forEach((key, value) -> System.out.println(key + " = " + value));
	}

	private interface PrintProperties {
		void print(InputStream propertyFile) throws IOException;
	}

}
