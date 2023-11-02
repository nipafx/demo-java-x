package dev.nipafx.demo.java_next.api.gather;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* --- UNTIL JEP 461 IS MERGED --- */
import dev.nipafx.demo.java_next.api.gather.stream.Gatherer;
import dev.nipafx.demo.java_next.api.gather.stream.Gatherer.Downstream;
import dev.nipafx.demo.java_next.api.gather.stream.Gatherer.Integrator;

/* --- AFTER JEP 461 IS MERGED --- */
//import java.util.stream.Gatherer;
//import java.util.stream.Gatherer.Downstream;
//import java.util.stream.Gatherer.Integrator;

import static java.lang.StringTemplate.STR;

/**
 * This demo is built for JEP 461, which is not yet merged. To allow experimentation, I rebuilt the essential parts:
 *
 * <ul>
 * 	<li>the interface hierarchy in {@link dev.nipafx.demo.java_next.api.gather.stream.Gatherer}</li>
 *  <li>the iteration mechanism in {@code apply_manually} (without any guarantee of correctness</li>
 * </ul>
 *
 * The code should work on any recent Java version as is.
 */
public class CustomGatherers {

	public static void main(String[] args) {
		var letters = List.of("A", "B", "D", "C", "B", "F", "E");
		Predicate<String> isEven = letter -> ((int) letter.charAt(0)) % 2 == 0;

		/* --- UNTIL JEP 461 IS MERGED --- */
		var result = apply_manually(
				letters,
				doNothing());
		/* --- AFTER JEP 461 IS MERGED --- */
//		var result = apply_jep461(
//				letters,
//				doNothing());

		System.out.println(STR."""

				in:  \{letters}
				out: \{result}
			""");
	}

	/* --- UNTIL JEP 461 IS MERGED --- */
	private static <R> List<R> apply_manually(List<String> letters, Gatherer<String, ?, R> gatherer) {
		// the raw type is needed because the compiler won't let us pass a `?` to a `?`
		var rawGatherer = (Gatherer) gatherer;
		var result = new ArrayList<R>();
		Downstream<? super R> downstream = result::add;

		Object state = gatherer.initializer().get();
		boolean integrateMore = true;
		var iterator = letters.iterator();
		while (integrateMore && iterator.hasNext())
			integrateMore = rawGatherer.integrator().integrate(state, iterator.next(), downstream);
		rawGatherer.finisher().accept(state, downstream);

		return result;
	}

	/* --- AFTER JEP 461 IS MERGED --- */
//	private static <R> List<R> apply_jep461(List<String> letters, Gatherer<String,?, R> gatherer) {
//		return letters.stream()
//				.gather(gatherer)
//				.toList();
//	}

	public static <T> Gatherer<T, ?, T> doNothing() {
		Integrator<Void, T, T> integrator = (_, element, downstream) -> {
			downstream.push(element);
			return true;
		};
		return Gatherer.of(integrator);
	}

	public static <T, R> Gatherer<T, ?, R> map(
			Function<? super T, ? extends R> mapper) {
		Integrator<Void, T, R> integrator = (_, element, downstream) -> {
			R newElement = mapper.apply(element);
			downstream.push(newElement);
			return true;
		};
		return Gatherer.of(integrator);
	}

	public static <T> Gatherer<T, ?, T> filter(
			Predicate<? super T> filter) {
		Integrator<Void, T, T> integrator = (_, element, downstream) -> {
			var passOn = filter.test(element);
			if (passOn)
				downstream.push(element);
			return true;
		};
		return Gatherer.of(integrator);
	}

	public static <T> Gatherer<T, ?, T> flatMapIf(
			Predicate<? super T> test,
			Function<? super T, Stream<? extends T>> mapper) {
		Integrator<Void, T, T> integrator = (_, element, downstream) -> {
			var expand = test.test(element);
			if (expand)
				mapper.apply(element).forEach(downstream::push);
			else
				downstream.push(element);
			return true;
		};
		return Gatherer.of(integrator);
	}

