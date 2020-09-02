(ns com.climate.test.integration.simple-test
    (:require [clojure.test :refer :all]
      [com.climate.configuration.env-config :as config]))


(deftest ^:integration test-something-simple
  (testing "A simple assertion"
    (is (= 1 1))))