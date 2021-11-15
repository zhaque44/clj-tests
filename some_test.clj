(ns clj-tests.some-test
  (:require [clojure.test :refer :all]
    :use clojure.test
        ring.mock.request
        clj-tests.web))

(deftest test-something-simple
  (testing "A simple assertion"
      (is (= 1 1))))