package dev.nipafx.demo.java17.lang.sealed;

/**
 * Rectangle allows one further implementation, namely {@link Square}
 * (because it's {@code sealed}).
 */
public sealed class Rectangle
		implements Shape
		permits Square {

	private final double length;
	private final double height;

	public Rectangle(double length, double height) {
		this.length = length;
		this.height = height;
	}

	@Override
	public double area() {
		return length * height;
	}

}
