(ns fpoo-exercises.ex9)

(defn make-incrementer
  [increment]
  (fn [x] (+ increment x)))

(def inc8 (make-incrementer 8))
(inc8 5)

(def add3 (partial + 3))
(add3 4)

; (def odd? (complement even?))

; ex1
(map (fn [n] (+ 2 n)) [1 2 3])
(map (comp inc inc) [1 2 3])

; ex2
(defn separate
  [pred sequence]
  ((juxt (partial filter pred) (partial remove pred)) sequence))

; (separate odd? [1 2 3 4 5])
; => [(1 3 5) (2 4)]

; ex3
(def myfun
  (let [x 3]
    (fn [] x)))

; ex4
(def myfun
  ((fn [x]
    (fn [] x)) 3))
(myfun)

; ex5
(def my-atom (atom 0))
(swap! my-atom inc)
(deref my-atom)

(swap! my-atom (fn [x] 33))

; ex6
(defn always [n]
  (fn [& args] n))

( (always 8) 1 'a :foo)

; ex7
(defn check-sum [coll]
  (apply + (map * (iterate inc 1) coll)))
; (check-sum [4 8 9 3 2 7])

; ex8
(defn isbn? [s]
  (-> (map #(- (int %1) (int \0)) s)
    check-sum
    (rem 11)
    zero?))
;(check-sum (->> "0977716614" (map #(- (int %1) (int \0)))))
;(apply + (map * [1 2 3 4 5 6 7 8 9 10] [0 9 7 7 7 1 6 6 1 4]))
;; (isbn? "0131774115")
;; (isbn? "0977716614")
;; (isbn? "1934356190")

; ex9
(defn upc-check-sum [coll]
  (apply + (map * (cycle [3 1]) coll)))
(defn upc? [s]
  (-> (map #(- (int %1) (int \0)) s)
    upc-check-sum
    (rem 10)
    zero?))

;; (upc? "074182265830")
;; (upc? "731124100023")
;; (upc? "722252601404") ;; This one is incorrect.

; ex10
(defn number-checker 
  [check-sum-f modulo]
  (fn [s]
    (-> 
      (map #(- (int %1) (int \0)) s)
      check-sum-f
      (rem modulo)
      zero?)) )
(defn isbn-check-sum [coll]
  (apply + (map * (iterate inc 1) coll)))
(def isbn? (number-checker isbn-check-sum 11))
;; (isbn? "0131774115")
;; (isbn? "0131774115")
;; (isbn? "0977716614")
