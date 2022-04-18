package dev.nipafx.demo.java11.api.io;

import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathOf {

	public static void main(String[] args) {
		// instead of `Path.get(String, String...)`
		Path tmp = Path.of("/home/nipa", "tmp");
		System.out.println(Files.exists(tmp));

		// instead of `Path.get(URI)`
		Path nipafx = Path.of(URI.create("https://nipafx.dev"));
	}

}
