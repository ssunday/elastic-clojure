(ns elastic-clojure.handler
  (:require
   [compojure.core :refer :all]
   [compojure.route :as route]
   [compojure.handler :as handler]
   [ring.adapter.jetty :refer :all]
   [elastic-clojure.setup :refer [setup]]
   [elastic-clojure.query :as query]
   [stencil.core :as stencil]))

(defn- render-template [name args]
  (stencil/render-file (str "../resources/templates/" name) args))

(defn- get-search-results [params]
  (let [query (:query params)]
    (query/full-text-search query)))

;; (defn- get-first-name-results [params]
;;   (let [first-name (:first-name params)]
;;     (query/search-on-first-name first-name)))

(defroutes app-routes
  (GET "/" [] (render-template "index" {}))
  (GET "/search" request (get-search-results (:params request)))
  (route/not-found (stencil/render-string "Not Found!" {})))

(def app
  (-> app-routes
      handler/site))

(defn run []
  (setup)
  (run-jetty app {:port 4000 :join? false}))
