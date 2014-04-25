(ns modern-cljs.core
  (use '[clojure.core.match :only (match)]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

;; (defn eval-postfix [expr]
;;   ())

(defn fizz-buzz []
  (doseq [n (range 1 101)]
    (println
     (match [(mod n 3) (mod n 5)]
            [0 0] "FizzBuzz"
            [0 _] "Fizz"
            [_ 0] "Buzz"
            :else n))))

(defn eval-expression [expr]
  (seq (.split expr " ")))
