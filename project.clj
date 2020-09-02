(defproject com.climate/scully "dynamic-version"
            :description
            "An integration testing project."
            :url
            "https://zubair.com"
            :min-lein-version "2.0.0"
            :repositories [["release-bin" {:url           "https://bin/release"
                                               :username      :env/ARTIFACTORY_SVC_ACCT
                                               :password      :env/ARTIFACTORY_SVC_ACCT_PWD
                                               :sign-releases false
                                               }]]

            :source-paths ["src/main/clojure"]
            :test-paths ["src/test/clojure" "src/test/resources"]
            :resource-paths ["src/main/resources"]
            :dependencies
            [[org.clojure/clojure "1.8.0"]
             [clj-http "3.3.0"]
             [clj-time "0.13.0"]
             [cheshire "5.6.3"]
             [http-kit "2.2.0"]
             [org.clojure/test.check "0.9.0"]]

            :profiles {:doc {:plugins      [[lein-marginalia "0.8.0"]]
                             :dependencies [[marginalia "2015.06.30T22.52.56.e2f8955"]]}}

            :plugins
            [[lein-cloverage "1.0.3"]
             [lein-ancient "0.5.5"]]

            :test-selectors {:default     #(not-any? % #{:integration :ignore})
                             :integration :integration
                             :service     :service
                             :ignore      :ignore
                             :all         (constantly true)}

            :aliases
            {"docs" ["marg" "-d" "target"]})
