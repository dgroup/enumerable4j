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
 * Test cases for {@link Enumerable#next}.
 *
 * @since 0.1.0
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
public class NextTest {

    @Test
    void nullPredicate() {
        new Assertion<>(
            "In a case of the null predicate, we get null as a result",
            new Linked<>(1, 2, 3).next(null),
            new IsNull<>()
        ).affirm();
    }

    @Test
    void altNullPredicate() {
        new Assertion<>(
            "In a case of the null predicate, we get an alternative result",
            new Linked<>(1, 2, 3).next(null, -1),
            new IsEqual<>(-1)
        ).affirm();
    }

    @Test
    void getNext() {
        new Assertion<>(
            "Returns the next element after the first one which corresponds the condition",
            new Linked<>(1, 2, 3, 4, 5).next(n -> n > 1),
            new IsEqual<>(3)
        ).affirm();
    }

    @Test
    void altGetNext() {
        new Assertion<>(
            "Returns the next element after the first one which corresponds the condition",
            new Linked<>(1, 2, 3, 4, 5).next(n -> n > 1, -1),
            new IsEqual<>(3)
        ).affirm();
    }

    @Test
    void noneMatch() {
        new Assertion<>(
            "No one element corresponds the condition, the result is null",
            new Linked<>(1, 2, 3).next(n -> n > 10),
            new IsNull<>()
        ).affirm();
    }

    @Test
    void altNoneMatch() {
        new Assertion<>(
            "No one element corresponds the condition, we get an alternative result",
            new Linked<>(1, 2, 3).next(n -> n > 10, -1),
            new IsEqual<>(-1)
        ).affirm();
    }

}
