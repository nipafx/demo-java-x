package dev.nipafx.demo.java18.javadoc;

/**
 * This class has a constructor and here's how you call it:
 * {@snippet class="SnippetDocsDemo" region="constructor"}
 *
 * <h2>Highlighting</h2>
 *
 * With highlights (matching by substring):
 * {@snippet class="SnippetDocsDemo" region="highlight-substring"}
 *
 * With highlights (matching by regex):
 * {@snippet class="SnippetDocsDemo" region="highlight-regex"}
 *
 * With highlights (in a single line):
 * {@snippet class="SnippetDocsDemo" region="highlight-single"}
 *
 * With highlights (in the entire region):
 * {@snippet class="SnippetDocsDemo" region="highlight-region"}
 *
 * With highlights (in bold):
 * {@snippet class="SnippetDocsDemo" region="highlight-bold"}
 *
 * With highlights (in italic):
 * {@snippet class="SnippetDocsDemo" region="highlight-italic"}
 *
 * With highlights (highlighted):
 * {@snippet class="SnippetDocsDemo" region="highlight-highlighted"}
 *
 * With highlights (customized highlighted):
 * {@snippet class="SnippetDocsDemo" region="highlight-scary"}
 *
 * <h2>Replacing</h2>
 *
 * Replacing code (matching by substring):
 * {@snippet class="SnippetDocsDemo" region="replace-substring"}
 *
 * <h2>Linking</h2>
 *
 * Linking words (matching by regex):
 * {@snippet class="SnippetDocsDemo" region="link-regex"}
 *
 * <h2>Other files</h2>
 *
 * That CSS from earlier:
 * {@snippet file="javadoc-highlight.css"}
 *
 * <h2>HTML IDs</h2>
 *
 * That CSS from earlier:
 * {@snippet file="javadoc-highlight.css" id="highlighted-style"}
 *
 * <h2>Inline</h2>
 *
 * Code in Javadoc:
 * {@snippet :
 * List<String> yay = List.of("Don't", "have to", "escape <>&");
 * }
 *
 * Code in Javadoc with check
 * {@snippet class="SnippetDocsDemo" region="inline-check" :
 * List<String> yay = List.of("Don't", "have to", "escape <>&", "!");
 * }
 */
public class SnippetDocs {

	/**
	 * The constructor (amazing documentation, I know!)
	 */
	 public SnippetDocs() { }

}
