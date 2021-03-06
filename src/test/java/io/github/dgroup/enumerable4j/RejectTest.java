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
import org.hamcrest.collection.IsEmptyIterable;
import org.hamcrest.core.AllOf;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasSize;
import org.llorllale.cactoos.matchers.HasValues;

/**
 * Test cases for {@link Enumerable#reject}.
 *
 * @since 0.1.0
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
final class RejectTest {

    @Test
    void reject() {
        new Assertion<>(
            "Negative values from enumerable found",
            new Linked<>(1, 2, 3, -1).reject(val -> val > 0),
            new AllOf<>(
                new HasSize(1),
                new HasValues<>(-1)
            )
        ).affirm();
    }

    @Test
    void negative() {
        new Assertion<>(
            "All values from enumerable found",
            new Linked<>(1, 2, 3).reject(val -> val > 0),
            new IsEmptyIterable<>()
        ).affirm();
    }

    @Test
    void nullFunction() {
        new Assertion<>(
            "In case null-function the self enumerable is expected",
            new Linked<>(3, 0, 2, -1).reject(null),
            new HasValues<>(3, 0, 2, -1)
        ).affirm();
    }

    @Test
    void varArgsReject() {
        final Predicate<Integer> negative = val -> val < 0;
        final Predicate<Integer> even = val -> (val & 1) == 0;
        new Assertion<>(
            "Negative values from enumerable found",
            new Linked<>(1, 2, 3, -1).reject(negative, even),
            new AllOf<>(
                new HasSize(2),
                new HasValues<>(1, 3)
            )
        ).affirm();
    }

    @Test
    void varArgsNegative() {
        final Predicate<Integer> negative = val -> val < 0;
        final Predicate<Integer> even = val -> (val & 1) == 0;
        new Assertion<>(
            "No values from enumerable found",
            new Linked<>(2, 4, 6).reject(negative, even),
            new IsEmptyIterable<>()
        ).affirm();
    }

    @Test
    void varArgsNullFunction() {
        new Assertion<>(
            "In case null-function the self enumerable is expected",
            new Linked<>(3, 0, 2, -1).reject(null, null, null),
            new HasValues<>(3, 0, 2, -1)
        ).affirm();
    }

    @Test
    void nullVarArgs() {
        new Assertion<>(
            "In null-vararg case the self enumerable is expected",
            new Linked<>(3, 0, 2, -1).reject(null, null),
            new HasValues<>(3, 0, 2, -1)
        ).affirm();
    }
}
