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

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * The class combines all predicates into one common predicate.
 *
 * @param <X> The type of entities.
 * @since 0.1.0
 */
public final class Joined<X> implements Predicate<X> {

    /**
     * The function that returns the result of executing a predicates for the provided value.
     */
    private final Function<X, Boolean> fnc;

    /**
     * Ctor.
     * The null predicates are skipped.
     * If all predicates are null, it contains the false-returned predicate instead.
     * @param first The source predicate.
     * @param other The source predicates.
     */
    @SafeVarargs
    @SuppressWarnings("PMD.ConstructorOnlyInitializesOrCallOtherConstructors")
    public Joined(final Predicate<X> first, final Predicate<X>... other) {
        this.fnc = val -> {
            Predicate<X> prd = v -> false;
            if (first != null) {
                prd = first;
            }
            if (other != null) {
                for (final Predicate<X> oth : other) {
                    if (oth != null) {
                        prd = prd.and(oth);
                    }
                }
            }
            return prd.test(val);
        };
    }

    @Override
    public boolean test(final X val) {
        return this.fnc.apply(val);
    }
}
