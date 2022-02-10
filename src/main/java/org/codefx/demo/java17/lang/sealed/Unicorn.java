package org.codefx.demo.java17.lang.sealed;

/**
 * Because records are implicitly final, they are a convenient way
 * to implement/extend a sealed type.
 */
public record Unicorn(String name, double area) implements Shape {

}
