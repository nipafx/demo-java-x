package dev.nipafx.demo.java17.api.random;

import java.util.random.RandomGenerator;
import java.util.random.RandomGenerator.ArbitrarilyJumpableGenerator;
import java.util.random.RandomGeneratorFactory;
import java.util.stream.Stream;

public class RandomGeneratorFactories {

	// to run this, you need to add the following command line option:
	// --add-exports=java.base/jdk.internal.util.random=dev.nipafx.demo.java_x
	public static void main(String[] args) {
		RandomGenerator with128StateBits = createWithAtLeastStateBits(128);

		System.out.println("ARBITRARILY JUMPABLE GENERATORS:");
		// if everything went well, this should show the custom XKCD generator,
		// which is integrated with the generator factory API
		createArbitrarilyJumpableGenerators()
				.forEach(g -> System.out.println(" - " + g.getClass().getName()));

		System.out.println("STOCHASTIC GENERATORS:");
		createStochasticGenerators()
				.forEach(g -> System.out.println(" - " + g.getClass().getName()));
	}

	/*
	 * The casts aren't ideal. If you don't want to call the interface-specific
	 * methods on the created instances, they're not needed.
	 */

	private static RandomGenerator createWithAtLeastStateBits(int stateBits) {
		return RandomGeneratorFactory.all()
				.filter(factory -> factory.stateBits() >= stateBits)
				.findAny()
				.map(RandomGeneratorFactory::create)
				.orElseThrow();
	}


	private static Stream<ArbitrarilyJumpableGenerator> createArbitrarilyJumpableGenerators() {
		return RandomGeneratorFactory.all()
				.filter(RandomGeneratorFactory::isArbitrarilyJumpable)
				.map(RandomGeneratorFactory::create)
				.map(ArbitrarilyJumpableGenerator.class::cast);
	}

	private static Stream<RandomGenerator> createStochasticGenerators() {
		return RandomGeneratorFactory.all()
				.filter(RandomGeneratorFactory::isStochastic)
				.map(RandomGeneratorFactory::create);
	}

}
