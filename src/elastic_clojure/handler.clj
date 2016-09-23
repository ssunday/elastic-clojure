(ns elastic-clojure.handler
  (:require
   [compojure.core :refer :all]
   [compojure.route :as route]
   [compojure.handler :as handler]
   [ring.util.response :as resp]
   [ring.adapter.jetty :refer :all]
   [elastic-clojure.setup :refer [setup]]
   [elastic-clojure.query :as query]))

(defn- render-file [name]
  (resp/resource-response name {:root "public"}))

(defn- get-query [params] (:query params))

(defmulti get-search-results
  (fn [params] (:type params)))

(defmethod get-search-results "full-text" [params]
  (query/full-text-search (get-query params)))

(defmethod get-search-results "first-name" [params]
  (query/first-name-search (get-query params)))

(defmethod get-search-results "last-name" [params]
  (query/last-name-search (get-query params)))

(defmethod get-search-results "email" [params]
  (query/email-search (get-query params)))

(defmethod get-search-results "gender" [params]
  (query/gender-search (get-query params)))

(defroutes app-routes
  (GET "/" [] (render-file "index.html"))
  (GET "/css/index.css" [] (render-file "css/index.css"))
  (GET "/js/index.js" [] (render-file "js/index.js"))
  (GET "/search" request (get-search-results (:params request)))
  (route/not-found (render-file "not_found.html")))

(def app
  (-> app-routes
      handler/site))

(defn run []
  (setup)
  (run-jetty app {:port 4000 :join? false}))
