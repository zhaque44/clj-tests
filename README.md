# Integration tests in clojure

This talk is geared for those who are writing or working on back-end services in clojure. Also, for individuals who want to learn clojure.

## Usage
    The goal is to use this project to practice and build your clojure skills & increase test coverage 
    for your microservice
  

[Blog Post for reference](https://medium.com/@haque.zubair/continuous-coverage-with-clojure-89655c51fde8)

## Getting Started

Most clojure projects use leiningen or maven, which are the two most popular build tools. So if you're going to use clojure it's important to know how to use them. This repository uses the leiningen build tool, this will help us with the following:

**Project Compilation:**

**Dependency Management:**

**Running Tasks:**

**Deployment:**

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
