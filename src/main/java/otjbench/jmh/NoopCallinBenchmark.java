package otjbench.jmh;

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

import otjbench.noop.NoopTeam;
import otjbench.noop.Person;

@Fork(2)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class NoopCallinBenchmark {
	private Person p1, p2;
	private NoopTeam myTeam;

	@Setup(Level.Trial)
	public void setupTeam() {
		myTeam = new NoopTeam();
		myTeam.activate();
	}

	@Setup(Level.Iteration)
	public void setup() {
		p1 = new Person("Martin");
		p2 = new Person("Max");
	}

	@Benchmark
	public void baseline() {
		// intentionally left empty
	}

	@Benchmark
	public String callin_noargs() {
		return p1.sayHello();
	}

	@Benchmark
	public String callin_withargs() {
		return p1.sayHelloTo(p2);
	}
}
