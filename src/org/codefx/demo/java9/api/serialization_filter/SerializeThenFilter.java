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
		LinkedListNode deserializedList = deserializeWithFilter(serializedList);

		System.out.println("Deserialized list: " + deserializedList);
	}

	private static byte[] serialize(LinkedListNode list) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		new ObjectOutputStream(byteArrayOutputStream).writeObject(list);
		return byteArrayOutputStream.toByteArray();
	}

	// TODO: implement and add filter as soon as it is merged into JDK
	private static LinkedListNode deserializeWithFilter(byte[] serializedList, Object filter) throws IOException, ClassNotFoundException {
		ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(serializedList));
		// inputStream.setSerialFilter(filter);
		return (LinkedListNode) inputStream.readObject();
	}

}
