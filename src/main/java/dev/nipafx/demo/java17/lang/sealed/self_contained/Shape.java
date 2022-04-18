package dev.nipafx.demo.java17.lang.sealed.self_contained;

/**
 * If a sealed type's source file contain all its intended
 * implementations, no `permits` clause is needed
 */
sealed interface Shape { }

non-sealed interface Circle extends Shape { }

non-sealed interface Triangle extends Shape { }

non-sealed interface Rectangle extends Shape { }
