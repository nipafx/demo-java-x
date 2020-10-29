package org.codefx.demo.java_next.lang.sealed.shape;

public sealed interface Shape
		permits Circle, Rectangle, Triangle, Unicorn {

	double area();

	default Shape rotate(double angle) {
		return this;
	}

	default String areaMessage() {
		if (this instanceof Circle)
			return "Circle: " + area();
		else if (this instanceof Rectangle)
			return "Rectangle: " + area();
		else if (this instanceof RightTriangle)
			return "Triangle: " + area();
		// :(
		throw new IllegalArgumentException();
	}

}