	public static <T> Gatherer<T, ?, T> takeWhileIncluding(
			Predicate<? super T> predicate) {
		Integrator<Void, T, T> integrator = (_, element, downstream) -> {
			downstream.push(element);
			return predicate.test(element);
		};
		return Gatherer.of(integrator);
	}

	public static <T> Gatherer<T, ?, T> limit(int numberOfElements) {
		Supplier<AtomicInteger> initializer = AtomicInteger::new;
		Integrator<AtomicInteger, T, T> integrator = (state, element, downstream) -> {
			var currentIndex = state.getAndIncrement();
			if (currentIndex < numberOfElements)
				downstream.push(element);
			return currentIndex + 1 < numberOfElements;
		};
		return Gatherer.ofSequential(initializer, integrator);
	}

	public static <T> Gatherer<T, ?, T> increasing(Comparator<T> comparator) {
		Supplier<AtomicReference<T>> initializer = AtomicReference::new;
		Integrator<AtomicReference<T>, T, T> integrator = (state, element, downstream) -> {
			T largest = state.get();
			var isLarger = largest == null || comparator.compare(element, largest) > 0;
			if (isLarger) {
				downstream.push(element);
				state.set(element);
			}
			return true;
		};
		return Gatherer.ofSequential(initializer, integrator);
	}

	public static Gatherer<Integer, ?, Double> runningAverage() {
		class State {

			private long sum;
			private long count;

		}
		Supplier<State> initializer = State::new;
		Integrator<State, Integer, Double> integrator = (state, element, downstream) -> {
			state.sum += element;
			state.count++;
			double average = (double) state.sum / state.count;
			downstream.push(average);
			return true;
		};
		return Gatherer.ofSequential(initializer, integrator);
	}

	public static <T> Gatherer<T, ?, List<T>> slidingWindow(int size) {
		Supplier<List<T>> initializer = ArrayList::new;
		Integrator<List<T>, T, List<T>> integrator = (state, element, downstream) -> {
			state.addFirst(element);
			if (state.size() > size) {
				state.removeLast();
			}
			var group = List.copyOf(state);
			downstream.push(group);
			return true;
		};
		return Gatherer.ofSequential(initializer, integrator);
	}

	public static <T> Gatherer<T, ?, List<T>> fixedGroups(int size) {
		Supplier<List<T>> initializer = ArrayList::new;
		Integrator<List<T>, T, List<T>> integrator = (state, element, downstream) -> {
			state.add(element);
			if (state.size() == size) {
				var group = List.copyOf(state);
				downstream.push(group);
				state.clear();
			}
			return true;
		};
		BiConsumer<List<T>, Downstream<? super List<T>>> finisher = (state, downstream) -> {
			var group = List.copyOf(state);
			downstream.push(group);
		};
		return Gatherer.ofSequential(initializer, integrator, finisher);
	}

	public static <T> Gatherer<T, ?, T> sorted(Comparator<? super T> comparator) {
		Supplier<List<T>> initializer = ArrayList::new;
		Integrator<List<T>, T, T> integrator = (state, element, _) -> {
			state.add(element);
			return true;
		};
		BiConsumer<List<T>, Downstream<? super T>> finisher = (state, downstream) -> {
			state.sort(comparator);
			state.forEach(downstream::push);
		};
		return Gatherer.ofSequential(initializer, integrator, finisher);
	}

	public static <T> Gatherer<T, ?, List<T>> increasingSequences(Comparator<T> comparator) {
		Supplier<List<T>> initializer = ArrayList::new;
		Integrator<List<T>, T, List<T>> integrator = (state, element, downstream) -> {
			boolean isInSequence = state.isEmpty()
					|| comparator.compare(element, state.getLast()) >= 0;
			if (!isInSequence) {
				var group = List.copyOf(state);
				downstream.push(group);
				state.clear();
			}
			state.addLast(element);
			return true;
		};
		BiConsumer<List<T>, Downstream<? super List<T>>> finisher = (state, downstream) -> {
			var group = List.copyOf(state);
			downstream.push(group);
		};
		return Gatherer.ofSequential(initializer, integrator, finisher);
	}

}
