package org.codefx.demo.java14.lang.switch_;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Switch {

	public static void main(String[] args) {
		catchExceptions(
			Switch::switchStatement,
			Switch::switchExpression,
			Switch::block,
			Switch::multipleCaseLabels,
			Switch::exhaustiveness,
			Switch::noFallThrough,
			Switch::voidExpression,
			Switch::polyExpression,
			Switch::moreTypes,
			Switch::switchExpressionWithColon,
			Switch::returnFromWithin
		);
	}

	private static void switchStatement() {
		System.out.println("---- SWITCH STATEMENT ----");
		var bool = Bool.random();
		// in real life, I would have pulled this into a method (see `switchMethod()`),
		// just so I can use `return` instead of `break`
		boolean result;
		switch (bool) {
			case TRUE:
				result = true;
				// don't forget to `break` or you're screwed!
				break;
			case FALSE:
				result = false;
				break;
			case FILE_NOT_FOUND:
				// intermediate variable for demo purposes; wait for it...
				var ex = new UncheckedIOException(
						"This is ridiculous!",
						new FileNotFoundException());
				throw ex;
			default:
				// ... here we go:
				// can't declare another variable with the same name
				var ex2 = new IllegalArgumentException(
					"Seriously?! 🤬");
				throw ex2;
		}
	}

	private static boolean switchMethod() {
		var bool = Bool.random();
		switch (bool) {
			case TRUE: return true;
			case FALSE: return false;
			case FILE_NOT_FOUND:
				throw new UncheckedIOException(
						"This is ridiculous!",
						new FileNotFoundException());
				// without default branch, the method wouldn't compile
			default:
				throw new IllegalArgumentException("Seriously?! 🤬");
		}
	}

	private static void switchExpression() {
		System.out.println("---- SWITCH EXPRESSION ----");
		var bool = Bool.random();
		// switch "gets a value" (i.e. becomes an expression that can be evaluated);
		// lambda-esque syntax instead of break/return;
		// no fall-through;
		// compiler checks exhaustiveness;
		// right-hand side can be expression, exception (both demo'ed here), or block (see `block`)
		boolean result = switch (bool) {
			case TRUE -> true;
			case FALSE -> false;
			case FILE_NOT_FOUND -> throw new UncheckedIOException(
					"This is ridiculous!",
					new FileNotFoundException());
			// this is not strictly necessary - see `exhaustiveness()`
			default -> throw new IllegalArgumentException("Seriously?! 🤬");
		};

		System.out.println("Bool was: " + result);
	}

	// how to return from blocks; variable scope
	private static void block() {
		System.out.println("---- BLOCKS ----");
		boolean result = switch (Bool.random()) {
			case TRUE -> {
				System.out.println("Bool true");
				// return with `yield`, not `return`
				// (good decision - aligns all options to leave `switch` branch)
				yield true;
			}
			case FALSE -> {
				System.out.println("Bool false");
				yield false;
			}
			case FILE_NOT_FOUND -> {
				// intermediate variable for demo purposes; wait for it...
				var ex = new UncheckedIOException(
						"This is ridiculous!",
						new FileNotFoundException());
				throw ex;
			}
			default -> {
				// ... here we go:
				// can now declare another variable with the same name!
				var ex = new IllegalArgumentException(
					"Seriously?! 🤬");
				throw ex;
			}
		};
	}

	private static void multipleCaseLabels() {
		System.out.println("---- MULTIPLE CASE LABELS ----");
		String result = switch (Bool.random()) {
			case TRUE, FALSE -> "sane";
			// `default, case FILE_NOT_FOUND -> ...` does not work
			// (neither does other way around), but that makes
			// sense because using only `default` suffices
			default -> "insane";
		};

		System.out.println("Bool was: " + result);
	}

	private static void exhaustiveness() {
		System.out.println("---- EXHAUSTIVENESS ----");
		boolean result = switch (Bool.random()) {
			case TRUE -> true;
			// The following branch would lead to a compile error because
			// it doesn't produce a value
//			case FALSE -> System.out.println("false");
			case FALSE -> false;
			case FILE_NOT_FOUND -> throw new UncheckedIOException(
					"This is ridiculous!",
					new FileNotFoundException());
			// we don't actually need a `default` branch because we've covered
			// all three possible `Bool` values; the compiler slips one in for
			// the case where `Bool` was recompiled with a new value; see
			// `src/switch_default/java` and `switch-default.sh` for experiments
		};

		System.out.println("Bool was: " + result);
	}

	private static void noFallThrough() {
		System.out.println("---- NO FALL-THROUGH ----");
		var bool = Bool.TRUE;
		switch (bool) {
			case TRUE, FALSE -> System.out.println("Bool was sane");
			// in colon form, we would see "sane" and "insane"
			case FILE_NOT_FOUND -> System.out.println("Bool was insane");
		}
	}

	private static void voidExpression() {
		System.out.println("---- VOID EXPRESSIONS ----");
		// in Java `void` is not an instance of `Void`, so the following doesn't compile;
		// error message: "void cannot be converted to Void" 😹
//		Void v = switch (Bool.random()) {
//			case TRUE, FALSE -> System.out.println("sane");
//			case FILE_NOT_FOUND -> System.out.println("insane");
//		};
	}

	private static void polyExpression() {
		System.out.println("---- POLY EXPRESSION ----");
		var bool = Bool.random();

		// if the switch expression's target type is known (e.g. because the result
		// is assigned to a variable), that type will be "pushed into" the branches
		// and each branch's result must be assignable to it

		// here, the default branch results in `IllegalArgumentException`, not `String`,
		// so this is a compile error:
//		String message = switch (bool) {
//			case TRUE, FALSE -> "sane";
//			default -> new IllegalArgumentException("insane");
//		};

		// `Serializable` is a supertype for `String` and `IllegalArgumentException`,
		// so this works:
		Serializable serializableMessage = switch (bool) {
			case TRUE, FALSE -> "sane";
			default -> new IllegalArgumentException("insane");
		};
		System.out.println("Serializable message: " + serializableMessage);

		// if the target type is unknown, the compiler will infer one
		// that matches all switch branches
		var varMessage = switch (bool) {
			case TRUE, FALSE -> "sane";
			default -> new IllegalArgumentException("insane");
		};
		System.out.println("Var message: " + varMessage);

	}

	private static void moreTypes() {
		System.out.println("---- SWITCH OVER MORE TYPES ----");
		int i = 0;
		switch (i) {
			case 0 -> System.out.println("Zero");
			case 1 -> System.out.println("One");
			default -> System.out.println("Other");
		}

		long l = 42;
		double d = 42;
		// these do not work yet, but they may in the future
//		switch (l) {
//			case 0 -> System.out.println("Zero");
//			case 1 -> System.out.println("One");
//			default -> System.out.println("Other");
//		}
//		switch (d) {
//			case 0.0 -> System.out.println("Zero");
//			case 1.0 -> System.out.println("One");
//			default -> System.out.println("Other");
//		}
	}

	private static void switchExpressionWithColon() {
		System.out.println("---- SWITCH EXPRESSION WITH COLON ----");
		// the arrow syntax is optional; colon and `yield` work just as well
		String result = switch (Bool.random()) {
			// multiple case labels
			case TRUE, FALSE: yield "sane";
			// fall-through prevented by `yield`
			//  blocks (and thus block scope) apply as well
			case FILE_NOT_FOUND: {
				String message = "This is ridiculous!";
				yield message;
			}
			// compiler verifies exhaustiveness
		};

		System.out.println("Bool was: " + result);
	}

	private static String returnFromWithin() {
		System.out.println("---- SWITCH STATEMENT THAT RETURNS ----");
		// both forms (arrow and colon) allow to return from within the `switch`
		// as long as it is used as a statement, not an expression

		switch (Bool.random()) {
			// `return` is only possible from block
			case TRUE, FALSE -> { return "sane"; }
			case FILE_NOT_FOUND -> {
				return "This is ridiculous!";
			}
		}

		switch (Bool.random()) {
			case TRUE, FALSE: return "sane";
			case FILE_NOT_FOUND: {
				return "This is ridiculous!";
			}
		}

		throw new IllegalStateException();
	}

	// HELPER

	private static void catchExceptions(Runnable... blocks) {
		Arrays.stream(blocks).forEach(Switch::catchException);
	}

	private static void catchException(Runnable block) {
		try {
			block.run();
		} catch (Exception ex) {
			System.out.println("Exception: " + ex.getClass().getSimpleName());
		}
	}

	// https://thedailywtf.com/articles/What_Is_Truth_0x3f_
	enum Bool {
		TRUE, FALSE, FILE_NOT_FOUND;

		static Bool random() {
			return Bool.values()[ThreadLocalRandom.current().nextInt(3)];
		}

	}

}
