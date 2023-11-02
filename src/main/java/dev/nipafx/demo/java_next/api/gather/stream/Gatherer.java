package dev.nipafx.demo.java_next.api.gather.stream;

import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Supplier;

public interface Gatherer<T, A, R> {

	Supplier<A> initializer();
	Integrator<A,T,R> integrator();
	BinaryOperator<A> combiner();
	BiConsumer<A, Downstream<? super R>> finisher();

	interface Integrator<A, T, R> {
		boolean integrate(A state, T element, Downstream<? super R> downstream);
	}

	interface Downstream<R> {
		boolean push(R element);
	}

	static <T, A, R> Gatherer<T, A, R> of(Integrator<A, T, R> integrator) {
		return new Gatherer<T, A, R>() {
			@Override
			public Supplier<A> initializer() {
				return () -> null;
			}

			@Override
			public Integrator<A, T, R> integrator() {
				return integrator;
			}

			@Override
			public BinaryOperator<A> combiner() {
				return (state1, state2) -> { throw new IllegalStateException(); };
			}

			@Override
			public BiConsumer<A, Downstream<? super R>> finisher() {
				return (state, downstream) -> { };
			}
		};
	}

	static <T, A, R> Gatherer<T, A, R> ofSequential(Supplier<A> initializer, Integrator<A, T, R> integrator) {
		return new Gatherer<T, A, R>() {
			@Override
			public Supplier<A> initializer() {
				return initializer;
			}

			@Override
			public Integrator<A, T, R> integrator() {
				return integrator;
			}

			@Override
			public BinaryOperator<A> combiner() {
				return (state1, state2) -> { throw new IllegalStateException(); };
			}

			@Override
			public BiConsumer<A, Downstream<? super R>> finisher() {
				return (state, downstream) -> { };
			}
		};
	}

	static <T, A, R> Gatherer<T, A, R> ofSequential(Supplier<A> initializer, Integrator<A, T, R> integrator, BiConsumer<A, Downstream<? super R>> finisher) {
		return new Gatherer<T, A, R>() {
			@Override
			public Supplier<A> initializer() {
				return () -> null;
			}

			@Override
			public Integrator<A, T, R> integrator() {
				return integrator;
			}

			@Override
			public BinaryOperator<A> combiner() {
				return (state1, state2) -> { throw new IllegalStateException(); };
			}

			@Override
			public BiConsumer<A, Downstream<? super R>> finisher() {
				return finisher;
			}
		};
	}

}
