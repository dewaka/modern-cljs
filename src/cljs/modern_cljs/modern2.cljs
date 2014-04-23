(ns modern-cljs.modern2)

;; (.write js/document "Hello, ClojureScript!")

(defn ^:export greet-user [name]
  (.write js/document (str "Hello there " name)))

(defn ^:export noisy-greeter [name]
  (js/alert (str "Hello there, " name)))

(defn ^:export simple-click-event [clicker]
  (js/alert "Hi there you clicked me!")
  (js/alert (str "You are a " (str clicker))))
