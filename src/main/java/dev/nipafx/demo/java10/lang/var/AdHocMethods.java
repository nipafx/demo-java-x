package dev.nipafx.demo.java10.lang.var;

import java.math.BigDecimal;

public class AdHocMethods {

	/*
	 * DOWNSIDES
	 *
	 *  - verbose
	 *  - combination of non-trivial language features (anonymous types & type inference)
	 *  - falls apart when extracting methods
	 *
	 * ALTERNATIVES
	 *
	 *  - extension type collecting all needed methods
	 *  - utility methods
	 */

	public void report(Megacorp megacorp) {
		var corp = new SimpleMegacorp(megacorp) {
			final BigDecimal SUCCESS_BOUNDARY = new BigDecimal("500000000");

			boolean isSuccessful() {
				return earnings().compareTo(SUCCESS_BOUNDARY) > 0;
			}

			boolean isEvil() {
				return true;
			}
		};
		System.out.printf(
				"Corporation %s is %s and %s.\n",
				corp.name(),
				corp.isSuccessful() ? "successful" : "a failure",
				corp.isEvil() ? "evil" : "a failure"
		);
	}

	// important domain concept, used throughout the system
	interface Megacorp {

		String name();

		BigDecimal earnings();

		BigDecimal taxes();

	}

	// some implementation
	class SimpleMegacorp implements Megacorp {

		private final String name;
		private final BigDecimal earnings;
		private final BigDecimal taxes;

		SimpleMegacorp(String name, BigDecimal earnings, BigDecimal taxes) {
			this.name = name;
			this.earnings = earnings;
			this.taxes = taxes;
		}

		public SimpleMegacorp(Megacorp megacorp) {
			this(megacorp.name(), megacorp.earnings(), megacorp.taxes());
		}

		@Override
		public String name() {
			return name;
		}

		@Override
		public BigDecimal earnings() {
			return earnings;
		}

		@Override
		public BigDecimal taxes() {
			return taxes;
		}

	}

}
