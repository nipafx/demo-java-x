package org.codefx.demo.java9.api.processes;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

import static java.util.Arrays.asList;

class PipeProcessesAndAwaitCompletion {

	public static void main(String[] args) throws IOException, InterruptedException {
		CountDownLatch latch = new CountDownLatch(2);

		ProcessBuilder ls = new ProcessBuilder()
				.command("ls")
				.directory(Paths.get("/home/nipa/tmp").toFile());
		ProcessBuilder grepPdf = new ProcessBuilder()
				.command("grep", "pdf")
				.redirectOutput(Redirect.INHERIT);
		List<Process> lsThenGrep = ProcessBuilder.startPipeline(asList(ls, grepPdf));

		CompletableFuture[] lsThenGrepFutures = lsThenGrep.stream()
				// onExit returns a CompletableFuture<Process>
				.map(Process::onExit)
				.map(processFuture -> processFuture.thenAccept(
						process -> System.out.println("Process " + process.getPid() + " finished.")))
				.toArray(CompletableFuture[]::new);
		// wait until all processes are finished
		CompletableFuture
				.allOf(lsThenGrepFutures)
				.join();
	}

}
