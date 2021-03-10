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

import org.hamcrest.collection.IsEmptyCollection;
import org.hamcrest.core.IsNot;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasValues;

/**
 * Test cases for {@link EnumerableOf#map}.
 *
 * @since 0.1.0
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
final class MapTest {
    @Test
    void map() {
        new Assertion<>(
            "All numbers where multiplied on 10",
            new EnumerableOf<>(2, 3, 4).map(val -> val * 10),
            new HasValues<>(20, 30, 40)
        ).affirm();
    }

    @Test
    void nullFunction() {
        new Assertion<>(
            "All numbers are the same",
            new EnumerableOf<>(2, 3, 4).map(null),
            new IsEmptyCollection<>()
        ).affirm();
    }

    @Test
    void negative() {
        new Assertion<>(
            "All numbers converted to numbers squares",
            new EnumerableOf<>(0, 1, 2, 3).map(val -> val * val),
            new IsNot<>(
                new HasValues<>(5, 7, 9)
            )
        ).affirm();
    }
}
