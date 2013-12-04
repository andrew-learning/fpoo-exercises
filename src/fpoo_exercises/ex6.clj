(ns fpoo-exercises.ex6)

(defn factorial
  [n]
  (if (=  1 n)
    1
    (* n (factorial (dec n)))))

(factorial 10)

(defn factorial2
  ([something so-far]
    (if (empty? something)
      so-far
      (factorial2 (rest something) (* (first something) so-far))))
  ([n] (factorial2 (map inc (range n)) 1)))

(factorial2 10)

(defn recursive-function
  [f something so-far]
    (if (empty? something)
      so-far
      (recursive-function f (rest something) (f (first something) so-far))))

(recursive-function + [1 2 3 4] 0)
(recursive-function * [1 2 3 4] 1)

(recursive-function (fn [x m] (merge m {x 0})) [:a :b :c] {})
(recursive-function (fn [x m] (merge m {x (count m)})) [:a :b :c] {})


