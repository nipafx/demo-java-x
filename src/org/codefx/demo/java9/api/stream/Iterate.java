package org.codefx.demo.java9.api.stream;

import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class Iterate {

	public static void main(String[] args) {
		Iterate iterate = new Iterate();
		iterate.withIterator();
		iterate.withExplicitIterator();
		iterate.withEnumerator();
		iterate.withStreamIterate();
	}

	public void withIterator() {
		System.out.println("ITERATOR");
		StringTokenizer tokens = new StringTokenizer("A B C D");

		for (Object token : (Iterable) tokens::asIterator)
			System.out.println("body: " + token);
	}

	public void withExplicitIterator() {
		System.out.println("ITERATOR EXPLICIT");
		StringTokenizer tokens = new StringTokenizer("A B C D");
		Iterator<Object> tokenIterator = tokens.asIterator();

		for (Object el = tokenIterator.next(); tokenIterator.hasNext(); el = tokenIterator.next())
			System.out.println("body: " + el);
	}

	public void withEnumerator() {
		System.out.println("ENUMERATOR");
		StringTokenizer tokens = new StringTokenizer("A B C D");

		for (; tokens.hasMoreElements(); )
			System.out.println("body: " + tokens.nextElement());
	}

	public void withStreamIterate() {
		System.out.println("STREAM ITERATE");
		StringTokenizer tokens = new StringTokenizer("A B C D");

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

}
