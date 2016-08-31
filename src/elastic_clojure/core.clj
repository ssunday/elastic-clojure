(ns elastic-clojure.core
  (:require
   [elastic-clojure.setup :refer [setup category]]
   [elastic-clojure.controller :refer [run]]
   [clojurewerkz.elastisch.rest :as esr]
   [clojurewerkz.elastisch.rest.document :as doc]
   [clojurewerkz.elastisch.query :as q]))

(def index "es-clj")

(defn -main []
  (let [conn (esr/connect "http://127.0.0.1:9200")]
    (setup index conn)
    (Thread/sleep 4000)
    (run index category conn)))
