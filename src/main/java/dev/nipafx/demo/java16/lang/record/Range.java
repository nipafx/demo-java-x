package dev.nipafx.demo.java16.lang.record;

import java.io.Serializable;

//                these are "components"
public record Range(int low, int high)
		// no class inheritance
		// extends AbstractList

		// but can implement interfaces
		implements Comparable<Range>,

		// because records are just data, they're obvious candidates for serialization,
		// which can rely on accessors and canonical constructor to (de)serialize instances
		// instead of black reflection magic that bypasses classic guarantees of the object model
		Serializable {

	// compiler generates:
	//  * (final) fields
	//  * canonical constructor
	//  * accessors `low()`, `high()`
	//  * equals, hashCode, toString using `low` and `high`

	// records can't have additional fields
//	private final distance;

	// canonical constructor can be overridden
	public Range {
		if (high < low)
			throw new IllegalArgumentException();
		// parameters are assigned to fields after this block
	}

	// additional constructors can be declared
	public Range(int high) {
		this(0, high);
	}

	// Accessors can be overridden, but because records should be *transparent* carriers
	// it is not recommended to return anything but the fields value. This should always be true:
	// range.equals(new Range(range.low(), range.high()));
	@Override
	public int low() {
		return low;
	}

	// records can have other methods, like this one to implement `Comparable`
	@Override
	public int compareTo(Range other) {
		return this.low == other.low
				? this.high - other.high
				: this.low - other.low;
	}

	// the generated methods `equals`, `hashCode`, `toString` can be overridden
	@Override
	public String toString() {
		return "[%d, %d]".formatted(low, high);
	}

}
