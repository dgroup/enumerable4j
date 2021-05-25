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

import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.hamcrest.core.AllOf;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.HasSize;
import org.llorllale.cactoos.matchers.HasValues;

/**
 * Test cases for {@link Joined}.
 *
 * @since 0.1.0
 * @checkstyle MagicNumberCheck (100 lines)
 * @checkstyle JavadocMethodCheck (100 lines)
 */
@SuppressWarnings("PMD.AvoidDuplicateLiterals")
final class JoinedTest {

    @Test
    void join() {
        final Predicate<Integer> even = val -> (val & 1) == 0;
        final Predicate<Integer> positive = val -> val > 0;
        final Joined<Integer> joined = new Joined<>(even, positive);
        new Assertion<>(
            "Joined provided predicates",
            Stream.of(-2, 2, 3, 4).filter(joined).collect(Collectors.toList()),
            new AllOf<>(
                new HasSize(2),
                new HasValues<>(2, 4)
            )
        ).affirm();
    }

    @Test
    void negateJoin() {
        final Predicate<Integer> odd = val -> (val & 1) == 1;
        final Predicate<Integer> negative = val -> val < 0;
        final Joined<Integer> joined = new Joined<>(odd.negate(), negative.negate());
        new Assertion<>(
            "Joined provided predicates with negation",
            Stream.of(-2, 0, 2, 3, 4).filter(joined).collect(Collectors.toList()),
            new AllOf<>(
                new HasSize(3),
                new HasValues<>(0, 2, 4)
            )
        ).affirm();
    }

    @Test
    void nullPredicate() {
        final Joined<Integer> joined = new Joined<>(null, null);
        new Assertion<>(
            "In case of null-predicates, we get the true-returned predicate",
            Stream.of(-2, -1, 0, 1, 2).filter(joined).collect(Collectors.toList()),
            new AllOf<>(
                new HasSize(5),
                new HasValues<>(-2, -1, 0, 1, 2)
            )
        ).affirm();
    }

    @Test
    void nullPredicates() {
        final Joined<Integer> joined = new Joined<>(null, null, null);
        new Assertion<>(
            "In case of null-predicates, we get the true-returned predicate",
            Stream.of(-2, -1, 0, 1, 2).filter(joined).collect(Collectors.toList()),
            new AllOf<>(
                new HasSize(5),
                new HasValues<>(-2, -1, 0, 1, 2)
            )
        ).affirm();
    }

    @Test
    void orJoin() {
        final Predicate<Integer> odd = val -> (val & 1) == 1;
        final Predicate<Integer> lessthan = val -> val < 3;
        final Predicate<Integer> zero = val -> val == 0;
        final Joined<Integer> joined = new Joined<>(odd.or(zero), lessthan);
        new Assertion<>(
            "Joined the predicates to get values which are less than 3, and both odd or zero",
            Stream.of(-1, 0, 1, 2, 3).filter(joined).collect(Collectors.toList()),
            new AllOf<>(
                new HasValues<>(-1, 0, 1)
            )
        ).affirm();
    }

}
