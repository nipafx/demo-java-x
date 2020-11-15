package org.codefx.demo.java16.api.stream;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;

public class MapMultiGroupMessages {

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
				.mapMulti(new LateMessageGrouper()::groupByHour)
				// groups aren't finalized until a message with a new hour is observed and so the last group is never finalized, i.e. missing
				.forEach(group -> System.out.println(group.messages().get(0).timestamp().getHour() + "h: " + group.messages().size()));

		System.out.println("Incomplete groups observed:");
		messages.stream()
				.mapMulti(new EarlyMessageGrouper()::groupByHour)
				// a new group is published as soon as a message with a new hour is observed and so, due to streams' implementation,
				// incomplete groups can be observed (`findFirst` would be even worse!)
				.forEach(group -> System.out.println(group.messages().get(0).timestamp().getHour() + "h: " + group.messages().size()));
	}

	private static class LateMessageGrouper {

		private final List<Message> messages = new ArrayList<>();

		public void groupByHour(Message message, Consumer<MessageGroup> downstream) {
			if (messages.isEmpty())
				messages.add(message);
			else {
				int messageHour = message.timestamp().getHour();
				int currentHour = messages.get(0).timestamp().getHour();
				if (messageHour == currentHour)
					messages.add(message);
				else {
					downstream.accept(new MessageGroup(messages));
					messages.clear();
					messages.add(message);
				}
			}
		}

	}

	private static class EarlyMessageGrouper {

		private MessageGroup currentGroup;

		public void groupByHour(Message message, Consumer<MessageGroup> downstream) {
			if (currentGroup == null)
				startNextGroup(message, downstream);
			else {
				int messageHour = message.timestamp().getHour();
				int currentHour = currentGroup.messages.get(0).timestamp().getHour();
				if (messageHour == currentHour)
					currentGroup.messages.add(message);
				else
					startNextGroup(message, downstream);
			}
		}

		private void startNextGroup(Message message, Consumer<MessageGroup> downstream) {
			currentGroup = new MessageGroup();
			currentGroup.messages().add(message);
			downstream.accept(currentGroup);
		}

	}

	record MessageGroup(List<Message> messages) {

		MessageGroup {
			requireNonNull(messages);
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
