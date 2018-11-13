package org.codefx.demo.java11.api.string;

public class Repeat {

	public static void main(String[] args) throws InterruptedException {
		for (int i = 1; i <= 10; i++) {
			System.out.println(".".repeat(i));
			Thread.sleep(i * 100);
		}
	}

}
