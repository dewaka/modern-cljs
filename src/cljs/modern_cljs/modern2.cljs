(ns modern-cljs.modern2)

;; (.write js/document "Hello, ClojureScript!")

(defn ^:export greet-user [name]
  (.write js/document (str "Hello there " name)))

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
