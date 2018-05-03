package otjbench.jmh;

import java.math.BigDecimal;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import otjbench.bank_bigdec.Account;
import otjbench.bank_bigdec.Bank;
import otjbench.bank_bigdec.Person;
import otjbench.bank_bigdec.Transaction;

@Fork(warmups = 1)
@BenchmarkMode(Mode.SingleShotTime)
@State(Scope.Benchmark)
public class BigDecBankBenchmark {
	@Param("800")
	int N;

	BigDecimal nAsBigDecimal;
	Bank bank;

	@Setup(Level.Iteration)
	public void setup() {
		bank = new Bank();
		bank.activate();
		nAsBigDecimal = BigDecimal.valueOf(N);
		for (int i = 0; i < N; ++i) {
			Person p = new Person();
			bank.addCustomer(p);
			Account sa = new Account(1000);
			Account ca = new Account(1000);
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
			BigDecimal amount = from.getBalance().divide(nAsBigDecimal);
			for (Account to : bank.getSavingAccounts()) {
				Transaction transaction = new Transaction(new Transaction.Source(from), new Transaction.Target(to));
				transaction.execute(amount);
			}
		}
		return true;
	}

}
