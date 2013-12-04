(ns fpoo-exercises.ex7)

(-> [1]
    first
    inc
    list)

(-> [1]
    first
    inc
    (* 3)
    list)

(-> 3 ((fn [n] (* 2 n))) inc)

(-> 1
    (+ 2)
    (* 3)
    (+ 4))

(+ (* (+ 1 2) 3) 4)

(defn separate [predicate sequence]
  [(filter predicate sequence) (remove predicate sequence)])

(separate odd? [1 2 3 4 5])

; destructuring binding
(let [[odds evens] (separate odd? [1 2 3 4 5])]
      odds)
