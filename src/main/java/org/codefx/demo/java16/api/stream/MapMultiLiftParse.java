package org.codefx.demo.java16.api.stream;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Objects.requireNonNull;

public class MapMultiLiftParse {

	public static void main(String[] args) {
		MapMultiLiftParse multi = new MapMultiLiftParse();
		multi.parsePerson();
	}

	public void parsePerson() {
		"""
				{
					name: "Jane Doe",
					birthday: "2002-11-30"
				},
				{
					name: "John Doe",
					birthday: "2001-05-12"
				},
				{
					name: "Jekyll Doe",
					city: "Paris"
				}
				"""
				.lines()
				.mapMulti(new PersonParser()::parse)
				.forEach(Person::print);
	}

	private static class PersonParser {

		private static final Pattern VALUE_IN_LINE = Pattern.compile("\\w+: \"(.*)\"");
		private static final DateTimeFormatter BIRTHDAY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		private PersonBuilder builder;

		public void parse(String line, Consumer<Person> lift) {
			if (line.contains("{"))
				builder = new PersonBuilder();
			else if (line.contains("name"))
				builder.name(valueFrom(line));
			else if (line.contains("birthday"))
				builder.birthday(LocalDate.parse(valueFrom(line), BIRTHDAY_FORMATTER));
			else if (line.contains("city"))
				builder.city(valueFrom(line));
			else if (line.contains("}")) {
				lift.accept(builder.createPerson());
				builder = null;
			}
		}

		private static String valueFrom(String line) {
			Matcher matcher = VALUE_IN_LINE.matcher(line);
			if (!matcher.find())
				throw new IllegalArgumentException();
			return matcher.group(1);
		}

	}

	private static class PersonBuilder {

		private String name;
		private Optional<LocalDate> birthday = Optional.empty();
		private Optional<String> city = Optional.empty();

		public PersonBuilder name(String name) {
			this.name = name;
			return this;
		}

		public PersonBuilder birthday(LocalDate birthday) {
			this.birthday = Optional.of(birthday);
			return this;
		}

		public PersonBuilder city(String city) {
			this.city = Optional.of(city);
			return this;
		}

		public Person createPerson() {
			Person person = new Person(name, birthday, city);
			return person;
		}

	}

	record Person(String name, Optional<LocalDate> birthday, Optional<String> city) {

		Person {
			requireNonNull(name);
			requireNonNull(birthday);
			requireNonNull(city);
		}

		void print() {
			System.out.println(this);
		}

	}

}
