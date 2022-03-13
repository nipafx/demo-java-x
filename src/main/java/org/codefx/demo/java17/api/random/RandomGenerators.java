package org.codefx.demo.java17.api.random;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.SplittableRandom;
import java.util.concurrent.ThreadLocalRandom;
import java.util.random.RandomGenerator;
import java.util.random.RandomGenerator.JumpableGenerator;
import java.util.random.RandomGenerator.LeapableGenerator;
import java.util.random.RandomGenerator.SplittableGenerator;

public class RandomGenerators {

	public static void main(String[] args) {
		newInterface();
		simpleCreation();
		moreInterfaces();
	}

	private static void newInterface() {
		// `RandomGenerator` is a new interface that combines the APIs
		// of pre-existing random classes, which all implement it
		RandomGenerator random = new Random();
		RandomGenerator threadLocal = ThreadLocalRandom.current();
		RandomGenerator secure = new SecureRandom();
		RandomGenerator splittable = new SplittableRandom();
	}

	private static void simpleCreation() {
		// if you have no requirements
		var generator = RandomGenerator.getDefault();
		// if you know which algorithm you need
		// (be careful, this is fragile as algorithms can be removed;
		//  use RandomGeneratorFactory instead - see other demo class)
		// (for implemented algorithms, see Javadoc of java.util.random)
		var lxm = RandomGenerator.of("L128X128MixRandom");
		var xoshiro = RandomGenerator.of("Xoshiro256PlusPlus");
	}

	private static void moreInterfaces() {
		// There are many more new interfaces. They all extend `RandomGenerator`
		// and only differ in how you can create a new instance from an existing one.

		// The casts aren't ideal. If you don't want to call the interface-specific
		// methods on the newly-created instances, they're not needed.
		
		JumpableGenerator jumpable = JumpableGenerator.of("Xoroshiro128PlusPlus");
		JumpableGenerator newJumpable = (JumpableGenerator) jumpable.copyAndJump();

		LeapableGenerator leapable = LeapableGenerator.of("Xoshiro256PlusPlus");
		LeapableGenerator newLeapable = (LeapableGenerator) leapable.copyAndLeap();

		// there's no `ArbitrarilyJumpableGenerator` (in JDK 18)
//		ArbitrarilyJumpableGenerator arbitrarilyJumpable = ArbitrarilyJumpableGenerator.of("Xoshiro256PlusPlus");

		SplittableGenerator splittable = SplittableGenerator.of("L128X1024MixRandom");
		SplittableGenerator newSplittable = splittable.split();
	}

	private long randomPercentage(Random random) {
		// compile error on JDK <17 :( because there,
		// `nextLong(bound)` only exists on
		// `SplittableRandom` and `ThreadLocalRandom`
		return random.nextLong(101);
	}

	private record User() {

		// nay (don't pass specific classes)
		User randomUser(ArrayList<User> users, Random random) {
			int index = random.nextInt(users.size());
			return users.get(index);
		}

		// yay (instead pass the new interface)
		User randomUser(List<User> users, RandomGenerator random) {
			int index = random.nextInt(users.size());
			return users.get(index);
		}

	}


}
