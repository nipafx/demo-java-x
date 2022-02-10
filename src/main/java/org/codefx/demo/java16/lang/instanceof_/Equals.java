package org.codefx.demo.java16.lang.instanceof_;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

/**
 * Pattern matching in {@code if} works great for implementing {@code equals}.
 */
public class Equals {

	private final String someField;
	private final String anotherField;

	public Equals(String someField, String anotherField) {
		this.someField = requireNonNull(someField);
		this.anotherField = requireNonNull(anotherField);
	}

	@Override
	// note that an `equals` implementation that uses `instanceof`,
	// should be final to prevent subclasses from overriding it,
	// which usually breaks symmetry or transitivity
	public final boolean equals(Object o) {
		return o instanceof Equals other
				&& someField.equals(other.someField)
				&& anotherField.equals(other.anotherField);
	}

	@Override
	public int hashCode() {
		return Objects.hash(someField, anotherField);
	}

}
