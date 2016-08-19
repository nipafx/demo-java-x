package org.codefx.demo.java9.api.platform_logging.app;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;

/**
 * The application that is launched with a custom
 * {@link java.lang.System.LoggerFinder}.
 * It uses {@link java.util.logging.Logger} to log some messages.
 */
public class LoggingApplication {

	public static void main(String[] args) {
		// my messages go through the SystemOut logger but the JVM's don't :(
		Logger logger = System.getLogger("Application");
		logger.log(Level.DEBUG, "Everything looks peachy");
		logger.log(Level.INFO, "Hello, World!");
		logger.log(Level.WARNING, "Causing an exception... ");
		System.out.println(42 / 0);
	}

}
