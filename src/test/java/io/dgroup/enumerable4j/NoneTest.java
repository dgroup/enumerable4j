package io.dgroup.enumerable4j;

import java.util.function.Function;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsTrue;

/**
 * Test cases for {@link EnumerableOf#none(Function)}.
 *
 * @since 0.1.0
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class NoneTest {
    @Test
    public void none() {
        new Assertion<>(
            "There are no values in enumerable greater than 100",
            new EnumerableOf<>(-1, 2, 99).none(val -> val > 100),
            new IsTrue()
        ).affirm();
    }

    @Test
    public void negative() {
        new Assertion<>(
            "All values in enumerable are negative",
            new EnumerableOf<>(1, 2, 3).none(val -> val < 0),
            new IsEqual<>(true)
        ).affirm();
    }
}
