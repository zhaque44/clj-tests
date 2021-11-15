(ns clj-tests.some-test
  (:require [clojure.test :refer :all]
    :use clojure.test
        ring.mock.request))

(deftest test-something-simple
  (testing "A simple assertion"
      (is (= 1 1))))
