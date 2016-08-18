import java.util.Optional;

class SafeVarargs {

	class InJava8 {

		// @java.lang.SafeVarargs
		// With the annotation, it would not compile
		// because the method is private and non-final.
		// Without the annotation, we get a pesky warning.
		private <T> Optional<T> first(T... args) {
			if (args.length == 0)
				return Optional.empty();
			else
				return Optional.of(args[0]);
		}

	}

	class InJava9 {

		@java.lang.SafeVarargs
		// here we go with Java 9
		private <T> Optional<T> first(T... args) {
			if (args.length == 0)
				return Optional.empty();
			else
				return Optional.of(args[0]);
		}

	}

}
