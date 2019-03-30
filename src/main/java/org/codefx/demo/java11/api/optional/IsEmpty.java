package org.codefx.demo.java11.api.optional;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;

public class IsEmpty {

	public static void main(String[] args) {
		// most of this is just tomfoolery...
		var counter = new AtomicLong();
		var stupidRandomByte = ThreadLocalRandom.current()
				.ints()
				.peek(__ -> counter.incrementAndGet())
				.filter(number -> 0 < number && number < 256)
				.findFirst();
		// here's the interesting bit
		if (stupidRandomByte.isEmpty())
			throw new IllegalStateException("Should be `IllegalUniverseException`...");
		else
			System.out.println("Found a random byte: " + stupidRandomByte.getAsInt());
		System.out.println("(Took about "  + (counter.get() / 1_000_000) + " million tries...)");
	}

}
