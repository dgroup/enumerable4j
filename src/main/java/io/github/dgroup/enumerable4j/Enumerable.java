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

import java.util.Collection;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * The iterable with primitive operations witch simplify typical actions like count, map, etc.
 *
 * The API is based on Ruby's Enumerable:
 *  https://ruby-doc.org/core-2.6/Enumerable.html.
 *
 * The Enumerable provides methods with several traversal and searching features, and with the
 * ability to sort. The class must provide a method each, which yields successive members of the
 * collection.
 *
 * @param <X> The type of entities.
 * @since 0.1.0
 */
@SuppressWarnings("PMD.TooManyMethods")
public interface Enumerable<X> extends Collection<X> {

    /**
     * Passes each element of the collection to the each given function.
     * If no predicate (null) is given, then true is returned instead.
     * @param prd The array of functions to match each element.
     * @return True if the functions never return false or nil.
     */
    default boolean all(Predicate<X>... prd) {
        boolean match = true;
        if (prd != null) {
            for (int idx = 0; match && idx < prd.length; ++idx) {
                if (prd[idx] != null) {
                    match = this.stream().allMatch(prd[idx]);
                }
            }
        }
        return match;
    }

    /**
     * Passes at least one element of the collection to the each given function.
     * If no predicate (null) is given, then true is returned instead.
     * @param prd The array of functions to match at least one element.
     * @return True if the functions never return false or nil.
     */
    default boolean any(Predicate<X>... prd) {
        return !this.select(prd).isEmpty();
    }

    /**
     * Doesn't passes elements of the collection to the each given function.
     * If no predicate (null) is given, then true is returned instead.
     * @param prd The array of functions to match none elements.
     * @return True if the functions never returns false or nil.
     */
    default boolean none(Predicate<X>... prd) {
        boolean match = true;
        if (prd != null) {
            for (int idx = 0; match && idx < prd.length; ++idx) {
                if (prd[idx] != null) {
                    match = this.stream().noneMatch(prd[idx]);
                }
            }
        }
        return match;
    }

    /**
     * Returns an enumerable containing all elements of enumerable for which the given functions
     *  return a true value.
     * If no predicate (null) is given, then 'this' is returned instead.
     * @param prd The array of functions to match each element.
     * @return The enumerable.
     */
    default Enumerable<X> select(Predicate<X>... prd) {
        Enumerable<X> out = this;
        if (prd != null) {
            for (int idx = 0; !out.isEmpty() && idx < prd.length; ++idx) {
                if (prd[idx] != null) {
                    out = new Linked<>(
                        out.stream().filter(prd[idx]).collect(Collectors.toList())
                    );
                }
            }
        }
        return out;
    }

    /**
     * Returns an enumerable containing all elements of enumerable for which the given function
     *  returns a false value.
     * If no predicate (null) is given, then 'this' is returned instead.
     * @param prd The function to match each element.
     * @return The enumerable.
     */
    default Enumerable<X> reject(Predicate<X> prd) {
        final Enumerable<X> out;
        if (prd == null) {
            out = this;
        } else {
            out = new Linked<>(
                this.stream().filter(prd.negate()).collect(Collectors.toList())
            );
        }
        return out;
    }

    /**
     * Returns an enumerable containing first element of enumerable for which the given function
     *  returns a true value.
     * If no predicate (null) is given, or no element found then null is returned instead.
     * @param prd The function to match each element.
     * @return The first element of enumerable, that matches predicate.
     */
    default X find(Predicate<X> prd) {
        return this.find(prd, null);
    }

    /**
     * Returns an enumerable containing first element of enumerable for which the given function
     *  returns a true value.
     * If no predicate (null) is given, or no element found then alternative is returned instead.
     * @param prd The function to match each element.
     * @param alt The alternative to return in case of null predicate or no element found.
     * @return The first element of enumerable, that matches predicate.
     */
    default X find(Predicate<X> prd, X alt) {
        final X out;
        if (prd == null) {
            out = alt;
        } else {
            out = this.stream().filter(prd).findFirst().orElse(alt);
        }
        return out;
    }

    /**
     * Returns an enumerable containing all elements, on which given function was applied.
     * If no function (null) is given, then empty enumerable is returned instead.
     * @param fnc The function to apply to each element.
     * @param <Y> The type of target entity.
     * @return The enumerable.
     */
    default <Y> Enumerable<Y> map(Function<? super X, ? extends Y> fnc) {
        final Enumerable<Y> out;
        if (fnc == null) {
            out = new Empty<>();
        } else {
            out = new Linked<>(
                this.stream().map(fnc).collect(Collectors.toList())
            );
        }
        return out;
    }

