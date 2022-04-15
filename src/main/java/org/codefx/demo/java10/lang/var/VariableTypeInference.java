package org.codefx.demo.java10.lang.var;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class VariableTypeInference {

	// var can only be used for local variables; not for fields
//	private var foo = "";

	public static void main(String[] args) throws Exception {
		simpleVarExample();
		loopExample();
		tryExample();
		powerfulInference();
		var();
	}

	/*
	 * WORKS
	 */

	private static void simpleVarExample() throws IOException {
		var codefx = new URL("http://codefx.org");
		// connection needs to be closed but not in the demo
		var connection = codefx.openConnection();
		var reader = new BufferedReader(
				new InputStreamReader(connection.getInputStream()));

		reader.lines()
				.limit(3)
				.map(s -> s + " ")
				.forEach(System.out::println);
	}

	private static void loopExample() {
		var numbers = List.of("a", "b", "c");
		for (var nr : numbers)
			System.out.print(nr + " ");
		for (var i = 0; i < numbers.size(); i++)
			System.out.print(numbers.get(i) + " ");
		System.out.println();
	}

	private static void tryExample() {
		try (var file = new FileInputStream(new File("no-such-file"))) {
			new BufferedReader(new InputStreamReader(file))
					.lines()
					.forEach(System.out::println);
		} catch (IOException ex) {
			// at least, we tried
			System.out.println("There's actually no `no-such-file`. :)");
		}
	}

	private static void powerfulInference() {
		// may be inferred

		// anonymous class types
		var strings = new ArrayList<String>() {
			public int tenSize() {
				return 10 * size();
			}
		};

		strings.add("1");
		System.out.format("TenSize of %s is %d.%n", strings, strings.tenSize());

		// intersection types
		CharSequence chars = "c";
		Comparable comp = "c";

		chars.length();
		comp.compareTo(null);

//		CharSequence & Comparable s = createString("s");
		var s = createString("c");
		s.length();
		s.compareTo(null);
	}

	private static <T extends CharSequence & Comparable> T createString(String string) {
		return (T) string;
	}

	private static void var() {
		// var is still allowed as variable and method names, just not as class name
		var var = "var";
	}

	/*
	 * DOESN'T WORK
	 *
	 * Commented out code doesn't compile
	 */

	// var can only be used for local variables; not in a method signature
//	private var getString() {
//		return "foo";
//	}

	private void miscErrors() {
		// From JEP 286:
		// "Rejecting locals without initializers narrows the scope of the feature,
		// avoiding "action at a distance" inference errors, and only excludes a
		// small portion of locals in typical programs."

		// "lack initializers"
		String s;
//		var s;
		String f;
//		var f;
		f = "Foo";

		// "declare multiple variables"
		int foo = 0, bar = 1;
//		var fuu = 0, baar = 1;

		// "have extra array dimension brackets"
		int ints[] = new int[0];
//		var ints[] = new int[0];

		// lambda variables (works on Java 11+)
//		Function<String, String> appendSpace = (var a) -> a + " ";
	}

	private void polyTypeErrors() {
		// array literals
		int[] intses = { 0, 1, 2 };
//		var intses = {0, 1, 2};

		// lambdas
		Function<String, String> appendSpace = a -> a + " ";
//		var appendSpace = a -> a + " ";
	}

	private void inferenceAtADistance() {
		// "List" case:

		// cross fingers that compiler infers List<User>
		var strings = new ArrayList<String>();
		// but it doesn't, so this is a compile error:
//		strings = new LinkedList<String>();

		// other case:

		// inferred as `int`
		var id = 123;
		if (id < 100) {
			// very long branch; unfortunately
			// not its own method call
		} else {
			// oh boy, much more code...
		}

		// now we append this line:
//		id = "124";
		// if this would compile, `id` needed to be a common supertype of int and String,
		// which doesn't work with `<`, so the compile error would be far away in the `if` above
	}

}
