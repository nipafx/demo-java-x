module org.codefx.demo.java9.logging {
	provides java.lang.System.LoggerFinder
		with org.codefx.demo.java9.api.platform_logging.logger.SystemOutLoggerFinder;
}