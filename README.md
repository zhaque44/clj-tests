# Integration tests in clojure

This talk is geared for those who are writing or working on back-end services in clojure. Also, for individuals who want to learn clojure.

## Usage
    The goal is to use this project to practice and build your clojure skills & increase test coverage 
    for your microservice
  

[Blog Post for reference](https://medium.com/@haque.zubair/continuous-coverage-with-clojure-89655c51fde8)

## Getting Started

Most clojure projects use leiningen or maven, which are the two most popular build tools. So if you're going to use clojure it's important to know how to use them. This repository uses the leiningen build tool, this will help us with the following:

**Project Compilation:** Clojure gets converted into Java bytecode, and Leiningen tasks automate that process.

**Dependency Management:** Just like Ruby's bundler and gemfiles, Leiningen automates the process of resolving and downloading the Java jar files that your code depends on.

**Running Tasks:** Similar to Ruby's Rake, you can run custom automation tasks written in Clojure.

**Deployment:** Helps with creating Java jars which can be executed or incorporated in other projects. Similar to Ruby gems & project.clj is similar to a gem file.

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

## Test Definition

## Assertions

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
