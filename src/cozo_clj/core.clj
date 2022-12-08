;
; Copyright 2022, The Cozo Project Authors.
;
; This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0.
; If a copy of the MPL was not distributed with this file,
; You can obtain one at https://mozilla.org/MPL/2.0/.
;

(ns cozo-clj.core
  (:require [clojure.data.json :as json])
  (:import (org.cozodb CozoJavaBridge)))

(defn open-db
  "Creates a database.

  The method `close` must be called when the DB is no longer needed,
  unless you want it to live as long as your program.

  `engine` defaults to `:mem` if not specified,
  and can be `:mem`, `:sqlite`, `:rocksdb` and others,
  depending on what was compiled in.

  `path` is used to store persistent data, and is ignored for some engines.

  `options` is a map, engine-dependent."
  ([]
   (CozoJavaBridge. "mem" "" ""))
  ([engine path]
   (CozoJavaBridge. (name engine) path ""))
  ([engine path options]
   (CozoJavaBridge. (name engine) path (json/write-str options))))

(defn close-db
  "Close the DB and free any native resources associated with it.

  If you fail to call this and the Java part was garbage-collected,
  it is a resource-leak."
  [^CozoJavaBridge db]
  (.close db))

(defn- mk-err [err]
  (ex-info
    (or (:display err) (:message err) "an error occurred")
    err))

(defn query
  "Query the database with CozoScript.

  `params`, if given, should be a map of key-value pairs."
  [^CozoJavaBridge db ^String script & [params]]
  (let [params (json/write-str (or params {}))
        result (json/read-str
                 (.query db script params)
                 :key-fn keyword)]
    (if (:ok result)
      result
      (throw (mk-err result)))))

(defn export-relations
  "Export the relations with names given in a vector.

  If `as-objects` is true, the output format is different."
  [^CozoJavaBridge db rels]
  (let [args (json/write-str {:relations rels})
        result (json/read-str
                 (.exportRelations db args)
                 :key-fn keyword)]
    (if (:ok result)
      (:data result)
      (throw (mk-err result)))))

(defn import-relations
  "Import data into relations.

  The relations to be imported must already exist in the database.

  Note that triggers are _not_ run for the relations, if any exists.
  If you need to activate triggers, use queries with parameters.

  The format of `payload` is the same as returned by `export-relations`."
  [^CozoJavaBridge db payload]
  (let [args (json/write-str payload)
        result (json/read-str
                 (.importRelations db args)
                 :key-fn keyword)]
    (when-not (:ok result)
      (throw (mk-err result)))))

(defn backup
  "Backup the database into a file."
  [^CozoJavaBridge db ^String path]
  (let [result (json/read-str
                 (.backup db path))]
    (when-not (:ok result)
      (throw (mk-err result)))))

(defn restore
  "Restore a backup to the database.

  The database must be empty."
  [^CozoJavaBridge db ^String path]
  (let [result (json/read-str
                 (.restore db path))]
    (when-not (:ok result)
      (throw (mk-err result)))))

(defn import-relations-from-backup
  "Import relations from a backup file.

  The relations must already exist in the database.

  Note that triggers are _not_ run for the relations, if any exists.
  If you need to activate triggers, use queries with parameters.
  "
  [^CozoJavaBridge db path relations]
  (let [args (json/write-str {:path path :relations relations})
        result (json/read-str
                 (.importRelationsFromBackup db args))]
    (when-not (:ok result)
      (throw (mk-err result)))))

(defn ^:no-doc -main []
  (let [db (open-db :mem "")]
    (println (query db "?[] <- [[1,2,3]]"))
    (println (query db "?[a,b,c] <- [[1,2,3]] :replace s {a, b, c}"))
    (println (export-relations db [:s]))
    (import-relations db {:s [{:a 2 :b 3 :c 5}]})
    (println (query db "?[a, b, c] := *s[a, b, c]"))))