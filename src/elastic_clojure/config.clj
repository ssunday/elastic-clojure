(ns elastic-clojure.config
  (:require
   [clojurewerkz.elastisch.rest :as esr]))

(def index "es-clj")

(def category "person")

(def first-name "first_name")

(def last-name "last_name")

(def email "email")

(def gender "gender")

(def conn (esr/connect "http://127.0.0.1:9200"))
