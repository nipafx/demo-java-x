package org.codefx.demo.java11.api.http2;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Http2Api {

	private static final HttpClient CLIENT = HttpClient.newBuilder().build();

	private static final List<URI> URLS = Stream.of(
			"https://en.wikipedia.org/wiki/List_of_compositions_by_Franz_Schubert",
			"https://en.wikipedia.org/wiki/2018_in_American_television",
			"https://en.wikipedia.org/wiki/List_of_compositions_by_Johann_Sebastian_Bach",
			"https://en.wikipedia.org/wiki/List_of_Australian_treaties",
			"https://en.wikipedia.org/wiki/2016%E2%80%9317_Coupe_de_France_Preliminary_Rounds",
			"https://en.wikipedia.org/wiki/Timeline_of_the_war_in_Donbass_(April%E2%80%93June_2018)",
			"https://en.wikipedia.org/wiki/List_of_giant_squid_specimens_and_sightings",
			"https://en.wikipedia.org/wiki/List_of_members_of_the_Lok_Sabha_(1952%E2%80%93present)",
			"https://en.wikipedia.org/wiki/1919_New_Year_Honours",
			"https://en.wikipedia.org/wiki/List_of_International_Organization_for_Standardization_standards"
	).map(URI::create).collect(toList());

	private static final String SEARCH_TERM = "Foo";

	public static void main(String[] args) {
		blockingSearch(CLIENT, URLS, SEARCH_TERM);
		asyncSearch(CLIENT, URLS, SEARCH_TERM);
	}

	public static void blockingSearch(HttpClient client, List<URI> urls, String term) {
		urls.forEach(url -> {
			boolean found = blockingSearch(client, url, term);
			System.out.println("   [DEBUG] Completed " + url + " / found: " + found);
		});
	}

	private static boolean blockingSearch(HttpClient client, URI url, String term) {
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.GET()
					.uri(url)
					.build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			return response.body().contains(term);
		} catch (IOException | InterruptedException ex) {
			return false;
		}
	}

	private static void asyncSearch(HttpClient client, List<URI> urls, String searchTerm) {
		CompletableFuture[] futures = urls.stream()
				.map(url -> asyncSearch(client, url, searchTerm))
				.toArray(CompletableFuture[]::new);
		CompletableFuture.allOf(futures).join();
	}

	private static CompletableFuture<Void> asyncSearch(HttpClient client, URI url, String term) {
		HttpRequest request = HttpRequest.newBuilder()
				.GET()
				.uri(url)
				.build();
		return client
				.sendAsync(request, BodyHandlers.ofString())
				.thenApply(HttpResponse::body)
				.thenApply(body -> body.contains(term))
				.thenAccept(found ->
						System.out.println("   [DEBUG] Completed " + url + " / found: " + found));
	}

}
