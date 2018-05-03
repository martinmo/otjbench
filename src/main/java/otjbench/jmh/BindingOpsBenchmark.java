package otjbench.jmh;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.infra.Blackhole;

import otjbench.noop.BaseType;
import otjbench.noop.NoopTeam;

@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class BindingOpsBenchmark extends BenchmarkDefaults {
    @Param({ "5" })
    int N;

    BaseType base;
    NoopTeam[] teams;

    @Setup(Level.Iteration)
    public void setup() {
        base = new BaseType();
        teams = new NoopTeam[N];
        for (int i = 0; i < N; i++) {
            teams[i] = new NoopTeam();
        }
    }

    @TearDown(Level.Iteration)
    public void teardown() {
        // useful for JMH's -gc flag:
        base = null;
    }

    @Benchmark
    public void bind_unbind_N_roles(Blackhole bh) {
        for (int i = 0; i < N; i++) {
            teams[i].activate();
        }
        for (int i = 0; i < N; i++) {
            teams[i].deactivate();
        }
    }
}
