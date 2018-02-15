package otjbench.jmh;

import otjbench.bank.Account;
import otjbench.bank.Bank;
import otjbench.bank.CallinTransaction;
import otjbench.bank.CalloutTransaction;
import otjbench.bank.Person;

public class BankBenchmark {

	private Bank bank;
	private String optionalArgument;

	public boolean innerBenchmarkLoop(final int innerIterations) {
		bank.activate();
		for (Account from : bank.getCheckingAccounts()) {
			float amount = from.getBalance() / innerIterations;
			for (Account to : bank.getSavingAccounts()) {
				if(optionalArgument.equals("CALLIN")) {
					CallinTransaction transaction = new CallinTransaction();
					transaction.activate();
					try {
						transaction.execute(from, to, amount);
					} catch (RuntimeException e) {
						// do nothing
					} finally {
						transaction.deactivate();
					}
				} else {
					CalloutTransaction transaction = new CalloutTransaction();
					transaction.activate();
					try {
						transaction.execute(from, to, amount);
					} catch (RuntimeException e) {
						// do nothing
					} finally {
						transaction.deactivate();
					}
				}
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
