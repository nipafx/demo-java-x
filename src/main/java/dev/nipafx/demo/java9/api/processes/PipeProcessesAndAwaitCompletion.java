package dev.nipafx.demo.java9.api.processes;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.Arrays.asList;

public class PipeProcessesAndAwaitCompletion {

	public static void main(String[] args) throws IOException, InterruptedException {
		ProcessBuilder ls = new ProcessBuilder()
				.command("tree", "-i")
				.directory(Paths.get("/home/nipa").toFile());
		ProcessBuilder grepPdf = new ProcessBuilder()
				.command("grep", "pdf")
				.redirectOutput(Redirect.INHERIT);
		List<Process> lsThenGrep = ProcessBuilder.startPipeline(asList(ls, grepPdf));

		System.out.println("Started processes...");

		CompletableFuture[] lsThenGrepFutures = lsThenGrep.stream()
				// onExit returns a CompletableFuture<Process>
				.map(Process::onExit)
				.map(processFuture -> processFuture.thenAccept(
						process -> System.out.println("Process " + process.pid() + " finished.")))
				.toArray(CompletableFuture[]::new);
		// wait until all processes are finished
		CompletableFuture
				.allOf(lsThenGrepFutures)
				.join();
	}

}
