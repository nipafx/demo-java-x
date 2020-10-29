package org.codefx.demo.java_next.lang.sealed.shape;

public final class Circle implements Shape {

	private final double radius;

	public Circle(double radius) {
		this.radius = radius;
	}

	@Override
	public double area() {
		return Math.PI * radius * radius;
	}

}
