package org.codefx.demo.java9.api.stack_walking;

import java.lang.StackWalker.StackFrame;
import java.util.stream.Stream;

class StackWalking {

	public static void main(String[] args) {
		one();
	}

	static void one() {
		two();
	}

	static void two() {
		three();
	}

	static void three() {
		String line = StackWalker.getInstance().walk(StackWalking::walk);
		System.out.println(line);
	}

	private static String walk(Stream<StackFrame> stackFrameStream) {
		return stackFrameStream
				.filter(frame -> frame.getMethodName().contains("one"))
				.findFirst()
				.map(frame -> "Line " + frame.getLineNumber())
				.orElse("Unknown line");
	}

}
