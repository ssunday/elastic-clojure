(ns elastic-clojure.handler
  (:require
   [compojure.core :refer :all]
   [compojure.route :as route]
   [compojure.handler :as handler]
   [ring.adapter.jetty :refer :all]
   [elastic-clojure.setup :refer [setup category]]
   [elastic-clojure.query :as query]
   [clojurewerkz.elastisch.rest :as esr]
   [stencil.core :as stencil]))

(def index "es-clj")

(def conn (esr/connect "http://127.0.0.1:9200"))

(defn- render-template [name args]
  (stencil/render-file (str "../resources/templates/" name) args))

(defn- get-search-results [params]
  (let [query (:query params)]
    (query/full-text-search query conn index category)))

(defroutes app-routes
  (GET "/" [] (render-template "index" {}))
  (GET "/search" request (get-search-results (:params request)))
  (route/not-found (stencil/render-string "Not Found!" {})))

(def app
  (-> app-routes
      handler/site))

(defn run []
  (setup index conn)
  (run-jetty app {:port 4000 :join? false}))
