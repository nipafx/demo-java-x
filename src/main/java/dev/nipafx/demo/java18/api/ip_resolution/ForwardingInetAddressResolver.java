package dev.nipafx.demo.java18.api.ip_resolution;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.spi.InetAddressResolver;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * An {@link InetAddressResolver} that simply forwards to the builtin resolver.
 * It acts as a starting point for adding custom behavior to host and address resolution.
 */
public class ForwardingInetAddressResolver implements InetAddressResolver {

	private InetAddressResolver builtinResolver;

	public ForwardingInetAddressResolver(InetAddressResolver builtinResolver) {
		this.builtinResolver = builtinResolver;
	}

	@Override
	public Stream<InetAddress> lookupByName(String host, LookupPolicy lookupPolicy) throws UnknownHostException {
		System.out.printf("Looking up '%s'.%n", host);
		return builtinResolver.lookupByName(host, lookupPolicy);
	}

	@Override
	public String lookupByAddress(byte[] addr) throws UnknownHostException {
		System.out.printf("Looking up '%s'.%n", Arrays.toString(addr));
		return builtinResolver.lookupByAddress(addr);
	}

}
