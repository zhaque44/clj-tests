(defproject clj-tests "0.1.0-SNAPSHOT"
  :description "Clojure API Tests"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [clj-http "2.3.0"]
                 [clj-time "0.13.0"]
                 [cheshire "5.6.3"]
                 [http-kit "2.2.0"]
                 [org.clojure/test.check "0.9.0"]
                 [compojure "1.3.4"]
                 [ring-mock "0.1.5"]
                 [ring/ring-jetty-adapter "1.3.2"]
                 [environ "1.0.0"]]
  :min-lein-version "2.0.0"
  :plugins [[environ/environ.lein "0.2.1"]]

  :test-selectors {:default     #(not-any? % #{:integration :ignore})
                   :integration :integration
                   :ignore      :ignore
                   :all         (constantly true)}
          
  :hooks [environ.leiningen.hooks]
  :uberjar-name "clj-tests-standalone.jar"

  :profiles {:production {:env {:production true}}
             :uberjar {:main clj-bootstrap.web, :aot :all}})
