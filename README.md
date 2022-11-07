# cozo-clj

[![Clojars Project](https://img.shields.io/clojars/v/com.github.zh217/cozo-clj.svg)](https://clojars.org/com.github.zh217/cozo-clj)

Clojure bindings for [CozoDb](https://github.com/cozodb/cozo).

Artefacts are on [Clojars](https://clojars.org/com.github.zh217/cozo-clj). 
For Leiningen/Boot:
```clojure
[com.github.zh217/cozo-clj "0.1.0"]
```
For Clojure CLI/deps.edn:
```clojure
com.github.zh217/cozo-clj {:mvn/version "0.1.0"}
```

## Use

In a REPL:
```clojure
(use 'cozo-clj.core)

(in-ns 'cozo-clj.core)

;; open db
(def db (open-db "_test_db"))

;; run query
(iquery db "?[] <- [[1,2,3]]")

;; run query with bindings
(iquery db "?[] <- [[1,2,3,$a]]" {:a "clojure"})

;; close db, otherwise the native resources will not be released
;; until the program quits
(close-db db)
```

`iquery` will try to print the results (including errors) nicely and return `nil`. 
For non-interactive situations, use `query` instead.

## Dependency

This library depends on `com.github.zh217/cozo-lib-java`
for its native bindings. Refer to its [documentation](https://github.com/cozodb/cozo-lib-java) 
for how it works, and how to compile
the native binding in case a pre-compiled binary is not
available for your system.