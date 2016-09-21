(ns elastic-clojure.handler
  (:require
   [compojure.core :refer :all]
   [compojure.route :as route]
   [compojure.handler :as handler]
   [ring.util.response :as resp]
   [ring.adapter.jetty :refer :all]
   [elastic-clojure.setup :refer [setup]]
   [elastic-clojure.query :as query]))

(defn- render-file [name ]
  (resp/resource-response name {:root "public"}))

(defn- get-search-results [params]
  (let [query (:query params)]
    (query/full-text-search query)))

(defroutes app-routes
  (GET "/" [] (render-file "index.html"))
  (GET "/css/index.css" [] (render-file "css/index.css"))
  (GET "/search" request (get-search-results (:params request)))
  (route/not-found (render-file "not_found.html")))

(def app
  (-> app-routes
      handler/site))

(defn run []
  (setup)
  (run-jetty app {:port 4000 :join? false}))
