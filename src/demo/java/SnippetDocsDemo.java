package org.codefx.demo.java18.jvm.javadoc;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

class SnippetDocsDemo {

	@Test
	void constructorDemo() {
		// @start region="constructor"
		// How to call the parameterless constructor:
		SnippetDocs docs = new SnippetDocs();
		// @end

		// assert correct behavior...
	}

	@Nested
	class HighlightDemo {

		@Test
		void substringDemo() {
			// @start region="highlight-substring"
			// it's similar to `new Object()`:
			SnippetDocs docs = new SnippetDocs(); // @highlight substring="new SnippetDocs()"
			// @end
		}

		@Test
		void regexDemo() {
			// @start region="highlight-regex"
			// it's similar to `new Object()`:
			SnippetDocs docs = new SnippetDocs(); // @highlight regex="SnippetDocs[^\s]*"
			// @end
		}

		@Test
		void singleDemo() {
			// @start region="highlight-single"
			// it's similar to `new Object()`:
			SnippetDocs docs = new SnippetDocs(); // @highlight substring="new"
			// @end
		}

		@Test
		void regionDemo() {
			// @start region="highlight-region" @highlight region substring="new"
			// it's similar to `new Object()`:
			SnippetDocs docs = new SnippetDocs();
			// @end @end
		}

		@Test
		void boldDemo() {
			// @start region="highlight-bold"
			// it's similar to `new Object()`:
			SnippetDocs docs = new SnippetDocs(); // @highlight substring="new SnippetDocs()" type="bold"
			// @end
		}

		@Test
		void italicDemo() {
			// @start region="highlight-italic"
			// it's similar to `new Object()`:
			SnippetDocs docs = new SnippetDocs(); // @highlight substring="new SnippetDocs()" type="italic"
			// @end
		}

		@Test
		void highlightedDemo() {
			// @start region="highlight-highlighted"
			// it's similar to `new Object()`:
			SnippetDocs docs = new SnippetDocs(); // @highlight substring="new SnippetDocs()" type="highlight"
			// @end
		}

		@Test
		void customHighlightedDemo() {
			// @start region="highlight-scary"
			// styled with src/demo/css/javadoc-highlight.css
			SnippetDocs docs = new SnippetDocs(); // @highlight substring="new SnippetDocs()" type="scary"
			// @end
		}

	}

	@Nested
	class ReplaceDemo {

		@Test
		void substringDemo() {
			// @start region="replace-substring"
			// it's similar to `new Object()`:
			SnippetDocs docs = new SnippetDocs(); // @replace substring="SnippetDocs docs" replacement="..."
			// @end
		}

	}

	@Nested
	class LinkDemo {

		@Test
		void regexDemo() {
			// @start region="link-regex" @link region regex="new Object\(\)" target="Object#Object()"
			// it's similar to `new Object()`:
			SnippetDocs docs = new SnippetDocs();
			// @end @end
		}

	}

	@Nested
	class InlineCheckDemo {

		@Test
		void demo() {
			// for hybrid snippets (inline and an external file), the @start comment needs to end with
			// a semicolon or the snippet starts with a spurious newline
			// @start region="inline-check":
			List<String> yay = List.of("Don't", "have to", "escape <>&", "!");
			// @end
		}

	}

}
