package io.dgroup.enumerable4j;

import java.util.function.Function;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsTrue;

/**
 * Test cases for {@link EnumerableOf#any(Function)}.
 *
 * @since 0.1.0
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class AnyTest {
    @Test
    public void any() {
        new Assertion<>(
            "At least one element in enumerable is less than 0",
            new EnumerableOf<>(-1, 0, 1, 2).any(val -> val < 0),
            new IsTrue()
        ).affirm();
    }

    @Test
    public void negative() {
        new Assertion<>(
            "At least one value in enumerable is negative",
            new EnumerableOf<>(1, 2, 3).any(val -> val < 0),
            new IsEqual<>(false)
        ).affirm();
    }
}