    /**
     * Returns the number of elements that are present in enumerable for which the given
     * function return true.
     * If no function (null) is given, then 'size' is returned instead.
     * @param prd The function to match each element.
     * @return Number of elements satisfying the given function.
     */
    default long count(Predicate<X> prd) {
        final long count;
        if (prd == null) {
            count = this.size();
        } else {
            count = this.stream().filter(prd).count();
        }
        return count;
    }

    /**
     * Returns a result of the reduction of the elements in this stream,
     * using provided identity value and accumulation function operator.
     * If no function (null) is given, then identity is returned instead.
     * @param idn The identity value of the accumulation function.
     * @param opr The accumulation function operator which combining previous and current values.
     * @return Result of of combining elements.
     */
    default X reduce(X idn, BinaryOperator<X> opr) {
        final X res;
        if (opr == null) {
            res = idn;
        } else {
            res = this.stream().reduce(idn, opr);
        }
        return res;
    }

    /**
     * Returns an enumerable containing all elements of enumerable
     *  after the first one which corresponds the condition.
     * If no predicate (null) is given, then empty enumerable is returned instead.
     * @param prd The function to match element after which enumerable elements should be returned.
     * @return The enumerable.
     */
    default Enumerable<X> after(Predicate<X> prd) {
        return this.after(prd, this.size());
    }

    /**
     * Returns an enumerable containing a certain number of elements of enumerable
     *  after the first one which corresponds the condition.
     * If no predicate (null) is given, then empty enumerable is returned instead.
     * @param prd The function to match element after which enumerable elements should be returned.
     * @param size The number of elements the enumerable should be limited to.
     * @return The enumerable.
     * @throws IllegalArgumentException If the size is negative.
     */
    default Enumerable<X> after(Predicate<X> prd, long size) {
        final Enumerable<X> out;
        if (size < 0) {
            throw new IllegalArgumentException(Long.toString(size));
        } else if (size == 0 || prd == null) {
            out = new Empty<>();
        } else {
            boolean found = false;
            out = new Linked<>();
            long cnt = size;
            for (final X elem : this) {
                if (!found && prd.negate().test(elem)) {
                    continue;
                } else if (!found) {
                    found = true;
                    continue;
                }
                if (cnt > 0) {
                    --cnt;
                    out.add(elem);
                }
            }
        }
        return out;
    }

    /**
     * Returns the next element of enumerable after the first one which corresponds the condition.
     * If no predicate (null) is given, or no element found then null is returned instead.
     * @param prd The function to match element after which enumerable element should be returned.
     * @return The next element of enumerable after the first one which corresponds the condition.
     */
    default X next(Predicate<X> prd) {
        return this.next(prd, null);
    }

    /**
     * Returns the next element of enumerable after the first one which corresponds the condition.
     * If no predicate (null) is given, or no element found then alternative is returned instead.
     * @param prd The function to match element after which enumerable element should be returned.
     * @param alt The alternative to return in case of null predicate or no element found.
     * @return The next element of enumerable after the first one which corresponds the condition.
     */
    default X next(Predicate<X> prd, X alt) {
        final X out;
        if (prd == null) {
            out = alt;
        } else {
            out = this.after(prd, 1).stream().findFirst().orElse(alt);
        }
        return out;
    }

    /**
     * Returns a new enumerable which contains the items of the original collection
     *  and the added items of the given enumerable.
     * If no enumerable (null) is given, then 'this' is returned instead.
     * @param enm The given enumerable.
     * @return The enumerable.
     */
    default Enumerable<X> chain(Enumerable<X> enm) {
        final Enumerable<X> out = this;
        if (enm != null && !enm.isEmpty()) {
            out.addAll(enm);
        }
        return out;
    }

    /**
     * Returns an enumerable containing first elements of specified size from the enumerable.
     * @param size The number of elements the enumerable should be limited to.
     * @return The enumerable.
     * @throws IllegalArgumentException If the size is negative.
     */
    default Enumerable<X> take(long size) {
        final Enumerable<X> out;
        if (size < 0) {
            throw new IllegalArgumentException(Long.toString(size));
        } else if (size == 0) {
            out = new Empty<>();
        } else {
            out = new Linked<>(
                this.stream().limit(size).collect(Collectors.toList())
            );
        }
        return out;
    }

    /**
     * Returns an enumerable consisting of the elements of the collection,
     *  additionally performing the provided action on each element of the enumerable.
     * @param act An action to perform on the elements.
     * @return The enumerable.
     */
    default Enumerable<X> each(Consumer<X> act) {
        if (act != null && !this.isEmpty()) {
            this.forEach(act);
        }
        return this;
    }
}
