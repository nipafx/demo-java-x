package org.codefx.demo.java18.jvm.javadoc;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SnippetDocsDemo {

	@Test
	void constructorDemo() {
		// @start region="constructor"
		// How to call the parameterless constructor
		SnippetDocs docs = new SnippetDocs();
		// @end

		assertNotNull(docs);
	}

}
