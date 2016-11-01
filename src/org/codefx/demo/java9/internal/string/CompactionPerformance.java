package org.codefx.demo.java9.internal.string;

import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class CompactionPerformance {

	// To play around with this:
	//  (1) compile using `compile.sh`
	//  (2) run with either of the following lines
	//
	// WITH COMPACT STRINGS (enabled by default):
	// 	$JAVA9_HOME/bin/java -cp out org.codefx.demo.java9.internal.string.CompactionPerformance
	//
	// WITHOUT COMPACT STRINGS:
	// 	$JAVA9_HOME/bin/java -cp out -XX:-CompactStrings org.codefx.demo.java9.internal.string.CompactionPerformance
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
