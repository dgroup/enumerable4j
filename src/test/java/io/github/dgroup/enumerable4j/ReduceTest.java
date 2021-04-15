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
 * Test cases for {@link Enumerable#reduce}.
 *
 * @since 0.1.0
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
final class ReduceTest {
    @Test
    void sum() {
        new Assertion<>(
            "Sum of elements in enumerable",
            new Linked<>(-1, 0, 1, 2, 3).reduce(0, Integer::sum),
            new IsEqual<>(5)
        ).affirm();
    }

    @Test
    void multiplication() {
        new Assertion<>(
            "Multiplication of elements in enumerable",
            new Linked<>(-1, 1, 2, 3).reduce(1, (res, elem) -> res *= elem),
            new IsEqual<>(-6)
        ).affirm();
    }

    @Test
    void concatenation() {
        new Assertion<>(
            "Concatenation of string elements in enumerable",
            new Linked<>("b", "c", "d").reduce("a", String::concat),
            new IsEqual<>("abcd")
        ).affirm();
    }

    @Test
    void nullOperator() {
        new Assertion<>(
            "In case of null operator it returns the identity value",
            new Linked<>(1, 2, 3).reduce(10, null),
            new IsEqual<>(10)
        ).affirm();
    }
}
