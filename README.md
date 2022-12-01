# cozo-clj

[![Clojars Project](https://img.shields.io/clojars/v/com.github.zh217/cozo-clj.svg)](https://clojars.org/com.github.zh217/cozo-clj)

Clojure bindings for [CozoDb](https://github.com/cozodb/cozo).

This document describes how to set up Cozo for use in Clojure.
To learn how to use CozoDB (CozoScript), follow
the [tutorial](https://github.com/cozodb/cozo-docs/blob/main/tutorial/tutorial.ipynb)
first and then read the [manual](https://cozodb.github.io/current/manual/). You can run all the queries
described in the tutorial with an in-browser DB [here](https://cozodb.github.io/wasm-demo/).

## Usage

```clojure
(in-ns 'cozo-clj.core)

(def db (open-db))

(println (query db "?[] <- [[1,2,3]]"))

(close db)
```

For more information, see the docs.

## Dependency

This library depends on `io.github.cozodb/cozo-lib-java`
for its native bindings. Refer to its [documentation](https://github.com/cozodb/cozo-lib-java).
