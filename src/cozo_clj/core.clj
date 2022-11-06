(ns cozo-clj.core
  (:require [clojure.data.json :as json])
  (:import (org.cozodb CozoDb)))

(defn open-db
  [^String name]
  (CozoDb. name))

(defn close-db
  [^CozoDb db]
  (.close db))

(defn query
  [^CozoDb db ^String script & [params]]
  (let [params (json/write-str (or params {}))
        result (json/read-str
                 (.query db script params)
                 :key-fn keyword)]
    (if (:ok result)
      result
      (throw (ex-info
               "query failed"
               result)))))

(defn iquery
  [^CozoDb db ^String script & [params]]
  (let [params (json/write-str (or params {}))
        result (json/read-str
                 (.query db script params)
                 :key-fn keyword)]
    (if (:ok result)
      (clojure.pprint/pprint (select-keys result [:headers :rows]))
      (binding [*out* *err*]
        (println (or (:display result)
                     (:message result)))))))