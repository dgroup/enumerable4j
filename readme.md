[![Maven](https://img.shields.io/maven-central/v/io.github.dgroup/enumerable4j.svg)](https://mvnrepository.com/artifact/io.github.dgroup/enumerable4j)
[![Javadocs](http://www.javadoc.io/badge/io.github.dgroup/enumerable4j.svg)](http://www.javadoc.io/doc/io.github.dgroup/enumerable4j)
[![License: MIT](https://img.shields.io/github/license/mashape/apistatus.svg)](./license.txt)
[![Commit activity](https://img.shields.io/github/commit-activity/y/dgroup/enumerable4j.svg?style=flat-square)](https://github.com/dgroup/enumerable4j/graphs/commit-activity)
[![Hits-of-Code](https://hitsofcode.com/github/dgroup/enumerable4j)](https://hitsofcode.com/view/github/dgroup/enumerable4j)

[![CI](https://github.com/dgroup/enumerable4j/actions/workflows/build.yml/badge.svg)](https://github.com/dgroup/enumerable4j/actions/workflows/build.yml)
[![0pdd](http://www.0pdd.com/svg?name=dgroup/enumerable4j)](http://www.0pdd.com/p?name=dgroup/enumerable4j)
[![Dependency Status](https://requires.io/github/dgroup/enumerable4j/requirements.svg?branch=master)](https://requires.io/github/dgroup/enumerable4j/requirements/?branch=master)
[![Known Vulnerabilities](https://snyk.io/test/github/dgroup/enumerable4j/badge.svg)](https://snyk.io/org/dgroup/project/4a9f3433-7da5-4c24-985e-ee1d3077c458/?tab=dependencies\&vulns=vulnerable)

[![DevOps By Rultor.com](http://www.rultor.com/b/dgroup/enumerable4j)](http://www.rultor.com/p/dgroup/enumerable4j)
[![EO badge](http://www.elegantobjects.org/badge.svg)](http://www.elegantobjects.org/#principles)
[![We recommend IntelliJ IDEA](http://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

[![Qulice](https://img.shields.io/badge/qulice-passed-blue.svg)](http://www.qulice.com/)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=dgroup_enumerable4j\&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=dgroup_enumerable4j)
[![Codebeat Badge](https://codebeat.co/badges/ef2fc64b-b2cf-4cc5-8b01-c9b4baaae87a)](https://codebeat.co/projects/github-com-dgroup-enumerable4j-master)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/eb956780f5b34519ac193c204062acae)](https://www.codacy.com/gh/dgroup/enumerable4j/dashboard?utm_source=github.com\&utm_medium=referral\&utm_content=dgroup/enumerable4j\&utm_campaign=Badge_Grade)
[![Codecov](https://codecov.io/gh/dgroup/enumerable4j/branch/master/graph/badge.svg)](https://codecov.io/gh/dgroup/enumerable4j)

*   [Overview](#overview)

*   [How to use?](#how-to-use)
    *   [.all](#all)
    *   [.any](#any)
    *   [.none](#none)
    *   [.select](#select)
    *   [.map](#map)
    *   [.count](#count)
    *   [.reject](#reject)
    *   [.find](#find)
    *   [.reduce](#reduce)
    *   [.after](#after)

*   [How to contribute?](#how-to-contribute)

*   [Contributors](#contributors)

### Overview

**enumerable4j** is a Ruby's well known [Enumerable](https://ruby-doc.org/core-2.6/Enumerable.html)
ported to `java` as interface with set of default methods which simplify typical operations with collections.
```java
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
public interface Enumerable<X> extends Collection<X> {
    /**
     * Passes each element of the collection to the given block.
     * @param prd The predicate to match each element.
     * @return The true if the block never returns false or nil.
     */
    default boolean all(Predicate<T> prd) {
        // ...
    }

    /**
     * Passes at least one element of the collection to the given block.
     * @param prd The predicate to match at least one element.
     * @return The true if the block never returns false or nil.
     */
    default boolean any(Predicate<T> prd) {
        // ...
    }

    /**
     * Doesn't passes elements of the collection to the given block.
     * @param prd The predicate to match none elements.
     * @return The true if the block never returns false or nil.
     */
    default boolean none(Predicate<T> prd) {
        // ...
    }

    /**
     * Returns an enumerable containing all elements of enumerable for which the given function
     *  returns a true value.
     * If no predicate (null) is given, then 'this' is returned instead.
     * @param prd The function to match each element.
     * @return The enumerable.
     */
    default Enumerable<T> select(Predicate<T> prd) {
        // ...
    }

    /**
     * Returns an enumerable containing all elements of enumerable for which the given function
     *  returns a false value.
     * If no predicate (null) is given, then 'this' is returned instead.
     * @param prd The function to match each element.
     * @return The enumerable.
     */
    default Enumerable<T> reject(Predicate<T> prd) {
        // ...
    }

    /**
     * Returns an enumerable containing first element of enumerable for which the given function
     *  returns a true value.
     * If no predicate (null) is given, or no element found then null is returned instead.
     * @param prd The function to match each element.
     * @return The first element of enumerable, that matches predicate.
     */
    default T find(Predicate<T> prd) {
        // ...
    }

    /**
     * Returns an enumerable containing first element of enumerable for which the given function
     *  returns a true value.
     * If no predicate (null) is given, or no element found then alternative is returned instead.
     * @param prd The function to match each element.
     * @param alt The alternative to return in case of null predicate or no element found.
     * @return The first element of enumerable, that matches predicate.
     */
    default T find(Predicate<T> prd, T alt) {
        // ...
    }

    /**
     * Returns an enumerable containing all elements, on which given function was applied.
     * If no function (null) is given, then 'this' is returned instead.
     * @param fnc The function to apply to each element.
     * @param <Y> The type of target entity.
     * @return The enumerable.
     */
    default <Y> Enumerable<Y> map(Function<? super T, ? extends Y> fnc) {
        // ...
    }

    /**
     * Returns the number of elements that are present in enumerable for which the given
     * function return true.
     * If no function (null) is given, then 'size' is returned instead.
     * @param prd The function to match each element.
     * @return Number of elements satisfying the given function.
     */
    default long count(Predicate<T> prd) {
        // ...
    }

    /**
     * Returns a result of the reduction of the elements in this stream,
     * using provided identity value and accumulation function operator.
     * If no function (null) is given, then identity is returned instead.
     *
     * @param idn The identity value of the accumulation function.
     * @param opr The accumulation function operator which combining previous and current values.
     * @return Result of of combining elements.
     */
    default X reduce(X idn, BinaryOperator<X> opr) {
        // ...
    }

    /**
     * Returns an enumerable containing all elements of enumerable
     *  after the first one which corresponds the condition.
     * If no predicate (null) is given, then 'this' is returned instead.
     * @param prd The function to match element after which enumerable elements should be returned.
     * @return The enumerable.
     */
    default Enumerable<X> after(Predicate<X> prd) {
        // ...
    }

    /**
     * Returns an enumerable containing a certain number of elements of enumerable
     *  after the first one which corresponds the condition.
     * If no predicate (null) is given, then 'this' is returned instead.
     * @param prd The function to match element after which enumerable elements should be returned.
     * @param size The number of elements the enumerable should be limited to.
     * @return The enumerable.
     */
    default Enumerable<X> after(Predicate<X> prd, long size) {
        // ...
    }
}
```
See [more](./src/main/java/io/github/dgroup/enumerable4j/Enumerable.java).

### How to use

1.  Get the latest version [here](https://github.com/dgroup/enumerable4j/releases):
    ```xml
    <dependency>
      <groupId>io.github.dgroup</groupId>
      <artifactId>enumerable4j</artifactId>
      <version>${version}</version>
    </dependency>
    ```
2.  Assign the [Enumerable](./src/main/java/io/github/dgroup/enumerable4j/Enumerable.java) interface with default methods to your own collection
    ```java
    /**
     * The collection which you implemented in your project for some purposes.
     */
    public class YourOwnCollection<X> extends Collection<X> implements Enumerable<X> {
        //
    }
    ```
    You may (but not required) override the default implementations of methods from [Enumerable](./src/main/java/io/github/dgroup/enumerable4j/Enumerable.java) if needed.

3.  Java version required: 1.8+.
4.  Comparing matrix with other libs:
    
    enumerable4j (MIT) | Java 8 | [cactoos](https://github.com/yegor256/cactoos) (MIT) | [eclipse-collections]() (EDL) 
    |------ | ------ | ------ |------ |
    `.all(...)` | `.stream().allMatch(...)` | `new And<>(...,...).value()`| tbd |
    `.any(...)` | `.stream().anyMatch(...)` | `new Or<>(...,...).value()`| tbd |
    `.none(...)` | `.stream().noneMatch(...)` | `new And<>(...,...).value()`| tbd |
    `.select(...)` | `.stream().filter(...).collect(Collectors.toList())` | `new Filtered<>(...,...)` | tbd |
    `.reject(...)` | `.stream().filter((...).negate()).collect(Collectors.toList())` | `new Filtered<>(...,...)` | tbd |
    `.map(...)` | `.stream().map(...).collect(Collectors.toList())` | `new Mapped<>(...,...)` | tbd |
    `.count(...)` | `.stream().filter(...).count()` | `new Filtered<>(...).size()` | tbd |
    `.find(...)` | `.stream().filter(...).findFirst().orElse(...)` | `new FirstOf<>(...,...).value()` | tbd |
    `.reduce(...,...)` | `.stream().reduce(...,...)` | `new Reduced<>(...,...).value()` | tbd |
    `.after(...)` | | | tbd |

#### .all

```java
YourOwnCollection<Integer> src = ...                    // with elements [1, 2, 3]   
boolean allPositive = src.all(v -> v > 0);              // true 
```

#### .any

```java
YourOwnCollection<Integer> src = ...                    // with elements [-1, 0, 1]
boolean oneIsPositive = src.any(v -> v > 0);            // true 
```

#### .none

```java
YourOwnCollection<Integer> src = ...                    // with elements [-2, -1, 0]
boolean noneIsPositive = src.none(v -> v > 0);          // true 
```

#### .select

```java
YourOwnCollection<Integer> src = ...                    // with elements [-1, 1, 2]
Enumerable<Integer> positive = src.select(v -> v > 0);  // [1, 2] 
```

#### .reject

```java
YourOwnCollection<Integer> src = ...                    // with elements [-1, 1, 2]
Enumerable<Integer> negative = src.reject(v -> v > 0);  // [-1]
```

#### .map

```java
YourOwnCollection<Integer> src = ...                    // with elements [0, 1, 2]
Enumerable<Integer> positive = src.map(v -> v + 1);     // [1, 2, 3] 
```

#### .count

```java
YourOwnCollection<Integer> src = ...                    // with elements [-1, 0, 1]
long countNegative = src.count(val -> val < 0);         // 1
```

#### .find

```java
YourOwnCollection<Integer> src = ...                    // with elements [-1, 0, 1]
Integer first = src.find(val -> val > 0);               // 1 
Integer alternative = src.find(val -> val > 5, 50);     // 50                
```

#### .reduce

```java
YourOwnCollection<Integer> src = ...                    // with elements [1, 2, 3]   
Integer sum = src.reduce(0, Integer::sum);              // 6 
```

#### .after

```java
YourOwnCollection<Integer> src = ...                                    // with elements [2, 3, 4, 5, 6]
Enumerable<Integer> afterThree = src.after(val -> val == 3);            // [4, 5, 6] 
Enumerable<Integer> firstTwoAfterThree = src.after(val -> val == 3, 2); // [4, 5]                
```

### How to contribute?

[![EO badge](http://www.elegantobjects.org/badge.svg)](http://www.elegantobjects.org/#principles)

1.  Pull requests are welcome! Don't forget to add your name to contribution section and run this,
    beforehand:
    ```bash
    mvn -Pqulice clean install
    ```
2.  Everyone interacting in this project’s codebases, issue trackers, chat rooms is expected to
    follow the [code of conduct](.github/CODE_OF_CONDUCT.md).
3.  Latest maven coordinates [here](https://github.com/dgroup/enumerable4j/releases):
    ```xml
    <dependency>
        <groupId>io.github.dgroup</groupId>
        <artifactId>enumerable4j</artifactId>
        <version>${version}</version>
    </dependency>
    ```

### Contributors

*   [dgroup](https://github.com/dgroup) as Yurii Dubinka (<yurii.dubinka@gmail.com>)
*   [smithros](https://github.com/smithros) as Rostyslav Koval (<kovalr2000@gmail.com>)
*   [ashutosh](https://github.com/singhashutosh96) as Ashutosh Singh (<s.ashutosh@hotmail.com>)
*   [dykov](https://github.com/dykov) as Oleksii Dykov (<dykovoleksii@gmail.com>)
