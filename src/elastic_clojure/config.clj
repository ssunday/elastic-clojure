(ns elastic-clojure.config
  (:require
   [clojurewerkz.elastisch.rest :as esr]))

(def index "es-clj")

(def category "person")

(def conn (esr/connect "http://127.0.0.1:9200"))
