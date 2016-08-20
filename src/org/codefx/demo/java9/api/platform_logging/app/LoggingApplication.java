package org.codefx.demo.java9.api.platform_logging.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;

/**
 * The application that is launched with a custom
 * {@link java.lang.System.LoggerFinder}.
 * It uses {@link java.util.logging.Logger} to log some messages.
 */
public class LoggingApplication extends Application {

	private static Logger LOGGER = System.getLogger("Application");

	@Override
	public void start(Stage primaryStage) throws Exception {
		LOGGER.log(Level.DEBUG, "Everything looks peachy");

		primaryStage.setTitle("Hello World!");
		primaryStage.setScene(new Scene(new StackPane(new Button("Hello World")), 300, 250));
		primaryStage.show();

		LOGGER.log(Level.INFO, "Hello, World!");
	}

	public static void main(String[] args) {
		launch(args);
	}

}
