package dev.nipafx.demo.java9.runtime.multi_release;

public class Main {

	// see multi-release.sh in the project's root folder for how to run this example

	public static void main(String[] args) {
		System.out.println(new VersionDependent8().version());
	}

}
