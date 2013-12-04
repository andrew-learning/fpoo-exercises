(ns fpoo-exercises.just-enough)

; Exercise 1
(def second (fn [list] (first (rest list))))
(second [1 2 3])

; Exercise 2
(defn third1 [list] (second (rest list)))
(third1 [1 2 3])

(defn third2 [list] (nth list 2))
(third2 [3 4 5])

; (sequential? [1 2 3]) => true
; (sequential? '(1 2 3)) => true

(defn my-apply [function sequence] (eval (cons function sequence)))
(my-apply + [1 2 3 4])

; Exercise 3
(defn add-squares [& nums] (apply + (map #(* %1 %1) nums)))
(add-squares 1 2 5)
(add-squares 2 3)
(add-squares)

; Exercise 4
(defn fact [n] (apply * (range 1 (inc n))))
(fact 5)

; Exercise 5
(filter odd? [1 2 3 4])
(map inc [1 2 3])

(take 10 (iterate #(* 2 %) 1))

(defn add-distinct [& nums] (apply + (distinct nums)))
(add-distinct 1 2 3 2 4)

(concat [1 2] [3 4])  ; => (1 2 3 4)

(take 3 (repeat "hello"))

(interleave [1 2 3] [4 5 6])

(drop 3 (range 10))

(drop-last 3 (range 10))

; convoluted way of taking alternate elements
(flatten (map rest (partition 2 (range 10))))

(every? pos? (range 10)) ; => false because of 0
(every? pos? (range 1 10))

(remove
  (fn [n] (zero? (rem n 3)))
  (range 20))

; Exercise 6
(defn prefix-of? [a b] (= (take (count a) b) a))
(prefix-of? [1 2] [1 2 3 4])
(prefix-of? '(2 3) [1 2 3 4])
(prefix-of? '(1 2) [1 2 3 4])

; Exercise 7
(defn tails [coll]
  (map #(drop % coll)
    (range (inc (count coll)))))

(tails [1 2 3])

(def puzzle (fn [list] (list list)))
(puzzle '(1 2 3))

(def puzzle (fn [list] (clojure.core/list list)))
(puzzle '(1 2 3))

; Miscellaneous
(defn times-table [n]
  (let [ints (iterate inc 1)]
    (partition n
      (for [a (take n ints)
            b (take n ints)]
        (* a b)))))

(defn pretty [table]
  (str/join "\n"
    (map (fn [row] (apply str (map #(format "%3d " %) row))) table)))

(println (pretty (times-table 12)))





