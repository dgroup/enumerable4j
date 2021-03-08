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

*   [How to contribute?](#how-to-contribute)

*   [Contributors](#contributors)

### Overview

**enumerable4j** is a Ruby's well known [Enumerable](https://ruby-doc.org/core-2.6/Enumerable.html)
ported to `java`.

### How to use

Get the latest version [here](https://github.com/dgroup/enumerable4j/releases):

```xml

<dependency>
  <groupId>io.github.dgroup</groupId>
  <artifactId>enumerable4j</artifactId>
  <version>${version}</version>
</dependency>
```

Java version required: 1.8+.

enumerable4j | Java 8 | [cactoos](https://github.com/yegor256/cactoos)
|------ | ------ | ------ |
`.all(...)` | `.stream().allMatch(...);` | `new And<>(...,...).value()`
`.select(...)` | `.stream().filter(...).collect(Collectors.toList())` | `new Filtered<>(...,...)`

#### .all

```java
Collection<Integer> src = new EnumerableOf<>(1, 2, 3);
boolean allPositive = src.all(v -> v > 0); // true 
```

#### .any

```java
Collection<Integer> src = new EnumerableOf<>(-1, 0, 1);
boolean oneIsPositive = src.any(v -> v > 0); // true 
```

#### .none

```java
Collection<Integer> src = new EnumerableOf<>(-2, -1, 0);
boolean noneIsPositive = src.none(v -> v > 0); // true 
```

#### .select

```java
Collection<Integer> src = new EnumerableOf<>(-1, 1, 2);
Collection<Integer> positive = src.select(v -> v > 0); // [1, 2] 
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
