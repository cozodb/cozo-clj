# cozo-clj

Clojure bindings for [CozoDb](https://github.com/cozodb/cozo).

Artefacts are on [Clojars](https://clojars.org/com.github.zh217/cozo-clj). 


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

;; close db
(close-db db)
```

`iquery` will try to print the results (including errors) nicely and return `nil`. 
For non-interactive situations, use `query` instead.