package org.codefx.demo.java10.lang.var;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class IntersectionTypes {

	public static void main(String[] args) throws Exception {
		boolean empty = ThreadLocalRandom.current().nextBoolean();
		doItWithGenerics(empty);
		doItWithVar(empty);
	}

	private static <T extends Closeable & Iterator<String>> void doItWithGenerics(boolean empty) throws IOException {
		T elements = createCloseableIterator(empty);
		Optional<String> dollarWord = firstMatch(elements, s -> s.startsWith("$"));
		System.out.println(dollarWord.orElse("none"));
	}

	private static void doItWithVar(boolean empty) throws IOException {
		var scanner = createCloseableIterator(empty);
		Optional<String> dollarWord = firstMatch(scanner, s -> s.startsWith("$"));
		System.out.println(dollarWord.orElse("none"));
	}

	@SuppressWarnings("unchecked")
	private static <T extends Closeable & Iterator<String>> T createCloseableIterator(boolean empty) {
		if (empty)
			return (T) new Empty();
		else
			return (T) new Scanner(System.in);
	}

	private static <E, T extends Closeable & Iterator<E>> Optional<E> firstMatch(
			T elements, Predicate<? super E> condition)
			throws IOException {
		try (elements) {
			return stream(elements)
					.filter(condition)
					.findAny();
		}
	}

	private static <E> Stream<E> stream(Iterator<E> elements) {
		return StreamSupport.stream(((Iterable<E>) () -> elements).spliterator(), false);
	}

	static class Empty<E> implements Closeable, Iterator<E> {

		@Override
		public void close() throws IOException {

		}

		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public E next() {
			throw new IllegalStateException();
		}
	}

}
