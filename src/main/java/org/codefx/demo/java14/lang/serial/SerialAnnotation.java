package org.codefx.demo.java14.lang.serial;

import java.io.Serial;
import java.io.Serializable;

public class SerialAnnotation implements Serializable {

	/*
	 * You can use the `@Serial` annotation to mark members that are used
	 * by the serialization mechanism. The Javadoc lists them:
	 *
	 *     https://docs.oracle.com/en/java/javase/14/docs/api/java.base/java/io/Serial.html
	 *
	 * Compilers can then issue warnings or errors if `@Serial` is applied to
	 * other members. Similarly to `@Override`, this allows you to document
	 * your intent for other developers and the compiler to check.
	 *
	 * As of Java 18, the JDK compiler does not issue warnings/errors, though.
	 */

	@Serial
	private static final long serialVersionUid = 1166861880447174899L;

	@Serial
	private Object writeRelpace() {
		return this;
	}

}
