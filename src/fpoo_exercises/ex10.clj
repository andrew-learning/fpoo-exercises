(ns fpoo-exercises.ex10
  (:use clojure.algo.monads))

(defn function-that-might-produce-nil [x]
  (if (even? x) nil x))

(with-monad maybe-m
  (domonad [step1-value (function-that-might-produce-nil 1)
            step2-value (* (inc step1-value) 3)]
           (dec step2-value)))


