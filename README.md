# Integration tests in clojure

This project is geared for those who are writing or working on back-end services in clojure. Also, for individuals who want to learn clojure.

## Usage
    The goal is to use this project to practice and build your clojure skills & increase test coverage 
    for your microservice
  

[Blog Post for reference](https://medium.com/@haque.zubair/continuous-coverage-with-clojure-89655c51fde8)

## Getting Started

Most clojure projects use leiningen or maven, which are the two most popular build tools. So if you're going to use clojure it's important to know how to use them. This repository uses the leiningen build tool, this will help us with the following:

**Project Compilation:** Clojure gets converted into Java bytecode, and Leiningen tasks automate that process.

**Dependency Management:** Just like Ruby's bundler and gemfiles, Leiningen automates the process of resolving and downloading the Java jar files that your code depends on.

**Running Tasks:** Similar to Ruby's Rake, you can run custom automation tasks written in Clojure.

**Deployment:** Helps with creating Java jars which can be executed or incorporated in other projects. Similar to Ruby, your project.clj file is the equivalent to a Gemfile.

So let’s get started by adding dependencies to the `project.clj` file. Just as an FYI `Clojure libraries` are distributed the same way as other `JVM languages` in `jar files.`

```clojure
(defproject com.something.servicename "0.1.0-SNAPSHOT"
  :description "description of the service and what it is doing"
  :url "http://companyname.com"
  :min-lein-version "2.0.0"
  :exclusions [org.clojure/clojure]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/tools.reader "1.2.2"]
                 [org.clojure/test.check "0.7.0"]
                 [clj-http "3.3.0"]
                 [cheshire "5.6.3"]
                 [http-kit "2.2.0"]]
```

Listed below is a description of what was added:

`test.check is a Clojure property based testing tool.`

`clj-http is an HTTP library wrapping the Apache HTTPComponents client.`

`cheshire is a library that encodes and decodes JSON.`

`http-kit is supports concurrent asynchronous calls to a server.`

## Profiles

Having a `:dev` profile & a `:test` profile defined in your project.clj allows you to be flexible when specifying project specific development/testing tooling. Certain profiles are active by default unless you specify the `:dev` profile is their active by default, the `:test` profile is not active by default, as you can see in the example below we have our `profiles` defined. You should be able to notice how the dev dependent libraries are in a separate block as the test dependent libraries.

```clojure
:profiles {:dev {:dependencies
                   [http-kit.fake "0.2.1"]
                   [http-kit.fake "0.2.1"]
                   [ring/ring-mock "0.3.1"]]
                 :plugins [[some-plugin "0.3.10"]]
                 :resource-paths ["path/to/test-data"]
           :test {:test-paths ["test"]
                :dependencies [[org.clojure/test.check "0.7.0"]
                               [pjstadig/humane-test-output"0.8.1"]]
```

## Test Definition

Before we define our first clojure function we need to define a namespace, every `clojure` source file starts with a namespace declaration. The `ns` macro is will help you define your imports & requires:


**:require**  allows you to set up access to other Clojure namespaces from your codebase

**:as** It is common to make a namespace available under an alias

The `ns` macro allows you to declare the package name and imports. If you navigate to the `tagged_test.clj` file you can see I am defining my package name 

```clojure
(ns com.climate.test.integration.tagged-test
  (:require [clojure.test :refer :all]
            [com.climate.request.clj-http.client :as http]
            [cheshire.core :refer [decode encode] :as json]
            [com.climate.configuration.env-config :as config]))
 ```  

Let’s write our first test, navigate to the `tagged_test.clj` file & you will see the example listed below:

```clojure
(deftest test-something-simple
  (testing "A simple assertion"
    (is (= 1 1))))
 ```  

**deftest**  Defines a test function with no arguments

**testing**  Adds a new string which lists the contexts of what is being tested and must occur inside a test function.


## Assertions

```clojure
(deftest ^:integration get-user-data
  (testing "testing a GET request which retrieves all users in the system"
    (let [get-user-info (http/get (url (str "/todos/1"))
                                {:headers {:content-type :json}})
          response-body (-> get-user-info :body (json/parse-string true))]
      (is (= 200 (:status get-user-info)))
      (is (= 1 (get-in response-body [:userId]))))))
 ```     

## Test Selectors

Leiningen has a `test` task that lets you set metadata on tests so that you can filter a set of tests, basically tagging your tests. This allows you to run just a selection of your tests. You can add a keyword argument and it will invoke that will specify which test from which namespace you want to run. The example below will show you how to declare a test-selector which is added to your project.clj:

```clojure
:test-selectors {:default     #(not-any? % #{:integration :ignore})
                  :integration :integration
                  :service     :service
                  :ignore      :ignore
                  :all         (constantly true)}
```


```clojure
(deftest ^:integration integration-test
  (testing "This is a tagged test for demo purposes"
    (is (= true true))))
```
## Running tagged tests
```bash
$ lein test :integration

lein test clj-tests.api-test

Ran 1 tests containing 2 assertions.
0 failures, 0 errors.
```
this basically ran all of the tests in the given namespace, that were tagged with the ^:integration metadata flag. The result was printed summarizing the test results.
