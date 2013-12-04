(ns fpoo-exercises.point)

(defn make [class & args]
  (let [allocated {}
        seeded {:__class_symbol__ (:__own_symbol__ class)}
        constructor (:add-instance-values (:__instance_methods__ class))]
    (apply constructor seeded args)))

(def Point 
  {
     :__own_symbol__ 'Point
     :__instance_methods__ 
     {
       :add-instance-values
       (fn [this x y]
         (assoc this :x x :y y))

       :shift
       (fn [this xinc yinc]
         (make Point (+ (:x this) xinc)
                     (+ (:y this) yinc)))
     }
   })

(def send-to
  (fn [instance message & args]
    (let [class (eval (:__class_symbol__ instance)) 
          method (message (:__instance_methods__ class))]
      (apply method instance args))))

(def class-of :__class_symbol__)
(def x :x)
(def y :y)

(defn Triangle 
  [p1 p2 p3]
  {:p1 p1
   :p2 p2
   :p3 p3
   :_class_symbol__ 'Triangle})

(def right-triangle (Triangle (Point 0 0) (Point 0 1) (Point 1 0)))
(def equal-right-triangle (Triangle (Point 0 0) (Point 0 1) (Point 1 0)))
(def different-triangle (Triangle (Point 0 0) (Point 0 10) (Point 10 0)))

(defn equal-triangles? [& triangles] (apply = triangles))

; (equal-triangles? right-triangle right-triangle)
; (equal-triangles? right-triangle equal-right-triangle)
; (equal-triangles? right-triangle equal-right-triangle different-triangle)

(defn valid-triangle? [p1 p2 p3]
  (not-any? #(apply = %) [[p1 p2] [p2 p3] [p3 p1]]))

(def my-point (make Point 1 2))
(prn (send-to my-point :shift -1 -100))
