package dev.nipafx.demo.java9.api.version;

import java.lang.Runtime.Version;

public class VersionApi {

	public static void main(String[] args) {
		Version version = Runtime.version();
		// these were all deprecated in Java 10
		System.out.println(version.major() + "." + version.minor() + "." + version.security());
	}

}
