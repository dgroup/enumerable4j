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

import java.util.Collections;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.cactoos.collection.CollectionEnvelope;
import org.cactoos.collection.Immutable;
import org.cactoos.list.ListOf;

/**
 * The immutable enumerable.
 *
 * @param <T> The type of entities.
 * @since 0.1.0
 * @todo #/DEV Use empty enumerable in case if no func provided to method map.
 */
public class EnumerableOf<T> extends CollectionEnvelope<T> implements Enumerable<T> {
    /**
     * Ctor.
     * @param src The source items.
     */
    @SafeVarargs
    public EnumerableOf(final T... src) {
        super(new Immutable<>(new ListOf<>(src)));
    }

    /**
     * Ctor.
     * @param src The source items.
     */
    public EnumerableOf(final Iterable<T> src) {
        super(new Immutable<>(new ListOf<>(src)));
    }

    @Override
    public final boolean all(final Predicate<T> prd) {
        return this.stream().allMatch(prd);
    }

    @Override
    public final boolean any(final Predicate<T> prd) {
        return this.stream().anyMatch(prd);
    }

    @Override
    public final boolean none(final Predicate<T> prd) {
        return this.stream().noneMatch(prd);
    }

    @Override
    public final Enumerable<T> select(final Predicate<T> prd) {
        final Enumerable<T> out;
        if (prd == null) {
            out = this;
        } else {
            out = new EnumerableOf<>(this.stream().filter(prd).collect(Collectors.toList()));
        }
        return out;
    }

    @Override
    public final <Y> Enumerable<Y> map(final Function<? super T, ? extends Y> fnc) {
        final Enumerable<Y> out;
        if (fnc == null) {
            out = new EnumerableOf<>(Collections.emptyList());
        } else {
            out = new EnumerableOf<>(this.stream().map(fnc).collect(Collectors.toList()));
        }
        return out;
    }
}
