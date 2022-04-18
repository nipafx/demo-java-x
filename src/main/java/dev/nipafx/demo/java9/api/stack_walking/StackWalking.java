package dev.nipafx.demo.java9.api.stack_walking;

import java.lang.StackWalker.StackFrame;
import java.util.Arrays;
import java.util.stream.Stream;

class StackWalking {

	public static void main(String[] args) {
		oldWay();
		newWay();
	}

	static void oldWay() {
		StackTraceElement[] stackTrace = new Throwable().getStackTrace();
		String line = oldWalk(stackTrace);
		System.out.println(line);
	}

	private static String oldWalk(StackTraceElement[] stackTrace) {
		return Arrays.stream(stackTrace)
				.peek(frame -> System.out.println(frame.getMethodName()))
				.filter(frame -> frame.getMethodName().contains("oldWay"))
				.findFirst()
				.map(frame -> "Line " + frame.getLineNumber())
				.orElse("Unknown line");
	}

	static void newWay() {
		one();
	}

	static void one() {
		two();
	}

	static void two() {
		three();
	}

	static void three() {
		String line = StackWalker.getInstance().walk(StackWalking::newWalk);
		System.out.println(line);
	}

	private static String newWalk(Stream<StackFrame> stackFrameStream) {
		return stackFrameStream
				.filter(frame -> frame.getMethodName().contains("one"))
				.findFirst()
				.map(frame -> "Line " + frame.getLineNumber())
				.orElse("Unknown line");
	}

}
