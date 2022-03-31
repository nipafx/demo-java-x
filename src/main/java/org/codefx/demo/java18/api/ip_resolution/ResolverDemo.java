package org.codefx.demo.java18.api.ip_resolution;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

import static java.net.http.HttpResponse.BodyHandlers.ofLines;

/**
 * This demo sends a simple request to a website. If the project is wired up
 * correctly, the address resolution machinery picks up the other two classes
 * in this package and uses them for resolution, in which case you should see
 * a log message like "Looking up ..." on the command line for each request.
 */
public class ResolverDemo {

	public static void main(String[] args) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest
				.newBuilder(new URI("https://nipafx.dev"))
				.GET()
				.build();
		client
				.send(request, ofLines())
				.body()
				.limit(5)
				.forEach(System.out::println);
	}

}
