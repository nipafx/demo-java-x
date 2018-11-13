package org.codefx.demo.java11.api.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;

public class Null_IO {

	public static void main(String[] args) throws IOException {
		inputStream();
		outputStream();
		reader();
		writer();
	}

	private static void inputStream() throws IOException {
		InputStream input = InputStream.nullInputStream();
		System.out.println("Input stream content length: " + input.readAllBytes().length);
	}

	private static void outputStream() throws IOException {
		OutputStream output = OutputStream.nullOutputStream();
		output.write("This is going nowhere...".getBytes());
	}

	private static void reader() throws IOException {
		Reader reader = Reader.nullReader();
		char[] buffer = { '_', '_', '_' };
		var ended = reader.read(buffer) == -1;
		System.out.print("Buffer content:");
		for (char c : buffer) {
			System.out.print(" " + c);
		}
		System.out.println("\nStream ended? " + ended);
	}

	private static void writer() throws IOException {
		Writer writer = Writer.nullWriter();
		writer.write("This is going nowhere...");
	}

}
