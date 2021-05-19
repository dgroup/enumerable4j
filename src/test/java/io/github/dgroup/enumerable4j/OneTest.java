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
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.IsTrue;

/**
 * Test cases for {@link Enumerable#one}.
 *
 * @since 0.1.0
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
final class OneTest {

    @Test
    void one() {
        new Assertion<>(
            "Only one value is even",
            new Linked<>(1, 2, 3).one(val -> (val & 1) == 0),
            new IsTrue()
        ).affirm();
    }

    @Test
    void negative() {
        new Assertion<>(
            "Only one value is positive",
            new Linked<>(1, 2, 3).one(val -> val > 0),
            new IsEqual<>(false)
        ).affirm();
    }

    @Test
    void nullPredicate() {
        new Assertion<>(
            "In case of null predicate we will get true",
            new Linked<>(1, 2, 3).one(null),
            new IsTrue()
        ).affirm();
    }

    @Test
    void varArgsOne() {
        final Predicate<Integer> even = val -> (val & 1) == 1;
        final Predicate<Integer> lessthan = val -> val < 3;
        new Assertion<>(
            "Only one value is even and less than 3",
            new Linked<>(1, 2, 3, 4).one(even, lessthan),
            new IsTrue()
        ).affirm();
    }

    @Test
    void varArgsNullPredicates() {
        new Assertion<>(
            "All values correspond to the conditions",
            new Linked<>(1, 2, 3).one(null, null),
            new IsEqual<>(false)
        ).affirm();
    }

    @Test
    void varArgsNegative() {
        final Predicate<Integer> even = val -> (val & 1) == 1;
        final Predicate<Integer> greaterthan = val -> val > 3;
        new Assertion<>(
            "No one value corresponds to the conditions",
            new Linked<>(1, 2, 3).one(even, greaterthan),
            new IsEqual<>(false)
        ).affirm();
    }

    @Test
    void noArgs() {
        new Assertion<>(
            "All values correspond to non-provided conditions",
            new Linked<>(1, 2, 3).one(),
            new IsEqual<>(false)
        ).affirm();
    }
}
