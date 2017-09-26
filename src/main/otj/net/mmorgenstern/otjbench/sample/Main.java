package net.mmorgenstern.otjbench.sample;

public class Main {
	public static void main(String[] args) {
		Person martin = new Person("Martin");
		System.out.println(martin.sayHello());
		NoopTeam myTeam = new NoopTeam();
		myTeam.activate();
		System.out.println(martin.sayHello());
	}
}
