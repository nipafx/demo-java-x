package dev.nipafx.demo.java16.lang.instanceof_;

public class Details {

	public void scoped(Object object) {
		// variable is in scope wherever compiler can prove pattern matches
		// even later in same expression
		if (object instanceof String string && string.length() > 50)
			System.out.println("Long string");

		// compiler error because || means it's not necessarily a string
//		if (object instanceof String string || string.length() > 50)
//			System.out.println("Long string");
	}

	public void inverted(Object object) {
		if (!(object instanceof String string))
			throw new IllegalArgumentException();
		System.out.println(string.length());
	}

	public void upcast(String string) {
		// compile error on JDK 16+
//		if (string instanceof CharSequence sequence)
//			System.out.println("Duh");
	}

	public void reassign(Object object) {
		if (object instanceof String string) {
			// no longer a compile error on Java 16
			string = string.substring(5);
			System.out.println(string);
		}
	}

}
