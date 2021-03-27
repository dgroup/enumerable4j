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

import java.util.List;
import org.cactoos.list.ListEnvelope;
import org.cactoos.list.ListOf;

/**
 * The enumerable based on linked list.
 *
 * @param <X> The type of entities.
 * @since 0.1.0
 */
public class Linked<X> extends ListEnvelope<X> implements Enumerable<X> {

    /**
     * Ctor.
     * @param src The source items.
     */
    @SafeVarargs
    public Linked(final X... src) {
        this(new ListOf<>(src));
    }

    /**
     * Ctor.
     * @param src The source items.
     */
    public Linked(final Iterable<X> src) {
        this(new ListOf<>(src));
    }

    /**
     * Ctor.
     * @param src The source items.
     */
    public Linked(final List<X> src) {
        super(src);
    }
}
