package org.codefx.demo.java10.lang.var;

import java.math.BigDecimal;

public class Traits {

	public static void main(String[] args) {
		var ecorp = new ECorp();
		report(ecorp);
	}

	/*
	 * DOWNSIDES
	 *
	 *  - requires considerable amount of boilerplate
	 *  - has a non-trivial structure
	 *  - delegating interface has less flexibility than a class
	 *  - Object's methods can not be default-implemented,
	 *    so toString, equals, hashCode, ... can never be forwarded
	 *    to existing implementation
	 *
	 * ALTERNATIVES
	 *
	 *  - extension type collecting all needed methods
	 *  - utility methods
	 */

	public static void report(Megacorp megacorp) {
		// without `var` there would be no way to declare a variable
		// of type `IsSuccessful` and `IsEvil`
		var corp = (MegacorpDelegate & IsSuccessful & IsEvil) () -> megacorp;
		System.out.printf(
				"Corporation %s is %s and %s.\n",
				corp.name(),
				// relying on `IsSuccessful`
				corp.isSuccessful() ? "successful" : "a failure",
				// relying on `IsEvil`
				corp.isEvil() ? "evil" : "a failure"
		);
	}

	// important domain concept, used throughout the system
	interface Megacorp {

		String name();

		BigDecimal earnings();

		BigDecimal taxes();

	}

	static class ECorp implements Megacorp {

		@Override
		public String name() {
			return "E-Corp";
		}

		@Override
		public BigDecimal earnings() {
			// "One. Million. Dollars"
			return new BigDecimal("1000000");
		}

		@Override
		public BigDecimal taxes() {
			return BigDecimal.ONE;
		}

	}

	// created right next to `Megacorp` to allow easy extension
	// throughout the system
	@FunctionalInterface
	interface MegacorpDelegate extends Megacorp {

		// there can only be this one abstract method
		Megacorp delegate();

		@Override
		default String name() {
			return delegate().name();
		}

		@Override
		default BigDecimal earnings() {
			return delegate().earnings();
		}

		@Override
		default BigDecimal taxes() {
			return delegate().taxes();
		}

		// because `Object` methods can not have default implementations,
		// there can be no delegations for toString, equals, hashCode, ... :(

	}

	// these are concepts that are only useful in a very narrow part of the system
	// and so they are not added to the original interface to prevent polluting it
	// with too many methods;
	// these traits must not have abstract methods

	interface IsSuccessful extends Megacorp {

		BigDecimal SUCCESS_BOUNDARY = new BigDecimal("500000000");

		default boolean isSuccessful() {
			return earnings().compareTo(SUCCESS_BOUNDARY) > 0;
		}

	}

	interface IsEvil extends Megacorp {

		default boolean isEvil() {
			return true;
		}

	}

}
