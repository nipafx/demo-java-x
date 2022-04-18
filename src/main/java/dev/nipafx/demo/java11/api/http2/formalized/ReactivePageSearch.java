package dev.nipafx.demo.java11.api.http2.formalized;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class ReactivePageSearch implements PageSearch {

	private final HttpClient client;

	public ReactivePageSearch(HttpClient client) {
		this.client = client;
	}

	@Override
	public List<Result> search(List<Search> searches) {
		CompletableFuture[] futures = searches.stream()
				.map(search -> search(search))
				.toArray(CompletableFuture[]::new);

		CompletableFuture.allOf(futures).join();

		return Stream.of(futures)
				.map(this::getUnsafely)
				.collect(toList());
	}

	private CompletableFuture<Result> search(Search search) {
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.uri(search.url())
				.build();
		StringFinder finder = new StringFinder(search);
		client
				.sendAsync(request, BodyHandlers.fromLineSubscriber(finder))
				.exceptionally(ex -> {
					finder.onError(ex);
					return null;
				});
		return finder
				.found()
				.exceptionally(ex -> Result.failed(search, ex));
	}

	private Result getUnsafely(CompletableFuture<Result> result) {
		// this is unsafe because the future's exceptions are not properly handled;
		// it makes sense in this case because:
		//  (a) exceptional completion is covered by the the call to
		//      `exceptionally(...)` in `search(Search)`
		//  (b) the line `CompletableFuture.allOf(futures).join()` in
		//      `search(List<Search>)` means `get()` below can never block
		try {
			return result.get();
		} catch (ExecutionException | InterruptedException ex) {
			throw new IllegalStateException("Future should have completed and handled errors.", ex);
		}
	}

	private static class StringFinder implements Subscriber<String> {

		private final Search search;
		private final CompletableFuture<Result> found;
		private Subscription subscription;

		private StringFinder(Search search) {
			this.search = search;
			this.found = new CompletableFuture<>();
		}

		@Override
		public void onSubscribe(Subscription subscription) {
			this.subscription = subscription;
			requestLine();
		}

		private void requestLine() {
			subscription.request(1);
		}

		@Override
		public void onNext(String line) {
			// no cancelation
//			if (!found.isDone() && line.contains(search.term()))
//				found.complete(Result.completed(search, true));
			// with cancelation
			if (line.contains(search.term())) {
				found.complete(Result.completed(search, true));
				subscription.cancel();
			}

			requestLine();
		}

		@Override
		public void onError(Throwable ex) {
			found.completeExceptionally(ex);
		}

		@Override
		public void onComplete() {
			found.complete(Result.completed(search, false));
		}

		public CompletableFuture<Result> found() {
			return found;
		}

	}

}
