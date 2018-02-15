package otjbench.jmh;

import otjbench.bank.Account;
import otjbench.bank.Bank;
import otjbench.bank.Person;
import otjbench.bank.Transaction;

public class BankBenchmark {

	private Bank bank;

	public boolean innerBenchmarkLoop(final int innerIterations) {
		bank.activate();
		for (Account from : bank.getCheckingAccounts()) {
			float amount = from.getBalance() / innerIterations;
			for (Account to : bank.getSavingAccounts()) {
				Transaction.Source source = new Transaction.Source(from);
				Transaction.Target target = new Transaction.Target(to);
				Transaction.execute(source, target, amount);
			}
		}
		bank.deactivate();
		return true;
	}

	public boolean setUp(final int innerIterations) {
		bank = new Bank();
		bank.activate();

		for (int i = 0; i < innerIterations; ++i) {
			Person p = new Person();
			bank.addCustomer(p);

			Account sa = new Account(1000.0f);
			Account ca = new Account(1000.0f);
			bank.addSavingsAccount(p, sa);
			bank.addCheckingsAccount(p, ca);
		}
		return true;
	}
}
