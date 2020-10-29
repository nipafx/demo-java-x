package org.codefx.demo.java_next.lang.sealed.shape;

public class RightTriangle implements Triangle {

	private final double adjacent;
	private final double opposite;

	public RightTriangle(double adjacent, double opposite) {
		this.adjacent = adjacent;
		this.opposite = opposite;
	}

	@Override
	public double area() {
		interface People { String name(); }
		record Person(String name) implements People { }
		record Persons(String... names) { }

		People p = new Person("John Doe");

		return adjacent * opposite / 2;
	}

}
