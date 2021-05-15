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
 * Test cases for {@link Unique}.
 *
 * @since 0.1.0
 * @checkstyle MagicNumberCheck (100 lines)
 * @checkstyle JavadocMethodCheck (100 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
final class UniqueTest {

    @Test
    void varArgsConstructor() {
        final Unique<Integer> unique = new Unique<>(1, 2, 1, 3, 3);
        new Assertion<>(
            "Got unique collections of integers",
            unique,
            new AllOf<>(
                new HasSize(3),
                new HasValues<>(1, 2, 3)
            )
        ).affirm();
    }

    @Test
    void iterableArgConstructor() {
        final Unique<String> unique = new Unique<>(new Linked<>("a", "b", "b", "c", "a"));
        new Assertion<>(
            "Got unique collections of enumerables",
            unique,
            new AllOf<>(
                new HasSize(3),
                new HasValues<>("a", "b", "c")
            )
        ).affirm();
    }

}
