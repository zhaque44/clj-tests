(ns com.climate.test.integration.tagged-test
  (:require [clojure.test :refer :all]
            [com.climate.request.clj-http.client :as http]
            [cheshire.core :refer [decode encode] :as json]
            [com.climate.configuration.env-config :as config]))

(def base-url "https://jsonplaceholder.typicode.com")

(defn url [s] (str base-url s))

(deftest ^:integration integration-test
  (testing "This is a tagged test for demo purposes"
    (is (= true true))))

(deftest ^:integration get-user-data
  (testing "testing a GET request which retrieves all users in the system"
    (let [get-user-info (http/get (url (str "/todos/1"))
                                {:headers {:content-type :json}})
          response-body (-> get-user-info :body (json/parse-string true))]
      (is (= 200 (:status get-user-info)))
      (is (= 1 (get-in response-body [:userId]))))))