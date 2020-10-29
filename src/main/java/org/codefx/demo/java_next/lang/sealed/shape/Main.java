package org.codefx.demo.java_next.lang.sealed.shape;

public class Main {

	public static void main(String[] args) {
		Shape shape = new Circle(3);
		System.out.println(areaMessage(shape));
	}

	private static String areaMessage(Shape shape) {
		if (shape instanceof Circle)
			return "Circle: " + shape.area();
		else if (shape instanceof Rectangle)
			return "Rectangle: " + shape.area();
		else if (shape instanceof RightTriangle)
			return "Triangle: " + shape.area();
		// :(
		throw new IllegalArgumentException();
	}

	private static Shape rotate(Shape shape, double angle) {
		if (shape instanceof Circle) return shape;
		else if (shape instanceof Rectangle) return shape.rotate(angle);
		else if (shape instanceof RightTriangle) return shape.rotate(angle);
		// :(
		throw new IllegalArgumentException();
	}

}
