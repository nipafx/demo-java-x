package org.codefx.demo.java17.api.random;

import jdk.internal.util.random.RandomSupport.RandomGeneratorProperties;

import java.util.random.RandomGenerator.ArbitrarilyJumpableGenerator;

/*
 * It's possible to provide custom implementations of the generator interfaces by
 * providing them as a service (see module declaration). Out of the box, that works
 * with `RandomGenerator::of` (and on the other generator interfaces).
 *
 * By using the JDK-internal annotation `@RandomGeneratorProperties` (and possibly
 * opening this package to java.base), it also works with `RandomGeneratorFactory`,
 * but the integration mechanism isn't meant for public use.
 */

@RandomGeneratorProperties(
		name="xkcd",
		group="fancypants",
		equidistribution = 0,
		i = 1,
		k = -1
)
public class Xkcd implements ArbitrarilyJumpableGenerator {

	@Override
	public ArbitrarilyJumpableGenerator copy() {
		return this;
	}

	@Override
	public double jumpDistance() {
		return Math.pow(2, 64);
	}

	@Override
	public double leapDistance() {
		return Math.pow(2, 128);
	}

	@Override
	public void jumpPowerOfTwo(int logDistance) { }

	@Override
	public void jump(double distance) {	}

	@Override
	public long nextLong() {
		return 4;
	}

	@Override
	public String toString() {
		return "XKCD Random Number Generator - chosen by fair dice roll";
	}

}
