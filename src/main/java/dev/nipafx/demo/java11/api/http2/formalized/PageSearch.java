package dev.nipafx.demo.java11.api.http2.formalized;

import java.net.URI;
import java.util.List;

public interface PageSearch {

	List<Result> search(List<Search> searches);

	public class Search {

		private final URI url;
		private final String term;

		public Search(URI url, String term) {
			this.url = url;
			this.term = term;
		}

		public URI url() {
			return url;
		}

		public String urlEnd() {
			String url = this.url.toString();
			int lastSlashIndex = url.lastIndexOf('/');
			return url.substring(lastSlashIndex + 1);
		}

		public String term() {
			return term;
		}

		@Override
		public String toString() {
			return String.format("'%s' in '%s'?", term, urlEnd());
		}

	}

	public class Result {

		private final Search search;
		private final boolean contains;

		private Result(Search search, boolean contains) {
			this.search = search;
			this.contains = contains;
		}

		public static Result completed(Search search, boolean contains) {
			Result result = new Result(search, contains);
			System.out.println("   [DEBUG] Completed " + result);
			return result;
		}

		public static Result failed(Search search, Throwable exception) {
			Result result = new Result(search, false);
			System.err.println("   [ERROR] Error while searching " + search.url() + ": " + exception.getMessage());
			return result;
		}

		public Search search() {
			return search;
		}

		public boolean contains() {
			return contains;
		}

		@Override
		public String toString() {
			return String.format("'%s' in '%s': %s", search.term(), search.urlEnd(), contains);
		}

	}



}
