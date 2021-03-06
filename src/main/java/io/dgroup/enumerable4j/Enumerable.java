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

import java.util.Collection;
import java.util.function.Predicate;

/**
 * The immutable enumerable collection.
 *
 * The Enumerable provides methods with several traversal and searching features,
 *  and with the ability to sort. The class must provide a method each, which yields
 * successive members of the collection.
 *
 * This feature is ported from ruby language
 *  https://ruby-doc.org/core-2.6/Enumerable.html.
 *
 * @param <T> The type of entities.
 * @since 0.1.0
 */
public interface Enumerable<T> extends Collection<T> {

    /**
     * Passes each element of the collection to the given block.
     * @param prd The function to match each element.
     * @return The true if the block never returns false or nil.
     */
    boolean all(Predicate<T> prd);

    /**
     * Passes at least one element of the collection to the given block.
     * @param prd The function to match at least one element.
     * @return The true if the block never returns false or nil.
     */
    boolean any(Predicate<T> prd);

    /**
     * Doesn't passes elements of the collection to the given block.
     * @param prd The function to match none elements.
     * @return The true if the block never returns false or nil.
     */
    boolean none(Predicate<T> prd);

    /**
     * Returns an enumerable containing all elements of enumerable for which the given function
     *  returns a true value.
     * If no function (null) is given, then 'this' is returned instead.
     * @param prd The function to match each element.
     * @return The enumerable.
     */
    Enumerable<T> select(Predicate<T> prd);
}
