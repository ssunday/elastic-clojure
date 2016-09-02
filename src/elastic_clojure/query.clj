(ns elastic-clojure.query
  (:require
   [cheshire.core :as json]
   [clojurewerkz.elastisch.rest.document :as doc]
   [clojurewerkz.elastisch.query :as q]))

(defn full-text-search [query conn index category]
  (json/generate-string
   (doc/search conn index category :query (q/match :_all query))
   {:pretty true}))
