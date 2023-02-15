(ns clj-tests.some-test
 (:require [clojure.test :refer :all]
           [cheshire.core :as json]
           [clj-http.client :as http]
           [environ.core :refer [env]]))



(deftest test-some
  (testing "some test"
    (let [req (http/get "http://site.api.espn.com/apis/site/v2/sports/football/nfl/teams")
          response (json/parse-string (:body req) true)
          _ (println response)]
    (is (= 200 (:status req)))
    (is (not (empty? (:sports response)))))))