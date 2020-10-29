package org.codefx.demo.java16.lang.instanceof_;

import java.util.List;

public class Zoo {

	private final List<Animal> animals = List.of(new Tiger(), new Elephant());

	public void feed() {
		animals.forEach(this::feed);
	}

	public void feed(Animal animal) {
		if (animal instanceof Tiger)
			((Tiger) animal).eatMeat();
		else if (animal instanceof Elephant)
			((Elephant) animal).eatPlants();
	}

	public void feedWithPatterns(Animal animal) {
		if (animal instanceof Tiger tiger)
			tiger.eatMeat();
		else if (animal instanceof Elephant elephant)
			elephant.eatPlants();
	}

	interface Animal { }

	static class Tiger implements Animal {

		public void eatMeat() {
			System.out.println("Grr, meat!");
		}

	}

	static class Elephant implements Animal {

		public void eatPlants() {
			System.out.println("Nom, nom, plants.");
		}

	}

}
