(ns modern-cljs.modern2
  (:require-macros [cljs.core.match.macros :refer [match]])
  (:require [cljs.core.match]))

;; (.write js/document "Hello, ClojureScript!")

(defn print-line [s]
  (.write js/document (str s "</br>")))

(defn ^:export greet-user [name]
  (print-line (str "Hello there " name)))

(defn ^:export noisy-greeter [name]
  (js/alert (str "Hello there, " name)))

(defn ^:export simple-click-event [clicker]
  (js/alert "Hi there you clicked me!")
  (js/alert (str "You are a " (str clicker))))

;; Stub function for evaluating post-fix expressions
(defn fizz-buzz []
  (doseq [n (range 1 101)]
    (print-line  (match [(mod n 3) (mod n 5)]
                     [0 0] "FizzBuzz"
                     [0 _] "Fizz"
                     [_ 0] "Buzz"
                     :else n))))

(defn print-seq [ls]
  (loop [ls ls]
    (when (seq ls)
      (print-line (first ls))
      (recur (rest ls)))))

(defn operator-add [stack]
  (do (print-line (str "in + " stack))
  (match [stack]
         [([a b & r] :seq)] (cons (+ a b) r)
         :else stack)))

(defn operator-subtract [stack]
  (do (print-line (str "in - " stack))
  (match [stack]
         [([a b & r] :seq)] (cons (- b a) r)
         :else stack)))

(defn bin-operator [stack op]
  (match [stack]
         [([a b & r] :seq)] (cons (op b a) r)
         :else stack))

(defn eval-postfix-exprs [expr]
  (loop [expr expr
         stack []]
    (if-let [x (seq expr)]
      (match [(first x)]
             ["+"] (recur (rest expr) (bin-operator stack (partial +)))
             ["-"] (recur (rest expr) (bin-operator stack (partial -)))
             :else (recur (rest expr) (cons (js/parseInt (first x)) stack)))
      stack)))

(defn eval-postfix-string [exp]
  (print-line (str "Evaluating " exp " = " (eval-postfix-exprs (seq (.split exp " "))))))

(defn ^:export display-result []
  (if-let [input (.getElementById js/document "cInput")]
    (let [expr (.-value input)]
      (js/alert (str "Got it: " expr))
      (eval-postfix-string expr))
    (js/alert "Could not get input")))

(defn ^:export print-stuff []
  ;; (fizz-buzz)
  (print-seq [1 2 3 4 5])
  (print-line "--- OK ---")
  (print-line (operator-add [1 2 3]))
  (print-line "--- Parsing ---")
  (print-line (cons (js/parseInt "23") []))
  (print-line "--- Results ---")
  (eval-postfix-string "23 7 +")
  (eval-postfix-string "23 7 + 5 -")
  (eval-postfix-string "23 7 + 10 -"))
