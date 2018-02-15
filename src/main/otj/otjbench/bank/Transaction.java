package otjbench.bank;

public class Transaction {
	public static class Source {
		private final Account account;

		public Source(Account account) {
			this.account = account;
		}

		public void withdraw(float amount) {
			account.decrease(amount);
		}
	}

	public static class Target {
		private final Account account;

		public Target(Account account) {
			this.account = account;
		}

		public void deposit(float amount) {
			account.increase(amount);
		}
	}

	private final Source source;
	private final Target target;

	public Transaction(Source source, Target target) {
		this.source = source;
		this.target = target;
	}

	public void execute(final float amount) {
		source.withdraw(amount);
		target.deposit(amount);
	}
}
