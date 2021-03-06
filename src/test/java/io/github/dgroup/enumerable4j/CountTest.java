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

/**
 * Test cases for {@link Enumerable#count}.
 *
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 * @since 0.1.0
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
final class CountTest {
    @Test
    void count() {
        new Assertion<>(
            "One element in enumerable is less than 0",
            new Linked<>(-1, 0, 1, 2).count(val -> val < 0),
            new IsEqual<>(1L)
        ).affirm();
    }

    @Test
    void negative() {
        new Assertion<>(
            "No element in enumerable is less than 0",
            new Linked<>(7, 0, 1, 2).count(val -> val < 0),
            new IsEqual<>(0L)
        ).affirm();
    }

    @Test
    void nullPredicate() {
        new Assertion<>(
            "In case of null predicate we get 0",
            new Linked<>(1, 2, 3).count(null),
            new IsEqual<>(0L)
        ).affirm();
    }

    @Test
    void varArgsCount() {
        final Predicate<Integer> positive = val -> val > 0;
        final Predicate<Integer> odd = val -> (val & 1) == 1;
        new Assertion<>(
            "Two elements in enumerable are greater than 0 and odd",
            new Linked<>(-1, 0, 1, 2, 3).count(positive, odd),
            new IsEqual<>(2L)
        ).affirm();
    }

    @Test
    void varArgsNegative() {
        final Predicate<Integer> positive = val -> val > 0;
        final Predicate<Integer> odd = val -> (val & 1) == 1;
        new Assertion<>(
            "No element in enumerable are greater than 0 and odd",
            new Linked<>(0, 2, 4).count(positive, odd),
            new IsEqual<>(0L)
        ).affirm();
    }

    @Test
    void varArgsNullPredicate() {
        new Assertion<>(
            "In case of null predicate we get 0",
            new Linked<>(1, 2, 3).count(null, null, null),
            new IsEqual<>(0L)
        ).affirm();
    }
}
