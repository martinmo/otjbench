package otjbench.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import otjbench.bank.Account;
import otjbench.bank.Bank;
import otjbench.bank.Person;
import otjbench.bank.Transaction;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Benchmark)
public class BankBenchmark {
	@Param("1500")
	int N;

	Bank bank;

	@Setup(Level.Iteration)
	public void setup() {
		bank = new Bank();
		bank.activate();

		for (int i = 0; i < N; ++i) {
			Person p = new Person();
			bank.addCustomer(p);
			Account sa = new Account(1000.0f);
			Account ca = new Account(1000.0f);
			bank.addSavingsAccount(p, sa);
			bank.addCheckingsAccount(p, ca);
		}
	}

	@TearDown(Level.Iteration)
	public void teardown() {
		bank.deactivate();
	}

	@Benchmark
	public boolean process_transactions_NxN() {
		for (Account from : bank.getCheckingAccounts()) {
			float amount = from.getBalance() / N;
			for (Account to : bank.getSavingAccounts()) {
				Transaction.Source source = new Transaction.Source(from);
				Transaction.Target target = new Transaction.Target(to);
				Transaction.execute(source, target, amount);
			}
		}
		return true;
	}

}
