package dev.nipafx.demo.java9.api.reactive_streams;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.Executors.newSingleThreadExecutor;

/**
 * A publisher that produces a potentially infinite stream of consecutive positive integers.
 * <br>
 * The publisher makes a best effort to start each subscription's (partial) series
 * with the smallest value that has not yet been requested by all active subscribers.
 * (This item could be seen to be the oldest that was not yet processed by all existing
 * subscriptions.)
 * <br>
 * Once the last subscription is cancelled, the publisher will cease to accept new
 * subscriptions - {@link #waitUntilTerminated()} will terminate soon after, once
 * all remaining items were sent out.
 */
public class IncrementingPublisher implements Publisher<Integer> {

	private final ExecutorService executor = Executors.newFixedThreadPool(4);
	private final Set<Sub> subscriptions = Collections.newSetFromMap(new ConcurrentHashMap<>());
	private final AtomicInteger subscriptionCount = new AtomicInteger();
	private final CompletableFuture<Void> terminated = new CompletableFuture<>();

	@Override
	public void subscribe(Subscriber<? super Integer> subscriber) {
		Sub subscription = createNewSubscriptionFor(subscriber);
		registerSubscription(subscription);
		subscriber.onSubscribe(subscription);
	}

	private Sub createNewSubscriptionFor(Subscriber<? super Integer> subscriber) {
		int startValue = subscriptions.stream()
				.mapToInt(sub -> sub.nextValue.get())
				.min()
				.orElse(0);
		return new Sub(subscriber, startValue);
	}

	private void registerSubscription(Sub subscription) {
		subscriptions.add(subscription);
		subscriptionCount.incrementAndGet();
	}

	private boolean unregisterSubscriptionAndCheckIfLast(Sub subscription) {
		subscriptions.remove(subscription);
		return subscriptionCount.decrementAndGet() == 0;
	}

	private void shutdown() {
		System.out.println("Shutting down executor service...");
		executor.shutdown();
		newSingleThreadExecutor().submit(() -> {
			try {
				executor.awaitTermination(0, TimeUnit.SECONDS);
			} catch (InterruptedException ex) {
				// if waiting gets interrupted, we simply declare the publisher
				// to be terminated
			}
			System.out.println("Shutdown complete.");
			terminated.complete(null);
		});
	}

	public void waitUntilTerminated() throws InterruptedException {
		try {
			terminated.get();
		} catch (ExecutionException ex) {
			// even if something went wrong - the computation is terminated all the same
			System.out.println(ex);
		}
	}

	private class Sub implements Subscription {

		private final Subscriber<? super Integer> subscriber;
		private final AtomicInteger nextValue;
		private final AtomicBoolean canceled;

		public Sub(Subscriber<? super Integer> subscriber, int startValue) {
			this.subscriber = subscriber;
			this.nextValue = new AtomicInteger(startValue);
			this.canceled = new AtomicBoolean(false);
		}

		@Override
		public void request(long n) {
			if (canceled.get())
				return;

			if (n < 0)
				reportIllegalArgument();
			else
				publishItems(n);
		}

		private void reportIllegalArgument() {
			executor.execute(() -> subscriber.onError(new IllegalArgumentException()));
		}

		private void publishItems(long n) {
			for (long i = n; i > 0; i--)
				executor.execute(() -> subscriber.onNext(nextValue.getAndIncrement()));
		}

		@Override
		public void cancel() {
			canceled.set(true);
			// we do not cancel already requested items;
			// instead we unregister check whether we want to shut down
			boolean wasLast = unregisterSubscriptionAndCheckIfLast(this);
			if (wasLast)
				shutdown();
		}
	}
}
