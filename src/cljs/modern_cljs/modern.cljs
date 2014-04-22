(ns modern-cljs.modern)

;; (.write js/document "Hello, ClojureScript!")

(defn greet [name]
  (js/alert (str "Hello there " name)))

(defn get-name []
  (.prompt js/window "Please enter your name: "))

;; (greet (get-name))

(def my-canvas (.getElementById js/document "myCanvas"))

(defn draw-circle [ctx]
    (set! (.-fillStyle ctx) "blue")
    (.beginPath ctx)
    (.arc ctx 55 207 50 0 (* 2 (.-PI js/Math)))
    (.stroke ctx))

(defn draw-rect [ctx color x1 y1 x2 y2]
    (set! (.-fillStyle ctx) color)
    (.fillRect ctx x1 y1 x2 y2))

(defn paint-things []
  (let [ctx (.getContext my-canvas "2d")]
    ;; Red rectangle
    (draw-rect ctx "#FF0000" 0 0 150 75)
    ;; Paint a circle
    (draw-circle ctx)
    ;; Green rectangle
    (draw-rect ctx "green" 155 80 255 180)))

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
