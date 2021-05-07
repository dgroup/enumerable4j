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
 * Test cases for {@link Enumerable#after}.
 *
 * @since 0.1.0
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public final class AfterTest {

    @Test
    void nullPredicate() {
        new Assertion<>(
            "In case null-function the self enumerable is expected",
            new Linked<>(1, 2, 3, 4).after(null),
            new AllOf<>(
                new HasSize(4),
                new HasValues<>(1, 2, 3, 4)
            )
        ).affirm();
    }

    @Test
    void allAfter() {
        new Assertion<>(
            "Returns elements after the first one which corresponds the condition",
            new Linked<>(1, 2, 3, 4, 5).after(n -> n > 1),
            new AllOf<>(
                new HasSize(3),
                new HasValues<>(3, 4, 5)
            )
        ).affirm();
    }

    @Test
    void firstTwoAfter() {
        new Assertion<>(
            "Returns first 2 elements after the first one which corresponds the condition",
            new Linked<>("a", "b", "c", "d", "e").after(s -> s.equals("b"), 2),
            new AllOf<>(
                new HasSize(2),
                new HasValues<>("c", "d")
            )
        ).affirm();
    }

    @Test
    void firstTenAfter() {
        new Assertion<>(
            "The specified size can be greater than an actual enumerable size",
            new Linked<>(1, 2, 3, 4, 5).after(n -> n == 2, 10),
        new AllOf<>(
            new HasSize(3),
            new HasValues<>(3, 4, 5)
        )
        ).affirm();
    }

    @Test
    void allAfterFirst() {
        new Assertion<>(
            "The first element of enumerable corresponds the condition",
            new Linked<>(1, 2, 3, 4, 5).after(n -> n < 10),
        new AllOf<>(
            new HasSize(4),
            new HasValues<>(2, 3, 4, 5)
        )
        ).affirm();
    }

    @Test
    void noneMatch() {
        new Assertion<>(
            "No one element corresponds the condition, an empty collection is returned",
            new Linked<>(1, 2, 3).after(n -> n < 0),
            new IsEmptyIterable<>()
        ).affirm();
    }

    @Test
    void zeroSize() {
        new Assertion<>(
            "If the size value is 0, an empty collection is returned",
            new Linked<>(1, 2, 3).after(n -> n < 10, 0),
            new IsEmptyIterable<>()
        ).affirm();
    }

    @Test
    void nullPredicateWithSize() {
        new Assertion<>(
            "In case null-function and specified size a enumerable of the size is expected",
            new Linked<>(1, 2, 3, 4).after(null, 2),
            new AllOf<>(
                new HasSize(2),
                new HasValues<>(1, 2)
            )
        ).affirm();
    }

    @Test
    void nullPredicateAndZeroSize() {
        new Assertion<>(
            "If the size value is 0, an empty collection is returned",
            new Linked<>(1, 2, 3).after(null, 0),
            new IsEmptyIterable<>()
        ).affirm();
    }

}
