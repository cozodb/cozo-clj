(defproject com.github.zh217/cozo-clj "0.1.6"
  :description "CozoDb Clojure bindings"
  :url "http://github.com/cozodb/cozo"
  :signing {:gpg-key "hu.ziyang@cantab.net"}
  :license {:name "MIT/Apache-2.0/BSD-3-Clause"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.clojure/data.json "2.4.0"]
                 [com.github.zh217/cozo-lib-java "0.1.6"]]
  :repl-options {:init-ns cozo-clj.core})
