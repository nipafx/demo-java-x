package dev.nipafx.demo.java12.api.files;

import dev.nipafx.demo.java11.api.http2.ReactivePost;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FilesMismatch {

	private static final Path ORIGINAL = new File(
			ReactivePost.class.getClassLoader().getResource("large.json").getPath()).toPath();
	private static final Path COPY = new File(
			ReactivePost.class.getClassLoader().getResource("copy-of-large.json").getPath()).toPath();
	private static final Path ALMOST_COPY = new File(
			ReactivePost.class.getClassLoader().getResource("almost-copy-of-large.json").getPath()).toPath();

	public static void main(String[] args) throws IOException {
		printMatch(ORIGINAL, COPY);
		printMatch(ORIGINAL, ALMOST_COPY);
	}

	private static void printMatch(Path path1, Path path2) throws IOException {
		long mismatchIndex = Files.mismatch(path1, path2);
		boolean match = mismatchIndex == -1;
		if (match)
			System.out.println("Files match");
		else
			System.out.println("Files first difference is at index " + mismatchIndex);
	}

}
