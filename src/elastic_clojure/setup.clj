(ns elastic-clojure.setup
  (:require
   [elastic-clojure.config :refer [index conn category]]
   [cheshire.core :as json]
   [clojurewerkz.elastisch.rest.index :as idx]
   [clojurewerkz.elastisch.rest.document :as doc]))

(defn- create-index []
  (idx/delete conn index)
  (idx/create conn index))

(defn- seed []
  (let [data (json/parse-stream (clojure.java.io/reader "resources/MOCK_DATA.json"))]
    (doall (map #(doc/create conn index category %) data))))

(defn setup []
  (create-index)
  (seed)
  (Thread/sleep 2000))
