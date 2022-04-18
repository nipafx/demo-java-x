package dev.nipafx.demo.java9.runtime.string;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class CompactionPerformance {

	// To play around with this:
	//  (1) build the project with `mvn package`
	//  (2) run with either of the following lines
	//
	// WITH COMPACT STRINGS (enabled by default):
	// 	java -p target/java-x.jar -m dev.nipafx.demo.java_x/dev.nipafx.demo.java9.runtime.string.CompactionPerformance
	//
	// WITHOUT COMPACT STRINGS:
	// 	java -p target/java-x.jar -m dev.nipafx.demo.java_x/dev.nipafx.demo.java9.runtime.string.CompactionPerformance
	//
	// This is just for fun! Use JMH for a serious/reliable performance examination.

	public static void main(String[] args) throws InterruptedException {
		// time to connect profiler (at least JVisualVM doesn't want to :( )
//		Thread.sleep(5_000);

		long launchTime = System.currentTimeMillis();
		List<String> strings = IntStream.rangeClosed(1, 10_000_000)
				.mapToObj(Integer::toString)
				.collect(toList());
		long runTime = System.currentTimeMillis() - launchTime;
		System.out.println("Generated " + strings.size() + " strings in " + runTime + " ms.");

		launchTime = System.currentTimeMillis();
		String appended = strings.stream()
				.limit(100_000)
				.reduce("", (left, right) -> left + right);
		runTime = System.currentTimeMillis() - launchTime;
		System.out.println("Created string of length " + appended.length() + " in " + runTime + " ms.");
	}

}
