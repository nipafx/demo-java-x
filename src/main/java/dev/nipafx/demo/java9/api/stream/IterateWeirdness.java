package dev.nipafx.demo.java9.api.stream;

import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class IterateWeirdness {

	public static void main(String[] args) {
		IterateWeirdness iterate = new IterateWeirdness();
		iterate.withIterator();
		iterate.withExplicitIterator();
		iterate.whileWithEnumerator();
		iterate.forWithEnumerator();
		iterate.withStreamIterate();
	}

	public void withIterator() {
		System.out.println("ITERATOR");
		StringTokenizer tokens = createTokenizer();

		for (Object token : (Iterable) tokens::asIterator)
			System.out.println("body: " + token);
	}

	public void withExplicitIterator() {
		System.out.println("ITERATOR EXPLICIT");
		StringTokenizer tokens = createTokenizer();
		Iterator<Object> tokenIterator = tokens.asIterator();

		for (Object el = tokenIterator.next(); tokenIterator.hasNext(); el = tokenIterator.next())
			System.out.println("body: " + el);
	}

	public void whileWithEnumerator() {
		System.out.println("ENUMERATOR WHILE");
		StringTokenizer tokens = createTokenizer();

		while (tokens.hasMoreElements())
			System.out.println("body: " + tokens.nextElement());
	}

	public void forWithEnumerator() {
		System.out.println("ENUMERATOR FOR");
		StringTokenizer tokens = createTokenizer();

		for (Object el = tokens.nextElement(); tokens.hasMoreElements(); el = tokens.nextElement())
			System.out.println("body: " + el);
	}

	public void withStreamIterate() {
		System.out.println("STREAM ITERATE");
		StringTokenizer tokens = createTokenizer();

		Object seed = tokens.nextElement();
		Predicate<Object> predicate = element -> {
			System.out.println("has more: " + tokens.hasMoreElements());
			return tokens.hasMoreElements();
		};
		UnaryOperator<Object> operator = element -> {
			System.out.println("ask for next");
			return tokens.nextElement();
		};

		Stream.iterate(seed, predicate, operator).forEach(el -> System.out.println("body: " + el));
	}

	private static StringTokenizer createTokenizer() {
		return new StringTokenizer("A B C");
	}

}
