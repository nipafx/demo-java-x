package org.codefx.demo.java9.api.reactive_streams;

import java.util.Random;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.currentThread;

/**
 * A subscriber that simply logs to the console whatever happens
 * and creates random delays on certain actions to simulate processing.
 */
public class LoggingRandomDelaySubscriber implements Subscriber<Object> {

	// <name> [<thread name>]: <message>
	private static final String LOG_MESSAGE_FORMAT = "%s [%s]: %s%n";
	private static final Random RANDOM = new Random();

	private final String name;

	private Subscription subscription;
	private AtomicInteger buffer;

	private LoggingRandomDelaySubscriber(String name) {
		this.name = name;
	}

	public static void createAndSubscribe(String name, Publisher<?> publisher) {
		publisher.subscribe(new LoggingRandomDelaySubscriber(name));
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		log("Subscribed...");
		this.subscription = subscription;
		this.buffer = new AtomicInteger();
		requestItems();
	}

	private void requestItems() {
		int requestedItemCount = RANDOM.nextInt(6) + 5;
		buffer.addAndGet(requestedItemCount);
		log("Requesting %d new items...", requestedItemCount);
		subscription.request(requestedItemCount);
	}

	private void cancel() {
		log("Cancelling subscription...");
		subscription.cancel();
	}

	@Override
	public void onNext(Object item) {
		boolean bufferAlmostEmpty = buffer.decrementAndGet() == 3;
		log("%s", item.toString());
		unsafeSleepRandomUpToMillis(25);
		if (bufferAlmostEmpty)
			requestMoreItemsOrCancelSubscription();
	}

	private void requestMoreItemsOrCancelSubscription() {
		if (RANDOM.nextBoolean())
			requestItems();
		else
			cancel();
	}

	@Override
	public void onError(Throwable throwable) {
		log("Error - %s", throwable);
	}

	@Override
	public void onComplete() {
		log("Completed!");
	}

	private void log(String message, Object... args) {
		String fullMessage = String.format(LOG_MESSAGE_FORMAT, name, currentThread().getName(), message);
		System.out.printf(fullMessage, args);
	}

	public static void unsafeSleepRandomUpToMillis(int maxMillis) {
		try {
			Thread.sleep(RANDOM.nextInt(maxMillis) + 1);
		} catch (InterruptedException ex) {
			// ignore
		}
	}

}
