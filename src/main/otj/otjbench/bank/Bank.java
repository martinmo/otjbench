package otjbench.bank;

import java.util.List;
import java.util.ArrayList;

public team class Bank {
	private final List<Customer> customers = new ArrayList<>();
	private final List<Account> checkingAccounts = new ArrayList<>();
	private final List<Account> savingAccounts = new ArrayList<>();

	precedence SavingsAccount, CheckingsAccount;

	public class Customer playedBy Person {
		private final List<Account> accounts = new ArrayList<>();

		public void addAccount(Account account) {
			accounts.add(account);
		}
	}

	public List<Account> getSavingAccounts() {
		return savingAccounts;
	}

	public List<Account> getCheckingAccounts() {
		return checkingAccounts;
	}

	public void addCustomer(Person as Customer customer) {
		customers.add(customer);
	}

	public void addCheckingsAccount(Person as Customer c, Account as CheckingsAccount a) {
		c.addAccount(a);
		checkingAccounts.add(a);
	}

	public void addSavingsAccount(Person as Customer c, Account as SavingsAccount a) {
		c.addAccount(a);
		savingAccounts.add(a);
	}

	public class CheckingsAccount playedBy Account {
		private static final float LIMIT = 100.0f;

		callin void limited(float a) {
			// System.out.println("Checking limit...");
			if (a <= LIMIT) {
				base.limited(a);
			} else {
				throw new RuntimeException(a + "is over the limit " + LIMIT);
			}
		}

		void limited(float a) <- replace decrease(float a);
	}

	public class SavingsAccount playedBy Account {
		private static final float FEE = 0.1f;

		private float transactionFee(float a) {
			return a * FEE;
		}

		callin void withTransactionFee(float a) {
			// System.out.println("Calculating fee...");
			base.withTransactionFee(a - transactionFee(a));
		}

		void withTransactionFee(float a) <- replace increase(float a);
	}
}
