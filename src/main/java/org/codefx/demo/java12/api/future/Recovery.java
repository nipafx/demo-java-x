package org.codefx.demo.java12.api.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class Recovery {

	public static void main(String[] args) {
		CompletableFuture<String> future = new CompletableFuture<>();
		future
				// execute `recover` in arbitrary thread
				.exceptionallyCompose(Recovery::recover)
				// commit `recover` to default executor
				.exceptionallyComposeAsync(Recovery::recover)
				// commit `recover` to specified executor
				.exceptionallyComposeAsync(Recovery::recover, Executors.newScheduledThreadPool(4));
		future.completeExceptionally(new RuntimeException("Error message"));
	}

	private static CompletableFuture<String> recover(Throwable ex) {
		return CompletableFuture.completedFuture(ex.getMessage());
	}

}
