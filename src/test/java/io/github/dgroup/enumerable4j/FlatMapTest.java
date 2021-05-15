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

import org.hamcrest.collection.IsEmptyIterable;
import org.hamcrest.core.AllOf;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasSize;
import org.llorllale.cactoos.matchers.HasValues;

/**
 * Test cases for {@link Enumerable#flatMap}.
 *
 * @checkstyle MagicNumberCheck (100 lines)
 * @checkstyle JavadocMethodCheck (100 lines)
 * @since 0.1.0
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
final class FlatMapTest {

    @Test
    void nullFunction() {
        final Enumerable<Linked<Integer>> enumerable = new Linked<>(
            new Linked<>(1, 2, 3),
            new Linked<>(4, 5)
        );
        new Assertion<>(
            "In case of a null function, an empty enumeration is expected",
            enumerable.flatMap(null),
            new IsEmptyIterable<>()
        ).affirm();
    }

    @Test
    void join() {
        final Enumerable<Linked<Integer>> enumerable = new Linked<>(
            new Linked<>(1, 2, 3),
            new Linked<>(4, 5)
        );
        new Assertion<>(
            "Join values of inner collections into one enumerable",
            enumerable.flatMap(Linked::new),
            new AllOf<>(
                new HasSize(5),
                new HasValues<>(1, 2, 3, 4, 5)
            )
        ).affirm();
    }

    @Test
    void multiply() {
        final Enumerable<Linked<Integer>> enumerable = new Linked<>(
            new Linked<>(1, 2, 3),
            new Linked<>(4, 5)
        );
        new Assertion<>(
            "All numbers are multiplied by 10",
            enumerable.flatMap(enm -> enm.map(elem -> elem * 10)),
            new AllOf<>(
                new HasSize(5),
                new HasValues<>(10, 20, 30, 40, 50)
            )
        ).affirm();
    }

}
