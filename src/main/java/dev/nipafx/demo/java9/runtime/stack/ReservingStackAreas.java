package dev.nipafx.demo.java9.runtime.stack;

// This import may lead to errors in your IDE - two choices:
//  - put the import and the two uses below in comments
//  - configure your IDE to add the following compiler argument:
//    --add-exports=java.base/jdk.internal.vm.annotation=ALL-UNNAMED
import jdk.internal.vm.annotation.ReservedStackAccess;

public class ReservingStackAreas {

	private static int depth = 0;

	public static void main(String[] args) {
		System.out.println("\nDEPTH & CALLS USING REGULAR STACK");
		determineDepth();
		recurseThenGreet();

		System.out.println("\nDEPTH USING REGULAR STACK & CALLS USING RESERVED STACK");
		determineDepth();
		recurseWithReservedStackThenGreet();

		System.out.println("\nDEPTH USING RESERVED STACK");
		determineDepthWithReservedStack();
	}

	private static void determineDepth() {
		depth = 0;
		try {
			recurseToDetermineMaxDepth();
		} catch (StackOverflowError err) { }
		System.out.printf("Depth: %d%n", depth);
	}

	private static void recurseToDetermineMaxDepth() {
		depth++;
		recurseToDetermineMaxDepth();
	}

	private static void recurseThenGreet() {
		try {
			recurseToDepth();
			System.out.println("Hi!");
		} catch (StackOverflowError err) {
			System.out.println("Stack Overflow");
		}
	}

	private static void recurseToDepth() {
		depth--;
		if (depth > 0)
			recurseToDepth();
	}

	@ReservedStackAccess
	private static void determineDepthWithReservedStack() {
		determineDepth();
	}

	@ReservedStackAccess
	private static void recurseWithReservedStackThenGreet() {
		recurseThenGreet();
	}

}
