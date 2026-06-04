(ns dotnet
  "Dotnet related tasks to be called by `nostrand`.
  Nostrand uses the `magic` compiler.

  ## Motivation

  This namespace provides convenient functions to:
  - compile the prod namespaces to .net assemblies
  - run the tests in the CLR"
  (:require [nostrand.tasks :as tasks]))

(def jvm-only
  "JVM-only namespaces that must not load on the CLR."
  '[robertluo.fun-map.util])

(defn build
  "Compiles the project to dlls.
  nos dotnet/build"
  []
  (tasks/compile-project :exclude jvm-only :clean? true))

(defn run-tests
  "Run all the tests on the CLR.
  nos dotnet/run-tests"
  []
  ;; :re keeps run-all-tests from sweeping every loaded ns (clojure.*, deps' suites)
  (tasks/run-clojure-tests :aliases [:test]
                           :exclude jvm-only
                           :re #"robertluo\.fun-map.*"))
