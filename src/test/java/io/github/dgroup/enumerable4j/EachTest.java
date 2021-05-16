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

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.cactoos.text.Joined;
import org.cactoos.text.TextOf;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.llorllale.cactoos.matchers.Assertion;
import org.llorllale.cactoos.matchers.TextIs;

/**
 * Test cases for {@link Enumerable#each}.
 *
 * @checkstyle MagicNumberCheck (500 lines)
 * @checkstyle JavadocMethodCheck (500 lines)
 * @checkstyle ClassDataAbstractionCouplingCheck (500 lines)
 * @checkstyle JavadocVariableCheck (500 lines)
 * @since 0.1.0
 */
@SuppressWarnings({"PMD.AvoidDuplicateLiterals", "PMD.StaticAccessToStaticFields",
    "PMD.SystemPrintln"})
final class EachTest {

    private static ByteArrayOutputStream captor;

    @BeforeEach
    void setUp() {
        captor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(captor));
    }

    @Test
    void nullParameter() {
        new Assertion<>(
            "In null-parameter case, no actions with the elements will be performed",
            new Linked<>(1, 2, 3).each(null),
            new IsEqual<>(new Linked<>(1, 2, 3))
        ).affirm();
    }

    @Test
    void print() {
        new Linked<>("a", "b", "c").each(System.out::println);
        new Assertion<>(
            "Print each element",
            new TextOf(captor.toString()),
            new TextIs(new Joined(System.lineSeparator(), "a", "b", "c", ""))
        ).affirm();
    }

    @Test
    void printEmpty() {
        new Empty<>().each(System.out::println);
        new Assertion<>(
            "In case of empty enumerable, no actions with the elements will be performed",
            new TextOf(captor.toString()),
            new TextIs("")
        ).affirm();
    }

    @Test
    void chain() {
        new Linked<>(1, 2, 3)
            .each(null)
            .each(System.out::println)
            .each(val -> System.out.println(val * 10));
        new Assertion<>(
            "The chain of invocations",
            new TextOf(captor.toString()),
            new TextIs(new Joined(System.lineSeparator(), "1", "2", "3", "10", "20", "30", ""))
        ).affirm();
    }

}
