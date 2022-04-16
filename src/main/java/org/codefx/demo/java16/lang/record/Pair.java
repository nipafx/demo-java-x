package org.codefx.demo.java16.lang.record;

import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

// This `Pair` is a product type, namely "int ྾ int", i.e. its set of possible values
// is that of all ints paired with all other ints, e.g. (1, 5) and (-4524652, 986732479).
// As a consequence, if a function on ints has certain properties and is used to create a
// function on `Pair`s, the latter has the same properties. See below for an example.
public record Pair(int first, int second) {

	public static void main(String[] args) {
		// this "int ~> int" function is surjective (it maps to all ints) and injective
		// (to two ints are mapped to the same int). Taken together, that means it's
		// bijective, which is pretty cool. A big advantage of bijective functions is
		// that they can be reverted (i.e. "undone").
		IntUnaryOperator increment = i -> i == Integer.MAX_VALUE ? Integer.MIN_VALUE : ++i;

		// Since a `Pair` is "int ྾ int", `increment` can be used to create a function
		// "Pair ~> Pair" by applying it to each int. Because `increment` is bijective,
		// so is the new function.
		UnaryOperator<Pair> incrementPair = pair -> new Pair(increment.applyAsInt(pair.first), increment.applyAsInt(pair.second));

		System.out.println(increment.applyAsInt(0));
		System.out.println(increment.applyAsInt(Integer.MAX_VALUE));
		System.out.println(incrementPair.apply(new Pair(0, Integer.MAX_VALUE)));
	}

}
