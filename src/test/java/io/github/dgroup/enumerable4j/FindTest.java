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
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;

/**
 * Test cases for {@link Enumerable#find}.
 *
 * @since 0.1.0
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
final class FindTest {
    @Test
    void find() {
        new Assertion<>(
            "First number bigger than 0",
            new Linked<>(-1, 0, 1, 2, 3).find(val -> val > 0),
            new IsEqual<>(1)
        ).affirm();
    }

    @Test
    void nullPredicate() {
        new Assertion<>(
            "In case of null predicate we will get the same enumerable",
            new Linked<>(1, 2, 3).find(null),
            new IsNull<>()
        ).affirm();
    }

    @Test
    void negative() {
        new Assertion<>(
            "There are no prime numbers",
            new Linked<>(2, 4, 6, 8).find(val -> val % 2 != 0),
            new IsNull<>()
        ).affirm();
    }

    @Test
    void altFind() {
        new Assertion<>(
            "First number bigger than 0",
            new Linked<>(-1, 0, 1, 2, 3).find(val -> val > 0, 100),
            new IsEqual<>(1)
        ).affirm();
    }

    @Test
    void altNullPredicate() {
        new Assertion<>(
            "In case of null predicate we will get the same enumerable",
            new Linked<>(1, 2, 3).find(null, 100),
            new IsEqual<>(100)
        ).affirm();
    }

    @Test
    void altNegative() {
        new Assertion<>(
            "There are no prime numbers",
            new Linked<>(2, 4, 6, 8).find(val -> val % 2 != 0, 777),
            new IsEqual<>(777)
        ).affirm();
    }
}
