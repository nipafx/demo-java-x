package dev.nipafx.demo.java17.lang.sealed;

/**
 * A sealed type can only be (directly) implemented by the permitted
 * types, in this case {@link Circle}, {@link Rectangle}, etc.
 *
 * These classes must explicitly state how they deal with further inheritance.
 * Note that they're all either {@code final}, {@code sealed}, or
 * {@code non-sealed}.
 */
public sealed interface Shape
		permits Circle, Rectangle, Triangle, Unicorn {

	double area();

	default Shape rotate(double angle) {
		return this;
	}

}
