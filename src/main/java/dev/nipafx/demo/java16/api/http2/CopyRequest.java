package dev.nipafx.demo.java16.api.http2;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;

public class CopyRequest {

	public static void main(String[] args) throws URISyntaxException {
		// given an existing request...
		HttpRequest existingRequest = HttpRequest
				.newBuilder(new URI("https://en.wikipedia.org/wiki/Cultural_impact_of_Madonna"))
				.header("Keep-Alive", "timeout=5, max=1000")
				.header("Cache-Control", "max-age=604800")
				.version(Version.HTTP_1_1)
				.build();

		// ...create a new one with the same settings except but for HTTP 2 and cache control header
		HttpRequest newRequest = HttpRequest
				.newBuilder(
						existingRequest,
						(key, value) -> !key.equals("Cache-Control"))
				.version(Version.HTTP_2)
				.build();
	}

}
