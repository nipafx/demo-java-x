package org.codefx.demo.java16.lang.staticinner;

import java.time.LocalDateTime;

public class OuterClass {

	// Compiling this class with JDK <16 (e.g. with
	//     javac --enable-preview --release 15 OuterClass.java
	// ) leads to the compile errors in the following comments because static members
	// of non-static inner classes weren't allowed. They are since JDK 16.

	public class InnerClass {

		// error: Illegal static declaration in inner class OuterClass.InnerClass - modifier 'static' is only allowed in constant variable declarations
		static final String DATE = LocalDateTime.now().toString();

		// error: Illegal static declaration in inner class OuterClass.InnerClass - modifier 'static' is only allowed in constant variable declarations
		static String date() {
			return DATE;
		}

		// error: static declarations not allowed in inner classes
		record InnerRecord(String component) { }

	}

}