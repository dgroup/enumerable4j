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

package io.dgroup.enumerable4j;

import java.util.function.Function;
import java.util.stream.Collectors;
import org.cactoos.collection.CollectionEnvelope;
import org.cactoos.collection.Sticky;

/**
 * The immutable enumerable.
 *
 * @param <T> The type of entities.
 * @since 0.1.0
 */
public class EnumerableOf<T> extends CollectionEnvelope<T> implements Enumerable<T> {
    /**
     * Ctor.
     * @param src The source items.
     */
    @SafeVarargs
    public EnumerableOf(final T... src) {
        super(() -> new Sticky<>(src));
    }

    /**
     * Ctor.
     * @param src The source items.
     */
    public EnumerableOf(final Iterable<T> src) {
        super(() -> new Sticky<>(src));
    }

    @Override
    public final boolean all(final Function<T, Boolean> fnc) {
        return this.stream().allMatch(fnc::apply);
    }

    @Override
    public boolean any(final Function<T, Boolean> fnc) {
        return this.stream().anyMatch(fnc::apply);
    }

    @Override
    public boolean none(final Function<T, Boolean> fnc) {
        return this.stream().noneMatch(fnc::apply);
    }

    @Override
    public final Enumerable<T> select(final Function<T, Boolean> fnc) {
        final Enumerable<T> out;
        if (fnc == null) {
            out = this;
        } else {
            out = new EnumerableOf<>(this.stream().filter(fnc::apply).collect(Collectors.toList()));
        }
        return out;
    }
}
