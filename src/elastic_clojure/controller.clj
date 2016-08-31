(ns elastic-clojure.controller
  (:require
      [clojurewerkz.elastisch.rest.document :as doc]
      [clojurewerkz.elastisch.query :as q]))

(defn- ask-for-input []
  (println "Enter a query to search all data with elasticsearch (type 'quit' to exit):")
  (read-line))

(defn run [index category conn]
  (println "Running interactive search...")
  (loop [query "test"]
    (clojure.pprint/pprint (doc/search conn index category :query (q/match :_all query)))
    (if (not (= query "quit"))
      (recur (ask-for-input))
      (println "Bye!"))))
