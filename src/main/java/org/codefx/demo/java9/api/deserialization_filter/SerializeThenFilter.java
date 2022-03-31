package org.codefx.demo.java9.api.deserialization_filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputFilter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.codefx.demo.java9.api.deserialization_filter.LinkedListNode.createList;

public class SerializeThenFilter {

	/*
	 * CAREFUL: (De)serialization is a security-sensitive topic, but these examples don't take
	 *          that into account. They just demonstrate how some parts of the API work without
	 *          looking at the security implications. If you use this APIs to secure your application,
	 *          make sure to NOT USE THESE EXAMPLES. Instead, make sure to master the topic
	 *          and API first, e.g. by starting here:
	 *           - https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/ObjectInputFilter.html
	 *           - https://docs.oracle.com/pls/topic/lookup?ctx=javase17&id=secure_coding_guidelines_javase
	 *           - https://docs.oracle.com/pls/topic/lookup?ctx=javase17&id=serialization_filter_guide
	 */

	// launch with -Djdk.serialFilter=maxdepth=5 to configure deserialization filter to reject
	// instance graphs with a depth of 6 or more - observe effect in `staticDeserializationFilter`
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		staticDeserializationFilter();
		dynamicDeserializationFilter();
	}

	private static void staticDeserializationFilter() throws IOException, ClassNotFoundException {
		LinkedListNode list = createList("A", "B", "C", "D", "E", "F", "G", "H");
		System.out.println("List to serialize: " + list);

		byte[] serializedList = serialize(list);
		try {
			LinkedListNode deserializedList = deserialize(serializedList);
			System.out.println("Deserialization succeeded unexpectedly: " + deserializedList);
		} catch (InvalidClassException ex) {
			System.out.println("Deserialization failed as expected: " + ex.getMessage());
		}
	}

	private static void dynamicDeserializationFilter() throws IOException, ClassNotFoundException {
		LinkedListNode list = createList("AAA", "BBB", "CCC");
		System.out.println("List to serialize: " + list);

		byte[] serializedList = serialize(list);
		ObjectInputFilter filter = ObjectInputFilter.Config.createFilter("maxbytes=128");

		try {
			LinkedListNode deserializedList = deserializeWithFilter(serializedList, filter);
			System.out.println("Deserialization succeeded unexpectedly: " + deserializedList);
		} catch (InvalidClassException ex) {
			System.out.println("Deserialization failed as expected: " + ex.getMessage());
		}
	}

	private static byte[] serialize(LinkedListNode list) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		new ObjectOutputStream(byteArrayOutputStream).writeObject(list);
		return byteArrayOutputStream.toByteArray();
	}

	private static LinkedListNode deserialize(byte[] serializedList) throws IOException, ClassNotFoundException {
		ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(serializedList));
		return (LinkedListNode) inputStream.readObject();
	}

	private static LinkedListNode deserializeWithFilter(byte[] serializedList, ObjectInputFilter filter) throws IOException, ClassNotFoundException {
		ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(serializedList));
		 inputStream.setObjectInputFilter(filter);
		return (LinkedListNode) inputStream.readObject();
	}

}
