package net.mmorgenstern.otjbench;

public class Person {
	private final String name;

	public Person(String name) {
		this.name = name;
	}

	public String sayHello() {
		return "Hi, I'm " + name + "!";
	}
}
