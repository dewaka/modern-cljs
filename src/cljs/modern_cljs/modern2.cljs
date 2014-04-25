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

(defn ^:export display-result []
  (if-let [input (.getElementById js/document "cInput")]
    (let [expr (.-value input)]
      (js/alert (str "Got it: " expr))
      (eval-post-fix expr))
    (js/alert "Could not get input")))

;; Stub function for evaluating post-fix expressions
(defn eval-post-fix [exp]
  (js/alert (str "Going to evaluate: " exp)))

(defn fizz-buzz []
  (doseq [n (range 1 101)]
    (print-line  (match [(mod n 3) (mod n 5)]
                     [0 0] "FizzBuzz"
                     [0 _] "Fizz"
                     [_ 0] "Buzz"
                     :else n))))

(defn ^:export print-stuff []
  ;; (fizz-buzz)
  (print-seq [1 2 3 4 5]))

(defn print-seq [ls]
  (loop [ls ls]
    (when (seq ls)
      (print-line (first ls))
      (recur (rest ls)))))
