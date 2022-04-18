package dev.nipafx.demo.java17.lang.sealed;

/**
 * Because {@link Triangle} isn't sealed, {@code RightTriangle} can implement
 * it without having to be {@code final}, {@code sealed}, or {@code non-sealed}
 * itself.
 */
public class RightTriangle implements Triangle {

	private final double adjacent;
	private final double opposite;

	public RightTriangle(double adjacent, double opposite) {
		this.adjacent = adjacent;
		this.opposite = opposite;
	}

	@Override
	public double area() {
		return adjacent * opposite / 2;
	}

}
