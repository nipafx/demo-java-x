package org.codefx.demo.java16.lang.staticinner;

import java.time.LocalDateTime;

public class OuterClass {

	// TODO

	public static void main(String[] args) {
		System.out.println("foo");
	}

	public class InnerClass {

		static final String DATE = LocalDateTime.now().toString();

		static String date() {
			return DATE;
		}

		record InnerRecord(String component) { }

	}

}