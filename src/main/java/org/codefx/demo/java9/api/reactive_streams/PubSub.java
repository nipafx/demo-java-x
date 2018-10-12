package org.codefx.demo.java9.api.reactive_streams;

import java.util.stream.Stream;

import static org.codefx.demo.java9.api.reactive_streams.LoggingRandomDelaySubscriber.unsafeSleepRandomUpToMillis;

public class PubSub {

	/*
	 * Uses the new reactive stream interfaces to create a simple publisher/subscriber pipeline.
	 * Note that the JDK only defines a couple of basic interfaces (with the exception of
	 * SubmissionPublisher) for existing reactive stream libraries to integrate with.
	 */

	public static void main(String[] args) throws InterruptedException {
		IncrementingPublisher publisher = new IncrementingPublisher();
		Stream.of("A", "B", "C")
				.peek(name -> unsafeSleepRandomUpToMillis(20))
				.forEach(name -> LoggingRandomDelaySubscriber.createAndSubscribe(name, publisher));
		publisher.waitUntilTerminated();
	}

}
