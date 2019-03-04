package org.codefx.demo.java12.api.stream;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.averagingInt;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.minBy;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.teeing;

public class TeeingCollector {

	public static void main(String[] args) {
		collectToStats();
		collectToRange();
	}

	private static void collectToStats() {
		Statistics stats = Stream
				.of(1, 2, 4, 5)
				.collect(teeing(
						summingInt(i -> i),
						averagingInt(i -> i),
						Statistics::of));
		System.out.println(stats);
	}

	private static void collectToRange() {
		Range<String> range = Stream
				.of("A", "B", "C", "E")
				.collect(teeing(
						minBy(String::compareTo),
						maxBy(String::compareTo),
						Range::ofOptional))
				.orElseThrow(() -> new IllegalStateException("Non-empty stream was empty."));
		System.out.println(range);
	}

	private static void collectToRangeWithoutTeeing() {
		Range<Integer> range = Stream
				.of(1, 8, 2, 5)
				.reduce(
						// the initial range - parameters are `min` and `max`
						// in that order, so this range is empty
						Range.of(Integer.MAX_VALUE, Integer.MIN_VALUE),
						// combining an existing range with the next number from the stream
						(_range, number) -> {
							int newMin = Math.min(number, _range.min());
							int newMax = Math.max(number, _range.max());
							return Range.of(newMin, newMax);
						},
						// combining two ranges (needed at the end of a parallel stream)
						(_range1, _range2) -> {
							int newMin = Math.min(_range1.min(), _range2.min());
							int newMax = Math.max(_range1.max(), _range2.max());
							return Range.of(newMin, newMax);
						});
		System.out.println(range);
	}

	public static class Statistics {

		private final int sum;
		private final double average;

		private Statistics(int sum, double average) {
			this.sum = sum;
			this.average = average;
		}

		public static Statistics of(int sum, double average) {
			return new Statistics(sum, average);
		}

		public int sum() {
			return sum;
		}

		public double average() {
			return average;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Statistics that = (Statistics) o;
			return sum == that.sum &&
					Double.compare(that.average, average) == 0;
		}

		@Override
		public int hashCode() {
			return Objects.hash(sum, average);
		}

		@Override
		public String toString() {
			return "Statistics {" +
					"sum=" + sum +
					", average=" + average +
					'}';
		}

	}

	public static class Range<T> {

		private final T min;
		private final T max;

		private Range(T min, T max) {
			this.min = requireNonNull(min);
			this.max = requireNonNull(max);
		}

		public static <T> Range<T> of(T min, T max) {
			return new Range<T>(min, max);
		}

		public static <T> Optional<Range<T>> ofOptional(Optional<T> min, Optional<T> max) {
			if (min.isEmpty() || max.isEmpty())
				return Optional.empty();
			return Optional.of(new Range<T>(min.get(), max.get()));
		}

		public T min() {
			return min;
		}

		public T max() {
			return max;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Range<?> range = (Range<?>) o;
			return min.equals(range.min) &&
					max.equals(range.max);
		}

		@Override
		public int hashCode() {
			return Objects.hash(min, max);
		}

		@Override
		public String toString() {
			return "Range [" + min + ", " + max + ']';
		}

	}

}
