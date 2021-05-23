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

import org.hamcrest.core.AllOf;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasSize;
import org.llorllale.cactoos.matchers.HasValues;
import org.llorllale.cactoos.matchers.Throws;

/**
 * Test cases for {@link Enumerable#drop}.
 *
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 * @since 0.1.0
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
final class DropTest {
    @Test
    void drop() {
        new Assertion<>(
            "Drops first 2 elements of the collection",
            new Linked<>(1, 2, 3, 4).drop(2),
            new AllOf<>(
                new HasSize(2),
                new HasValues<>(3, 4)
            )
        ).affirm();
    }

    @Test
    void zeroSize() {
        new Assertion<>(
            "In case of zero size, the original enumeration is expected",
            new Linked<>(1, 2, 3).drop(0),
            new AllOf<>(
                new HasSize(3),
                new HasValues<>(1, 2, 3)
            )
        ).affirm();
    }

    @Test
    void throwExceptionOnNegativeSize() {
        new Assertion<>(
            "Must throw IllegalArgumentException if the size value is negative",
            () -> new Linked<>(1, 2, 3).drop(-1),
            new Throws<>("-1", IllegalArgumentException.class)
        ).affirm();
    }
}
