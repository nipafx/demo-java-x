package dev.nipafx.demo.java9.lang.deprecated_imports;

// in Java 8 this import causes a compiler warning
// (try it with javac -d out src/dev/nipafx/demo/java9/lang/deprecated_imports/DeprecatedImports.java)
// even though this type is deprecated itself
import java.io.LineNumberInputStream;

@Deprecated
class DeprecatedImports {

	LineNumberInputStream stream;

}
