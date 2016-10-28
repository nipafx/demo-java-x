package org.codefx.demo.java9.api.serialization_filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.codefx.demo.java9.api.serialization_filter.LinkedListNode.createList;

public class SerializeThenFilter {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		LinkedListNode list = createList("A", "B", "C");
		System.out.println("List to serialize: " + list);

		byte[] serializedList = serialize(list);
		Object filter = createFilter();
		LinkedListNode deserializedList = deserializeWithFilter(serializedList, filter);

		System.out.println("Deserialized list: " + deserializedList);
	}

	private static byte[] serialize(LinkedListNode list) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		new ObjectOutputStream(byteArrayOutputStream).writeObject(list);
		return byteArrayOutputStream.toByteArray();
	}

	private static Object createFilter() {
		// TODO: create filter as soon as the feature is merged into JDK
		return null;
	}

	private static LinkedListNode deserializeWithFilter(byte[] serializedList, Object filter) throws IOException, ClassNotFoundException {
		ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(serializedList));
		// TODO: add filter as soon as the feature is merged into JDK
		// inputStream.setSerialFilter(filter);
		return (LinkedListNode) inputStream.readObject();
	}

}
