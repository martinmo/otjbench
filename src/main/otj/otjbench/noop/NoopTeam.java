package otjbench.noop;

/**
 * @author martinmo
 *
 */
public team class NoopTeam {
    public class NoopRole playedBy BaseType {
        callin Object noArgs() {
            return base.noArgs();
        }

        callin Object referenceArgAndReturn(Object o) {
            return base.referenceArgAndReturn(o);
        }

        // arguments and return value will probably be boxed
        callin int primitiveArgsAndReturn(int x, int y) {
            return base.primitiveArgsAndReturn(x, y);
        }

        noArgs <- replace noArgs;
        referenceArgAndReturn <- replace referenceArgAndReturn;
        primitiveArgsAndReturn <- replace primitiveArgsAndReturn;
    }
}
