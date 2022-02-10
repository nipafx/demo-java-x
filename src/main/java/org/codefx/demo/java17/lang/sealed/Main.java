package org.codefx.demo.java17.lang.sealed;

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
		else if (shape instanceof Triangle)
			return "Triangle: " + shape.area();
		else if (shape instanceof Unicorn)
			return "Unicorn: " + shape.area();
		// when not switching over a sealed type
		// (but using an if-else chain like here),
		// the compiler can't detect completeness,
		// so this else branch is still needed
		else
			throw new IllegalArgumentException();
	}

}
