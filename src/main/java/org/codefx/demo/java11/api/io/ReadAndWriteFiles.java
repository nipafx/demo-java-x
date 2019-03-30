package org.codefx.demo.java11.api.io;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.util.function.Predicate.not;

public class ReadAndWriteFiles {

	public static void main(String[] args) throws IOException {
		System.out.println(findHaiku());

		var haiku = findHaiku();
		Path target = findTarget(haiku);
		// the combination `readString` and `lines` doesn't make sense; that's
		// what `Files::lines` is there for (but I want to show `readString`!)
		Files.readString(haiku)
				.lines()
				.filter(not(String::isBlank))
				.forEach(line -> {
					try {
						// here we write each line to its own file
						Files.writeString(target.resolve(toFileName(line)), line);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				});
	}

	private static Path findHaiku() {
		var path = Path.of("").toAbsolutePath();
		while (!Files.exists(path.resolve("echo-haiku.txt")))
			path = path.getParent();
		return path.resolve("echo-haiku.txt");
	}

	private static Path findTarget(Path haiku) throws IOException {
		var target = haiku.getParent().resolve("target");
		try {
			Files.createDirectory(target);
		} catch (FileAlreadyExistsException e) {
			// this is ok; nothing to do
		}
		return target;
	}

	private static String toFileName(String line) {
		return line.replace(" ", "").substring(0, 8)
				+ "-" + (System.currentTimeMillis() % 10000)
				+ ".txt";
	}

}
