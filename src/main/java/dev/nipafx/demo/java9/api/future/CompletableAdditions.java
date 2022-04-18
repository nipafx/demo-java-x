package dev.nipafx.demo.java9.api.future;

public class CompletableAdditions {

	// In Java 9, all these methods were added to `CompletableFuture`
	//
	//     Executor defaultExecutor()
	//     CompletableFuture<T> copy()
	//     CompletableFuture<U> newIncompleteFuture()
	//     CompletionStage<T> minimalCompletionStage()
	//     CompletableFuture<T> completeAsync(Supplier<? extends T> supplier)
	//     CompletableFuture<T> completeAsync(Supplier<? extends T> supplier, Executor executor)
	//     CompletableFuture<T> orTimeout(long timeout, TimeUnit unit)
	//     CompletableFuture<T> completeOnTimeout(T value, long timeout, TimeUnit unit)
	//
	//     static Executor delayedExecutor(long delay, TimeUnit unit)
	//     static Executor delayedExecutor(long delay, TimeUnit unit, Executor executor)
	//     static <U> CompletionStage<U> completedStage(U value)
	//     static <U> CompletionStage<U> failedStage(Throwable ex)
	//     static <U> CompletableFuture<U> failedFuture(Throwable ex)
	//
	// I missed these additions back in 2017 and didn't create any examples for it.
	// I could now, but it's 2022, so we're all already on Java 17 and nobody cares, right? ;)

}
