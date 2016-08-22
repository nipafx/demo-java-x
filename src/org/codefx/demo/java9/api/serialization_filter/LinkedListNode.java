package org.codefx.demo.java9.api.serialization_filter;

import java.io.Serializable;
import java.util.Objects;

public class LinkedListNode implements Serializable {

	private final String data;
	private final LinkedListNode next;

	private LinkedListNode(String data, LinkedListNode next) {
		this.data = data;
		this.next = next;
	}

	public static LinkedListNode createNode(String data) {
		return new LinkedListNode(data, null);
	}

	public static LinkedListNode createList(String data, LinkedListNode next) {
		return new LinkedListNode(data, next);
	}

	public static LinkedListNode createList(String... data) {
		LinkedListNode tail = createNode(data[data.length - 1]);
		for (int i = data.length - 2; i >= 0; i--)
			tail = createList(data[i], tail);
		return tail;
	}

	private String toCsvString() {
		return data + (next == null ? "" : ", " + next.toCsvString());
	}

	@Override
	public String toString() {
		return "[ " + toCsvString() + " ]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		LinkedListNode that = (LinkedListNode) o;
		return Objects.equals(data, that.data)
				&& Objects.equals(next, that.next);
	}

	@Override
	public int hashCode() {
		return Objects.hash(data);
	}

}
