package org.codefx.demo.java11.api.string;

public class Repeat {

	public static void main(String[] args) throws InterruptedException {
		"Java\n".repeat(25).lines().forEach(System.out::println);
	}

}
