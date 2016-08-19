package org.codefx.demo.java9.lang.deprecated_imports;// in Java 8 this import causes a compiler warning
// even though this type is deprecated itself
import java.io.LineNumberInputStream;

@Deprecated
class DeprecatedImports {

	LineNumberInputStream stream;

}