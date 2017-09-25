package net.mmorgenstern.otjbench.infra;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

import net.mmorgenstern.otjbench.NoopTeam;
import net.mmorgenstern.otjbench.Person;

@Fork(2)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class NoopCallin {
	private Person p;
	private NoopTeam myTeam;

	@Setup(Level.Trial)
	public void setupTeam() {
		myTeam = new NoopTeam();
		myTeam.activate();
	}

	@Setup(Level.Iteration)
	public void setup() {
		p = new Person("Martin");
	}

	@Benchmark
	public String callinTest() {
		return p.sayHello();
	}
}
