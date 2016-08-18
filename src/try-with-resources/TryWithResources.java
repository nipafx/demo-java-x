class TryWithResources {

	class InJava8 {

		void doSomethingWith(Connection connection) throws Exception {
			// in Java 8, try-with-resources can only manage resources
			// that are declared for the statement
			try(Connection c = connection) {
				c.doSomething();
			}
		}

	}

	class InJava9 {

		void doSomethingWith(Connection connection) throws Exception {
			// in Java 9, all effectively final variables work
			try(connection) {
				connection.doSomething();
			}
		}

	}

	class Connection implements AutoCloseable {

		void makeImportantPreparations() { }

		void doSomething() { }

		@Override
		public void close() throws Exception { }
	}

}
