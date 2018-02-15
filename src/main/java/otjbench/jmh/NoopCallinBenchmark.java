package otjbench.jmh;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import otjbench.noop.BaseType;
import otjbench.noop.NoopTeam;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class NoopCallinBenchmark extends BenchmarkDefaults {
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

    @TearDown(Level.Trial)
    public void teardown() {
        myTeam.deactivate();
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
