package org.codefx.demo.java16.api.stream;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class MapMultiLiftCollect {

	public static void main(String[] args) {
		List<Message> messages = List.of(
				new Message(LocalDateTime.of(2020, 10, 20, 21, 20, 20, 0), "Let's"),
				new Message(LocalDateTime.of(2020, 10, 20, 21, 28, 20, 0), "group"),
				new Message(LocalDateTime.of(2020, 10, 20, 21, 52, 20, 0), "these"),
				new Message(LocalDateTime.of(2020, 10, 20, 22, 10, 20, 0), "messages"),
				new Message(LocalDateTime.of(2020, 10, 20, 22, 43, 20, 0), "by"),
				new Message(LocalDateTime.of(2020, 10, 20, 23, 25, 20, 0), "hour."));

		System.out.println("Last group missing:");
		messages.stream()
				.mapMulti(new MessageGrouper()::groupByHour)
				// groups aren't finalized until a message with a new hour is observed and so the last group is never finalized, i.e. missing
				.forEach(group -> System.out.println(group.messages().get(0).timestamp().getHour() + "h: " + group.messages().size()));

		System.out.println("Incomplete groups observed:");
		messages.stream()
				.mapMulti(new IncompleteMessageGrouper()::groupByHour)
				// a new group is published as soon as a message with a new hour is observed and so, due to streams' implementation,
				// incomplete groups can be observed (`findFirst` would be even worse!)
				.forEach(group -> System.out.println(group.messages().get(0).timestamp().getHour() + "h: " + group.messages().size()));
	}

	private static class MessageGrouper {

		private final List<Message> messages = new ArrayList<>();

		public void groupByHour(Message message, Consumer<MessageGroup> lift) {
			if (messages.isEmpty())
				messages.add(message);
			else {
				int messageHour = message.timestamp().getHour();
				int currentHour = messages.get(0).timestamp().getHour();
				if (messageHour == currentHour)
					messages.add(message);
				else {
					lift.accept(new MessageGroup(messages));
					messages.clear();
					messages.add(message);
				}
			}
		}

	}

	private static class IncompleteMessageGrouper {

		private MessageGroup currentGroup;

		public void groupByHour(Message message, Consumer<MessageGroup> lift) {
			if (currentGroup == null)
				startNextGroup(message, lift);
			else {
				int messageHour = message.timestamp().getHour();
				int currentHour = currentGroup.messages.get(0).timestamp().getHour();
				if (messageHour == currentHour)
					currentGroup.messages.add(message);
				else
					startNextGroup(message, lift);
			}
		}

		private void startNextGroup(Message message, Consumer<MessageGroup> lift) {
			currentGroup = new MessageGroup();
			currentGroup.messages.add(message);
			lift.accept(currentGroup);
		}

	}

	record MessageGroup(List<Message> messages) {

		MessageGroup {
			messages = new ArrayList<>(messages);
		}

		MessageGroup() {
			this(new ArrayList<>());
		}

	}

	record Message(LocalDateTime timestamp, String message) {

		Message {
			requireNonNull(timestamp);
			requireNonNull(message);
		}

	}

}
