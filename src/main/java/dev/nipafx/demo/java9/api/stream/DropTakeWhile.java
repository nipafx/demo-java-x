package dev.nipafx.demo.java9.api.stream;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static dev.nipafx.demo.java9.api.stream.DropTakeWhile.Priority.ERROR;
import static dev.nipafx.demo.java9.api.stream.DropTakeWhile.Priority.INFO;
import static dev.nipafx.demo.java9.api.stream.DropTakeWhile.Priority.WARNING;
import static java.util.Objects.requireNonNull;

public class DropTakeWhile {

	final List<LogMessage> messages;

	public DropTakeWhile(List<LogMessage> messages) {
		this.messages = requireNonNull(messages);
	}

	public static void main(String[] args) {
		DropTakeWhile messages = new DropTakeWhile(List.of(
				new LogMessage("Starting", INFO), new LogMessage("Started", INFO),
				new LogMessage("High Temperature Detected", WARNING), new LogMessage("Overheated", ERROR),
				new LogMessage("Shutdown initiated", INFO)));

		System.out.println("FROM FIRST WARNING:");
		messages.fromFirstWarning().forEach(System.out::println);
		System.out.println();

		System.out.println("UNTIL FIRST ERROR:");
		messages.untilFirstError().forEach(System.out::println);
		System.out.println();

		System.out.println("FROM FIRST WARNING TO FOLLOWING ERROR:");
		messages.fromFirstWarningToFollowingError().forEach(System.out::println);
		System.out.println();
	}

	public Stream<LogMessage> fromFirstWarning() {
		return messages.stream()
				.dropWhile(message -> message.priority.lessThan(WARNING));
	}

	public Stream<LogMessage> untilBeforeFirstError() {
		return messages.stream()
				.takeWhile(message -> message.priority.atLeast(ERROR));
	}

	public Stream<LogMessage> untilFirstError() {
		AtomicBoolean sawFirstError = new AtomicBoolean(false);
		return messages.stream()
				.takeWhile(message -> !sawFirstError.getAndSet(message.priority.atLeast(ERROR)));
	}

	public Stream<LogMessage> fromFirstWarningToFollowingError() {
		AtomicBoolean sawFirstError = new AtomicBoolean(false);
		return messages.stream()
				.dropWhile(message -> message.priority.lessThan(WARNING))
				.takeWhile(message -> !sawFirstError.getAndSet(message.priority.atLeast(ERROR)));
	}

	static class LogMessage {

		final String message;
		final Priority priority;

		LogMessage(String message, Priority priority) {
			this.message = requireNonNull(message);
			this.priority = priority;
		}

		@Override
		public String toString() {
			return "{" + message + ", " + priority + "}";
		}
	}

	enum Priority {
		INFO,
		WARNING,
		ERROR;

		boolean lessThan(Priority other) {
			return other.compareTo(this) > 0;
		}

		boolean atMost(Priority other) {
			return other.compareTo(this) >= 0;
		}

		boolean atLeast(Priority other) {
			return other.compareTo(this) <= 0;
		}

		boolean moreThan(Priority other) {
			return other.compareTo(this) < 0;
		}

	}

}
