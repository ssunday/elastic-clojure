(ns elastic-clojure.query
  (:require
   [elastic-clojure.config :as c]
   [cheshire.core :as json]
   [clojurewerkz.elastisch.rest.document :as doc]
   [clojurewerkz.elastisch.query :as q]))

(defn- format-response [results]
  (json/generate-string results {:pretty true}))

(defn- search [query]
  (format-response (doc/search c/conn c/index c/category
                               :query query
                               :size 50)))

(defn full-text-search [query]
  (search (q/match :_all query)))

(defn first-name-search [first-name]
  (search (q/match c/first-name first-name)))

(defn last-name-search [last-name]
  (search (q/match c/last-name last-name)))

(defn email-search [email]
  (search (q/match c/email email)))

(defn gender-search [gender]
  (search (q/match c/gender gender)))
