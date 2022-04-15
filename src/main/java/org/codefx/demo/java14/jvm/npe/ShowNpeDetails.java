package org.codefx.demo.java14.jvm.npe;

public class ShowNpeDetails {

	private final String field = null;

	public static void main(String[] args) {
		// Each of these methods throws a `NullPointerException`.
		// To experiment, uncomment one of them and run them as single source file:
		//
		//     on JDK 14, 15 (showing details mus be activated with a flag)
		//     java -XX:+ShowCodeDetailsInExceptionMessages ShowNpeDetails.java
		//
		//     on JDK 16+ (details are shown by default, so no flag needed)
		//     java ShowNpeDetails.java

//		onVariable();
//		onField();
//		onMethodReturn();
	}

	private static void onVariable() {
		String variable = null;
		variable.length();
	}

	private static void onField() {
		new ShowNpeDetails().field.length();
	}

	private static void onMethodReturn() {
		new ShowNpeDetails().returnNull().length();
	}

	private String returnNull() {
		return null;
	}

}
