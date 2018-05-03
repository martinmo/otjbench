package otjbench.jmh;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;

import otjbench.noop.BaseType;
import otjbench.noop.NoopTeam;

@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
public class NoopBenchmark extends BenchmarkDefaults {
    @Param({ "1", "2", "3" })
    int numRoles;

    int x, y;
    BaseType b;
    NoopTeam[] myTeams;

    @Setup(Level.Iteration)
    public void setup() {
        b = new BaseType();
        myTeams = new NoopTeam[numRoles];
        for (int i = 0; i < numRoles; i++) {
            myTeams[i] = new NoopTeam();
            myTeams[i].activate();
        }
        Random random = new Random();
        x = random.nextInt();
        y = random.nextInt();
    }

    @TearDown(Level.Iteration)
    public void teardown() {
        for (NoopTeam team : myTeams) {
            team.deactivate();
        }
        // useful when JMH's "-gc" flag is used:
        b = null;
        myTeams = null;
    }

    @Benchmark
    public void baseline() {
        // intentionally left empty
    }

    @Benchmark
    public Object basecall_noargs() {
        return b.noArgs();
    }

    @Benchmark
    public Object basecall_withargs() {
        return b.referenceArgAndReturn(b);
    }

    @Benchmark
    public int basecall_primitiveargs() {
        return b.primitiveArgsAndReturn(x, y);
    }
}
