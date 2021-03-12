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

import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;

/**
 * Test cases for {@link EnumerableOf#count}.
 *
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 * @since 0.1.0
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
final class CountTest {
    @Test
    void truePredicate() {
        new Assertion<>(
            "One element in enumerable is less than 0",
            new EnumerableOf<>(-1, 0, 1, 2).count(val -> val < 0),
            new IsEqual<>(1L)
        ).affirm();
    }

    @Test
    void falsePredicate() {
        new Assertion<>(
            "No element in enumerable is less than 0",
            new EnumerableOf<>(7, 0, 1, 2).count(val -> val < 0),
            new IsEqual<>(0L)
        ).affirm();
    }

    @Test
    void noPredicate() {
        new Assertion<>(
            "In case of no predicate passed it will work as size",
            new EnumerableOf<>(1, 2, 3).count(),
            new IsEqual<>(3L)
        ).affirm();
    }

    @Test
    void nullPredicate() {
        new Assertion<>(
            "In case of null predicate it will work as size",
            new EnumerableOf<>(1, 2, 3).count(null),
            new IsEqual<>(3L)
        ).affirm();
    }
}
