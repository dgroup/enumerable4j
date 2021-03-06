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
 * Test cases for {@link Enumerable#all}.
 *
 * @since 0.1.0
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
final class AllTest {

    @Test
    void all() {
        new Assertion<>(
            "All values in enumerable are positive",
            new Linked<>(1, 2, 3).all(val -> val > 0),
            new IsTrue()
        ).affirm();
    }

    @Test
    void negative() {
        new Assertion<>(
            "Not all values in enumerable are negative",
            new Linked<>(1, 2, 3).all(val -> val < 0),
            new IsEqual<>(false)
        ).affirm();
    }

    @Test
    void nullPredicate() {
        new Assertion<>(
            "In case of null predicate we get false",
            new Linked<>(1, 2, 3).all(null),
            new IsEqual<>(false)
        ).affirm();
    }

    @Test
    void varArgsAll() {
        final Predicate<Integer> positive = val -> val > 0;
        final Predicate<Integer> even = val -> (val & 1) == 0;
        final Predicate<Integer> lessthan = val -> val < 7;
        new Assertion<>(
            "All values in enumerable are positive, even and less than 7",
            new Linked<>(2, 4, 6).all(positive, even, lessthan),
            new IsTrue()
        ).affirm();
    }

    @Test
    void varArgsNegative() {
        final Predicate<Integer> positive = val -> val > 0;
        final Predicate<Integer> negative = val -> val < 0;
        new Assertion<>(
            "There are positive values in the enumerable, but there are no negative",
            new Linked<>(1, 2, 3).all(positive, negative),
            new IsEqual<>(false)
        ).affirm();
    }

    @Test
    void varArgsNullPredicates() {
        new Assertion<>(
            "In case of varargs null predicates we get false",
            new Linked<>(1, 2, 3).all(null, null, null),
            new IsEqual<>(false)
        ).affirm();
    }

    @Test
    void nullVarArgs() {
        new Assertion<>(
            "In case of null varargs we get false",
            new Linked<>(1, 2, 3).all(null, null),
            new IsEqual<>(false)
        ).affirm();
    }
}
