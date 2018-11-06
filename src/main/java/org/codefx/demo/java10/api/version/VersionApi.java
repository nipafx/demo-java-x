package org.codefx.demo.java10.api.version;

import java.lang.Runtime.Version;

public class VersionApi {

	public static void main(String[] args) {
		Version version = Runtime.version();
		// these were all deprecated in Java 10
		System.out.println(version.major() + "." + version.minor() + "." + version.security());
		// use these instead
		System.out.println(version.feature() + "." + version.interim() + "." + version.update() + "." + version.patch());
	}

}
