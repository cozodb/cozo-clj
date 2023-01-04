# cozo-clj

[![java](https://img.shields.io/maven-central/v/io.github.cozodb/cozo-clj)](https://mvnrepository.com/artifact/io.github.cozodb/cozo-clj)

Clojure bindings for [CozoDb](https://www.cozodb.org).

This document describes how to set up Cozo for use in Clojure.
To learn how to use CozoDB (CozoScript), read the [docs](https://docs.cozodb.org/en/latest/index.html).

## Usage

```clojure
(in-ns 'cozo-clj.core)

(def db (open-db))

(println (query db "?[] <- [[1,2,3]]"))

(close db)
```

For more information, see the built-in docs.

## Dependency

This library depends on `io.github.cozodb/cozo-lib-java`
for its native bindings.