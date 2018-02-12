package otjbench.noop;

/**
 * @author martinmo
 *
 */
public team class NoopTeam {
	public class NoopRole playedBy Person {
		callin String sayHello() {
			return base.sayHello();
		}
		sayHello <- replace sayHello;

		callin String sayHelloTo(Person otherPerson) {
			return base.sayHelloTo(otherPerson);
		}
		sayHelloTo <- replace sayHelloTo;
	}
}
