package dev.nipafx.demo.java16.api.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

public class MapMultiGroupVisitors {

	public static void main(String[] args) {
		List<Visitor> visitors = List.of(
				new Visitor("Abraham Takahashi"),
				new Visitor("Kazumi Michelakis"),
				new Visitor("Aneko Kim"),
				new Visitor("Motoko Windrider"),
				new Visitor("Mahir Watanabe"));

		System.out.println("GROUP LATE");
		visitors.stream()
				.mapMulti(new VisitorGroupCoordinator(3)::groupLate)
				.forEach(System.out::println);

		System.out.println("GROUP EARLY");
		visitors.stream()
				.mapMulti(new VisitorGroupCoordinator(3)::groupEarly)
				.forEach(System.out::println);

		System.out.println("GROUP EARLY - COMPLETE");
		visitors.stream()
				.mapMulti(new VisitorGroupCoordinator(3)::groupEarly)
				.collect(toList())
				.forEach(System.out::println);
	}

	private static class VisitorGroupCoordinator {

		private final int visitorsPerGroup;
		private List<Visitor> visitors;

		private VisitorGroupCoordinator(int visitorsPerGroup) {
			this.visitorsPerGroup = visitorsPerGroup;
			this.visitors = new ArrayList<>();
		}

		public void groupEarly(Visitor visitor, Consumer<VisitorGroup> downstream) {
			visitors.add(visitor);
			if (visitors.size() == 1)
				downstream.accept(new VisitorGroup(visitors));
			else if (visitors.size() == visitorsPerGroup)
				visitors = new ArrayList<>();
		}

		public void groupLate(Visitor visitor, Consumer<VisitorGroup> downstream) {
			visitors.add(visitor);
			if (visitors.size() == visitorsPerGroup) {
				downstream.accept(new VisitorGroup(visitors));
				visitors = new ArrayList<>();
			}
		}

	}

	record VisitorGroup(List<Visitor> visitors) {

		VisitorGroup {
			// a defensive copy would break `groupEarly`
			requireNonNull(visitors);
		}

	}

	record Visitor(String name) {

		Visitor {
			requireNonNull(name);
		}

	}

}
