package org.codefx.demo.java11.api.http2.formalized;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class AsyncPageSearch implements PageSearch {

	private final HttpClient client;

	public AsyncPageSearch(HttpClient client) {
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
		return client
				.sendAsync(request, BodyHandlers.ofString())
				.thenApply(HttpResponse::body)
				.thenApply(body -> body.contains(search.term()))
				.thenApply(contains -> Result.completed(search,contains))
				.exceptionally(ex -> Result.failed(search, ex));
	}

	// this may come in handy (you never know)
	private Result getUnsafely(CompletableFuture<Result> result) {
		try {
			return result.get();
		} catch (ExecutionException | InterruptedException ex) {
			throw new IllegalStateException("Future should have completed and handled errors.", ex);
		}
	}

}
