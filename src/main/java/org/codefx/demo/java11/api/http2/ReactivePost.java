package org.codefx.demo.java11.api.http2;

import java.io.File;
import java.io.IOException;
import java.lang.StackWalker.StackFrame;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class ReactivePost {

	private static final URI POSTMAN_POST = URI.create("https://postman-echo.com/post");
	private static final Path LARGE_JSON = new File(
			ReactivePost.class.getClassLoader().getResource("large.json").getPath()).toPath();

	public static void main(String[] args) throws IOException, InterruptedException {
		HttpClient client = HttpClient.newBuilder().build();
		HttpRequest post = HttpRequest
				.newBuilder(POSTMAN_POST)
				.POST(new LoggingBodyPublisher(BodyPublishers.ofFile(LARGE_JSON)))
				.header("Content-Type", "application/json")
				.build();
		HttpResponse<String> response = client
				.send(post, BodyHandlers.ofString());

		System.out.println("Status: " + response.statusCode());
		System.out.println(response.body());
	}

	private static class LoggingBodyPublisher implements BodyPublisher {

		private final BodyPublisher publisher;

		private LoggingBodyPublisher(BodyPublisher publisher) {
			this.publisher = publisher;
		}

		@Override
		public long contentLength() {
			var contentLength = publisher.contentLength();
			log("Content length queried: " + contentLength);
			return contentLength;
		}

		@Override
		public void subscribe(Subscriber<? super ByteBuffer> subscriber) {
			log("Subscriber registered: " + subscriber);
			publisher.subscribe(new LoggingSubscriber(subscriber));
		}

	}

	private static class LoggingSubscriber implements Subscriber<ByteBuffer> {

		private final Subscriber<? super ByteBuffer> subscriber;
		private long totalBytesPassed = 0;

		private LoggingSubscriber(Subscriber<? super ByteBuffer> subscriber) {
			this.subscriber = subscriber;
		}

		@Override
		public void onSubscribe(Subscription subscription) {
			log("Subscription registered: " + subscription);
			subscriber.onSubscribe(new LoggingSubscription(subscription));
		}

		@Override
		public void onNext(ByteBuffer item) {
			int passed = item.array().length;
			totalBytesPassed += passed;
			log("Bytes passed: " + passed + " ↺ " + totalBytesPassed);
			subscriber.onNext(item);
		}

		@Override
		public void onError(Throwable throwable) {
			log("Error occured: " + throwable);
			subscriber.onError(throwable);
		}

		@Override
		public void onComplete() {
			log("Publishing completed");
			subscriber.onComplete();
		}

	}

	private static class LoggingSubscription implements Subscription {

		private final Subscription subscription;
		private long totalRequestedItems = 0;

		private LoggingSubscription(Subscription subscription) {
			this.subscription = subscription;
		}

		@Override
		public void request(long n) {
			totalRequestedItems += n;
			log("Items requested: " + n + " ↺ " + totalRequestedItems);
			subscription.request(n);
		}

		@Override
		public void cancel() {
			log("Subscription canceled.");
			subscription.cancel();
		}

	}

	private static void log(String message) {
		StackFrame caller = StackWalker.getInstance().walk(frames -> frames.skip(1).findFirst().orElseThrow());
		System.out.printf("    [DEBUG | %-12s] %s%n", caller.getMethodName(), message);
	}

}
