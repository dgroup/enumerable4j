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

import org.hamcrest.object.HasEqualValues;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;

/**
 * Test cases for {@link Enumerable#zip}.
 *
 * @since 0.1.0
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
final class ZipTest {

    @Test
    void zip() {
        new Assertion<>(
            "Returns enumerable of 3 inner collections of 2 element in each",
            new Linked<>(4, 5, 6).zip(new Linked<>(7, 8, 9)),
            new HasEqualValues<>(
                new Linked<Enumerable<Integer>>(
                    new Linked<>(4, 7),
                    new Linked<>(5, 8),
                    new Linked<>(6, 9)
                )
            )
        ).affirm();
    }

    @Test
    void varArgsZip() {
        new Assertion<>(
            "Returns enumerable of 3 inner collections of 3 element in each",
            new Linked<>(1, 2, 3).zip(new Linked<>(4, 5, 6), new Linked<>(7, 8, 9)),
            new HasEqualValues<>(
                new Linked<Enumerable<Integer>>(
                    new Linked<>(1, 4, 7),
                    new Linked<>(2, 5, 8),
                    new Linked<>(3, 6, 9)
                )
            )
        ).affirm();
    }

    @Test
    void differentSizes() {
        new Assertion<>(
            "The last values of argument collections are dropped",
            new Linked<>(1, 2).zip(new Linked<>(4, 5, 6), new Linked<>(7, 8, 9)),
            new HasEqualValues<>(
                new Linked<Enumerable<Integer>>(
                    new Linked<>(1, 4, 7),
                    new Linked<>(2, 5, 8)
                )
            )
        ).affirm();
    }

    @Test
    void nullInResult() {
        new Assertion<>(
            "A lack of elements is replaced with null",
            new Linked<>(4, 5, 6).zip(new Linked<>(1, 2), new Linked<>(8)),
            new HasEqualValues<>(
                new Linked<Enumerable<Integer>>(
                    new Linked<>(4, 1, 8),
                    new Linked<>(5, 2, null),
                    new Linked<>(6, null, null)
                )
            )
        ).affirm();
    }

    @Test
    void nullInArgs() {
        new Assertion<>(
            "A lack of elements is replaced with null",
            new Linked<>(1, 2, 3).zip(null, new Linked<>(8)),
            new HasEqualValues<>(
                new Linked<Enumerable<Integer>>(
                    new Linked<>(1, null, 8),
                    new Linked<>(2, null, null),
                    new Linked<>(3, null, null)
                )
            )
        ).affirm();
    }

    @Test
    void nullInArgCollection() {
        new Assertion<>(
            "A lack of elements is replaced with null",
            new Linked<>(1, 2, 3).zip(new Linked<>(null, null), new Linked<>(8)),
            new HasEqualValues<>(
                new Linked<Enumerable<Integer>>(
                    new Linked<>(1, null, 8),
                    new Linked<>(2, null, null),
                    new Linked<>(3, null, null)
                )
            )
        ).affirm();
    }
}
