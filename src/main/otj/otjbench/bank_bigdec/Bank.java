package otjbench.bank_bigdec;

import java.util.List;
import java.math.BigDecimal;
import java.util.ArrayList;

public team class Bank {
	private List<Customer> customers;
	private List<Account> checkingAccounts;
	private List<Account> savingAccounts;

	precedence SavingsAccount,CheckingsAccount;

	public class Customer playedBy Person {
		private List<Account> accounts;

		public void addAccount(Account account) {
			if (accounts == null) {
				accounts = new ArrayList<>();
			}
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
		if (customers == null) {
			customers = new ArrayList<>();
		}
		customers.add(customer);
	}

	public void addCheckingsAccount(Person as Customer c, Account as CheckingsAccount a) {
		c.addAccount(a);
		if (checkingAccounts == null) {
			checkingAccounts = new ArrayList<>();
		}
		checkingAccounts.add(a);
	}

	public void addSavingsAccount(Person as Customer c, Account as SavingsAccount a) {
		c.addAccount(a);
		if (savingAccounts == null) {
			savingAccounts = new ArrayList<>();
		}
		savingAccounts.add(a);
	}

	private static final BigDecimal LIMIT = BigDecimal.valueOf(100);

	public class CheckingsAccount playedBy Account {
		callin void limited(BigDecimal a) {
			// System.out.println("Checking limit...");
			if (a.compareTo(LIMIT) <= 0) {
				base.limited(a);
			} else {
				throw new RuntimeException(a + "is over the limit " + LIMIT);
			}
		}

		void limited(BigDecimal a) <- replace decrease(BigDecimal a);

	}

	private static final BigDecimal FEE = new BigDecimal("0.1");

	public class SavingsAccount playedBy Account {


		private BigDecimal transactionFee(BigDecimal a) {
			return a.multiply(FEE);
		}

		callin void withTransactionFee(BigDecimal a) {
			// System.out.println("Calculating fee...");
			base.withTransactionFee(a.subtract(transactionFee(a)));
		}

		void withTransactionFee(BigDecimal a) <- replace increase(BigDecimal a);
	}
}
