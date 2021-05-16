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

/**
 * Test cases for {@link Enumerable#chain}.
 *
 * @since 0.1.0
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
final class ChainTest {
    @Test
    void nullParameter() {
        new Assertion<>(
            "In case of null enumerable the same enumerable is expected",
            new Linked<>(1, 2, 3).chain(null),
            new AllOf<>(
                new HasSize(3),
                new HasValues<>(1, 2, 3)
            )
        ).affirm();
    }

    @Test
    void emptyParameter() {
        new Assertion<>(
            "If add an empty collection to the enumerable, the same enumerable is expected",
            new Linked<>(1, 2, 3).chain(new Empty<>()),
            new AllOf<>(
                new HasSize(3),
                new HasValues<>(1, 2, 3)
            )
        ).affirm();
    }

    @Test
    void chain() {
        new Assertion<>(
            "Add values to the enumerable",
            new Linked<>(1, 2, 3).chain(new Linked<>(4, 5)),
            new AllOf<>(
                new HasSize(5),
                new HasValues<>(1, 2, 3, 4, 5)
            )
        ).affirm();
    }

    @Test
    void manyChains() {
        new Assertion<>(
            "Add some chains of values to the enumerable",
            new Linked<>(1, 2)
                .chain(new Linked<>(3))
                .chain(new Empty<>())
                .chain(new Linked<>(4, 5)),
            new AllOf<>(
                new HasSize(5),
                new HasValues<>(1, 2, 3, 4, 5)
            )
        ).affirm();
    }

}
