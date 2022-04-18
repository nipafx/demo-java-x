package dev.nipafx.demo.java9.api.desktop;

import java.awt.Taskbar;
import java.awt.Taskbar.Feature;

import static java.util.Arrays.stream;

public class DesktopFeatures {

	public static void main(String[] args) {
		if (Taskbar.isTaskbarSupported()) {
			System.out.println("Taskbar is supported - feature support breakdown:");
			Taskbar taskbar = Taskbar.getTaskbar();
			stream(Feature.values())
					.forEach(feature -> System.out.printf(" - %s: %s%n", feature, taskbar.isSupported(feature)));
		} else {
			System.out.println("Taskbar is not supported on your platform. :(");
		}
	}

}
