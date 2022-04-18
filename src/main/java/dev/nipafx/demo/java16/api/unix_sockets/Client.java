package dev.nipafx.demo.java16.api.unix_sockets;

import java.io.IOException;
import java.net.StandardProtocolFamily;
import java.net.UnixDomainSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;

public class Client {

	public static void main(String[] args) throws IOException, InterruptedException {
		Path file = Path.of(System.getProperty("user.home")).resolve("server.socket");
		UnixDomainSocketAddress address = UnixDomainSocketAddress.of(file);

		SocketChannel channel = SocketChannel.open(StandardProtocolFamily.UNIX);
		channel.connect(address);
		Thread.sleep(3_000);
		writeMessageToSocket(channel, "Hello");
		Thread.sleep(1_000);
		writeMessageToSocket(channel, "UNIX domain sockets");
	}

	private static void writeMessageToSocket(SocketChannel socketChannel, String message) throws IOException {
		ByteBuffer buffer= ByteBuffer.allocate(1024);
		buffer.clear();
		buffer.put(message.getBytes());
		buffer.flip();
		while(buffer.hasRemaining()) {
			socketChannel.write(buffer);
		}
	}

}
