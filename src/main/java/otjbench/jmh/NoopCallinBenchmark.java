package otjbench.jmh;

import java.util.Random;
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
import otjbench.noop.BaseType;

@Fork(2)
@Warmup(iterations = 10)
@Measurement(iterations = 10)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class NoopCallinBenchmark {
    private int x, y;
    private BaseType b;
    private NoopTeam myTeam;

    @Setup(Level.Trial)
    public void setupTeam() {
        myTeam = new NoopTeam();
        myTeam.activate();
    }

    @Setup(Level.Iteration)
    public void setup() {
        Random random = new Random();
        b = new BaseType();
        x = random.nextInt();
        y = random.nextInt();
    }

    @Benchmark
    public void baseline() {
        // intentionally left empty
    }

    @Benchmark
    public Object callin_noargs() {
        return b.noArgs();
    }

    @Benchmark
    public Object callin_withargs() {
        return b.referenceArgAndReturn(b);
    }

    @Benchmark
    public int callin_primitiveargs() {
        return b.primitiveArgsAndReturn(x, y);
    }
}
