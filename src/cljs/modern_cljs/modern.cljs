(ns modern-cljs.modern)

;; (.write js/document "Hello, ClojureScript!")

(defn greet [name]
  (js/alert (str "Hello there " name)))

(defn get-name []
  (.prompt js/window "Please enter your name: "))

;; (greet (get-name))

(def my-canvas (.getElementById js/document "myCanvas"))

(defn paint-things []
  (let [ctx (.getContext my-canvas "2d")]
    (set! (.-fillStyle ctx) "#FF0000")
    (.fillRect ctx 0 0 150 75)

    (set! (.-fillStyle ctx) "green")
    (.fillRect ctx 155 80 255 180)))

(defn do-stuff []
  (greet (get-name)))

(defn register-mouseclick []
  (set! (.-onclick my-canvas) do-stuff))

(defn print-mouse-position [event]
  (.log js/console (str "x = " (.-pageX event) ", y = " (.-pageY event))))

(defn register-mousemove []
  (set! (.-onmousemove my-canvas) print-mouse-position))

(paint-things)
;; (register-mouseclick)

(register-mousemove)
