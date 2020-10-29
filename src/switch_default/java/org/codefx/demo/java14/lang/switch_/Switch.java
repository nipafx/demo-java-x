package org.codefx.demo.java14.lang.switch_;

public class Switch {

	public static void main(String[] args) {
		var bool = Bool.valueOf(args[0]);
		var result = switch (bool) {
			case TRUE -> true;
			case FALSE -> false;
			// branches are exhaustive for `OldBool`, so no default
		};
		System.out.println("Argument was \"" + result + "\"");
	}

}
