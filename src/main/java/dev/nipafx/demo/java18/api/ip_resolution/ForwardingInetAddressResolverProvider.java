package dev.nipafx.demo.java18.api.ip_resolution;

import java.net.spi.InetAddressResolver;
import java.net.spi.InetAddressResolverProvider;

public class ForwardingInetAddressResolverProvider extends InetAddressResolverProvider {

	@Override
	public InetAddressResolver get(Configuration configuration) {
		return new ForwardingInetAddressResolver(configuration.builtinResolver());
	}

	@Override
	public String name() {
		return "Injectable";
	}

}
