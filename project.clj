(defproject elastic-clojure "0.1.0-SNAPSHOT"
  :description "Elastic Search with Clojure Example"
  :url "https://github.com/ssunday/elastic-clojure"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [cheshire "5.6.3"]
                 [compojure "1.4.0"]
                 [ring "1.4.0"]
                 [clojurewerkz/elastisch "2.2.2"]]
  :main elastic-clojure.core/-main)
