/*
 * MIT License
 *
 * Copyright (c) 2019-2021 Yurii Dubinka
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"),
 * to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom
 * the Software is  furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE
 * OR OTHER DEALINGS IN THE SOFTWARE.
 */

package io.github.dgroup.enumerable4j;

import java.util.function.Predicate;
import org.hamcrest.core.AllOf;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasSize;
import org.llorllale.cactoos.matchers.HasValues;

/**
 * Test cases for {@link Enumerable#select}.
 *
 * @since 0.1.0
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
final class SelectTest {

    @Test
    void select() {
        new Assertion<>(
            "Positive values from enumerable found",
            new Linked<>(3, 0, 2, -1).select(val -> val > 0),
            new AllOf<>(
                new HasSize(2),
                new HasValues<>(3, 2)
            )
        ).affirm();
    }

    @Test
    void nullFunction() {
        new Assertion<>(
            "In case null-function the self enumerable is expected",
            new Linked<>(3, 0, 2, -1).select(null),
            new HasValues<>(3, 0, 2, -1)
        ).affirm();
    }

    @Test
    void varArgsSelect() {
        final Predicate<Integer> positive = val -> val > 0;
        final Predicate<Integer> even = val -> (val & 1) == 0;
        final Predicate<Integer> lessthan = val -> val < 5;
        new Assertion<>(
            "All values in enumerable are positive, even and less than 5",
            new Linked<>(-1, 0, 1, 2, 3, 4, 5, 6).select(positive, even, lessthan),
            new AllOf<>(
                new HasSize(2),
                new HasValues<>(2, 4)
            )
        ).affirm();
    }

    @Test
    void varArgsNullPredicates() {
        new Assertion<>(
            "In case null-function the self enumerable is expected",
            new Linked<>(3, 0, 2, -1).select(null, null, null),
            new HasValues<>(3, 0, 2, -1)
        ).affirm();
    }

    @Test
    void noArgs() {
        new Assertion<>(
            "In case of no-args invocation the self enumerable is expected",
            new Linked<>(1, 2, 3).select(),
            new HasValues<>(1, 2, 3)
        ).affirm();
    }
}
