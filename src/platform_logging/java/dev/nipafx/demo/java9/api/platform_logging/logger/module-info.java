module dev.nipafx.demo.javaX.logging {
	provides System.LoggerFinder
		with dev.nipafx.demo.java9.api.platform_logging.logger.SystemOutLoggerFinder;
}
