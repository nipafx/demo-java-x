package org.codefx.demo.java_next.lang.sealed.shape;

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
