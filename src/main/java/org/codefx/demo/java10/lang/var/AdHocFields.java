package org.codefx.demo.java10.lang.var;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import static java.util.Map.entry;

public class AdHocFields {

	/*
	 * DOWNSIDES
	 *
	 *  - verbose
	 *  - combination of non-trivial language features (anonymous types & type inference)
	 *  - falls apart when extracting methods
	 *
	 * ALTERNATIVES
	 *
	 *  - Map.Entry
	 *  - a library offering tuples
	 *  - wait for records / data classes
	 */

	private static void process(User user, Map<User, SimpleAddress> addresses) {
		// with var it is possible to directly declare anonymous types
		// and refence their ad-hoc fields later
		var userWithAddress = new Object() {
			User _user = user;
			Optional<SimpleAddress> _address = Optional.ofNullable(addresses.get(user));
		};

		// further work with `userwithAddress`
		String asString = userWithAddress._user + " at " + userWithAddress._address;
		System.out.println(asString);
	}

	private static void processWithAnonymousType(List<User> users, Map<User, SimpleAddress> addresses) {
		// the stream pipeline works exactly the same in Java 8, but `userWithAddress`
		// would have to be `Optional<? extends Object>`, thus loosing the ad-hoc fields
		var userWithAddress = users.stream()
				.map(user -> new Object() {
					User _user = user;
					Optional<SimpleAddress> _address = Optional.ofNullable(addresses.get(user));
				})
				.filter(o -> o._address.isPresent())
				.filter(o -> isValid(o._address.get()))
				.map(ua -> new Object() {
					User _user = ua._user;
					FullAddress _address = canonicalize(ua._address.get());
				})
				.findAny();

		// further work with `userwithAddress`
		String asString = userWithAddress
				.map(ua -> ua._user + " at " + ua._address)
				.orElse("NONE");
		System.out.println(asString);
	}

	private static void processWithEntries(List<User> users, Map<User, SimpleAddress> addresses) {
		// with `Entry` it is no longer necessary to rely on `var` - it is also shorter
		Optional<Entry<User, FullAddress>> userWithAddress = users.stream()
				.map(user -> entry(user, Optional.ofNullable(addresses.get(user))))
				.filter(o -> o.getValue().isPresent())
				.filter(o -> isValid(o.getValue().get()))
				.map(ua -> entry(ua.getKey(), canonicalize(ua.getValue().get())))
				.findAny();

		// further work with `userwithAddress`
		String asString = userWithAddress
				.map(ua -> ua.getKey() + " at " + ua.getValue())
				.orElse("NONE");
		System.out.println(asString);
	}

	private static boolean isValid(SimpleAddress address) {
		// fancy validation of `address`
		return true;
	}

	private static FullAddress canonicalize(SimpleAddress address) {
		// fancy processing of `address`
		return new FullAddress(address.address);
	}

	private static class User {

		private final String name;

		public User(String name) {
			this.name = name;
		}

	}

	private static class SimpleAddress {

		private final String address;

		public SimpleAddress(String address) {
			this.address = address;
		}
	}

	private static class FullAddress {

		private final String address;

		public FullAddress(String address) {
			this.address = address;
		}
	}

}
