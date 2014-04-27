(ns modern-cljs.modern2
  (:require-macros [cljs.core.match.macros :refer [match]]
                   [cljs.core.async.macros :as m :refer [go]])
  (:require [cljs.core.match]
            [cljs.core.async :refer [chan close!]]))

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

(defn bin-operator [stack op]
  (match [stack]
         [([a b & r] :seq)] (cons (op b a) r)
         :else stack))

(defn eval-postfix [expr]
  (loop [expr (seq (.split expr " "))
         stack []]
    (if-let [x (seq expr)]
      (match [(first x)]
             ["+"] (recur (rest expr) (bin-operator stack (partial +)))
             ["-"] (recur (rest expr) (bin-operator stack (partial -)))
             ["*"] (recur (rest expr) (bin-operator stack (partial *)))
             ["/"] (recur (rest expr) (bin-operator stack (partial /)))
             :else (recur (rest expr) (cons (js/parseInt (first x)) stack)))
      stack)))

(defn print-postfix-result [exp]
  (print-line (str "Evaluating " exp " = " (eval-postfix exp))))

(defn alert-postfix-result [exp]
  (js/alert (str "Evaluating " exp " = " (eval-postfix exp))))

(defn ^:export display-result []
  (if-let [input (.getElementById js/document "cInput")]
    (let [expr (.-value input)]
      (alert-postfix-result expr))
    (js/alert "Could not get input")))

(defn timeout [ms]
  (let [c (chan)]
    (js/setTimeout (fn [] (close! c)) ms)
    c))
 
;; This piece of code will have simple sleeps
(go
  (<! (timeout 1000))
  (.log js/console "Hello")
  (<! (timeout 1000))
  (.log js/console "async")
  (<! (timeout 1000))
  (.log js/console "world!"))

(defn ^:export print-stuff []
  ;; (fizz-buzz)
  (print-seq [1 2 3 4 5])
  (print-line "--- Results ---")
  (print-postfix-result "23 7 +")
  (print-postfix-result "23 7 + 5 -")
  (print-postfix-result "25 4 * 5 -")
  (print-postfix-result "25 4 * 5 /")
  (print-postfix-result "23 7 + 10 -"))
