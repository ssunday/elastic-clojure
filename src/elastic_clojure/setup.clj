(ns elastic-clojure.setup
  (:require
   [cheshire.core :as json]
   [clojurewerkz.elastisch.rest.index :as idx]
   [clojurewerkz.elastisch.rest.document :as doc]))

(def category "person")

(defn- create-index [index conn]
  (idx/delete conn index)
  (idx/create conn index))

(defn- seed [index conn]
  (let [data (json/parse-stream (clojure.java.io/reader "resources/MOCK_DATA.json"))]
    (doall (map #(doc/create conn index category %) data))))

(defn setup [index conn]
  (create-index index conn)
  (seed index conn)
  (Thread/sleep 2000))
