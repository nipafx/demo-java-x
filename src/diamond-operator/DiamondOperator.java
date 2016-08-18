import java.util.Arrays;
import java.util.List;

class DiamondOperator {

	class inJava8 {

		<T> Box<T> createBox(T content) {
			// we have to put the `T` here :(
			return new Box<T>(content) { };
		}

	}

	class inJava {

		<T> Box<T> createBox(T content) {
			// Java 9 can infer `T` because it is a denotable type
			return new Box<>(content) { };
		}

		Box<?> createCrazyBox(Object content) {
			List<?> innerList = Arrays.asList(content);
			// we can't do the following because the inferred type is non-denotable:
			// return new Box<>(innerList) { };
			return new Box<List<?>>(innerList) { };
		}

	}

	class Box<T> {
		Box(T content) { }
	}

}
