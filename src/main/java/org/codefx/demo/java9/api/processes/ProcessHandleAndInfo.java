package org.codefx.demo.java9.api.processes;

import java.lang.ProcessHandle.Info;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

public class ProcessHandleAndInfo {

	private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

	public static void main(String[] args) {
		System.out.println("   PID |    START |     CPU TIME | USER NAME |   CHILDREN | COMMAND");
		ProcessHandle.allProcesses()
				.filter(process -> process.info().command().isPresent())
				.map(ProcessHandleAndInfo::prettyPrint)
				.forEach(System.out::println);
	}

	private static String prettyPrint(ProcessHandle process) {
		Info info = process.info();
		String start = info
				.startInstant()
				.map(instant -> LocalDateTime.ofInstant(instant, TimeZone.getDefault().toZoneId()))
				.map(DATE_TIME_FORMATTER::format)
				.orElse("n.a.");
		String cpuDuration = info
				.totalCpuDuration()
				.map(ProcessHandleAndInfo::prettyPrint)
				.orElse("n.a.");
		String user = info
				.user()
				.map(name -> name.length() <= 9
						? name
						: name.substring(0, 8) + "…")
				.orElse("n.a.");
		String command = info.command().orElse("n.a.");
		String children = process
				.children()
				.map(ProcessHandle::pid)
				.map(Object::toString)
				.collect(joining(", "));
		children = children.length() <= 10
				? children
				: children.substring(0, 9) + "…";

		return format("%6d | %8s | %12s | %9s | %10s | %s", process.pid(), start, cpuDuration, user, children, command);
	}

	private static String prettyPrint(Duration duration) {
		return NumberFormat.getNumberInstance().format(duration.toMillis()) + " ms";
	}

}
