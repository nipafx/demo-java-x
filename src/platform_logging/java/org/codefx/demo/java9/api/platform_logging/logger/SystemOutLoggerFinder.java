package org.codefx.demo.java9.api.platform_logging.logger;

import java.lang.System.Logger;
import java.lang.System.LoggerFinder;

public class SystemOutLoggerFinder extends LoggerFinder {

	@Override
	public Logger getLogger(String name, Module module) {
		return new SystemOutLogger();
	}

}
