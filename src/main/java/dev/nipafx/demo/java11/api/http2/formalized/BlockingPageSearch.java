package dev.nipafx.demo.java11.api.http2.formalized;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class BlockingPageSearch implements PageSearch {

	private final HttpClient client;

	public BlockingPageSearch(HttpClient client) {
		this.client = client;
	}

	@Override
	public List<Result> search(List<Search> searches) {
		return searches.stream()
				.map(this::search)
				.collect(toList());
	}

	private Result search(Search search) {
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.GET()
					.uri(search.url())
					.build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			boolean contains = response.body().contains(search.term());
			return Result.completed(search, contains);
		} catch (IOException | InterruptedException ex) {
			return Result.failed(search, ex);
		}
	}

}
