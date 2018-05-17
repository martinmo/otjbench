package otjbench.jmh;

import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Warmup;

@Fork(2)
@Warmup(iterations = 20)
@Measurement(iterations = 10)
@BenchmarkMode(Mode.AverageTime)
public abstract class BenchmarkDefaults {
}
