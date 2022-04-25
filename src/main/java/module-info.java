import dev.nipafx.demo.java17.api.random.Xkcd;
import dev.nipafx.demo.java18.api.ip_resolution.ForwardingInetAddressResolverProvider;

import java.net.spi.InetAddressResolverProvider;
import java.util.random.RandomGenerator;

/**
 * Project demonstrating various Java features introduced since Java 9.
 */
module dev.nipafx.demo.java_x {
	exports dev.nipafx.demo.java18.javadoc;

	requires java.desktop;
	requires java.net.http;
	requires jdk.incubator.vector;

	provides RandomGenerator with Xkcd;
	provides InetAddressResolverProvider with ForwardingInetAddressResolverProvider;
}
