(ns elastic-clojure.query
  (:require
   [elastic-clojure.config :as c]
   [cheshire.core :as json]
   [clojurewerkz.elastisch.rest.document :as doc]
   [clojurewerkz.elastisch.query :as q]))

(defn- format-response [results]
  (json/generate-string results {:pretty true}))

(defn- search [query]
  (format-response (doc/search c/conn c/index c/category :query query)))

(defn full-text-search [query]
  (search (q/match :_all query)))
