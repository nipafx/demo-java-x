package dev.nipafx.demo.java9.runtime.security;

import java.security.DrbgParameters;
import java.security.DrbgParameters.Instantiation;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import static java.security.DrbgParameters.Capability.RESEED_ONLY;

public class Drbg {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		Instantiation instantiation = DrbgParameters.instantiation(128, RESEED_ONLY, null);
		SecureRandom random = SecureRandom.getInstance("DRBG", instantiation);

		byte[] bytes = new byte[20];
		random.nextBytes(bytes);
		for (byte b : bytes) {
			System.out.print(b + " ");
		}
		System.out.println();
	}

}
