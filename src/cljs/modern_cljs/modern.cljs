(ns modern-cljs.modern)

;; (.write js/document "Hello, ClojureScript!")

(defn greet [name]
  (js/alert (str "Hello there " name)))

(defn get-name []
  (.prompt js/window "Please enter your name: "))

;; (greet (get-name))

(def my-canvas (.getElementById js/document "myCanvas"))

(defn draw-circle [ctx x y r s-angle e-angle color]
    (set! (.-fillStyle ctx) "blue")
    (.beginPath ctx)
    ;; (.arc ctx 55 207 50 0 (* 2 (.-PI js/Math)))
    (.arc ctx x y r s-angle e-angle)
    (.stroke ctx)
    (set! (.-fillStyle ctx) color)
    (.fill ctx))

(defn draw-rect [ctx color x1 y1 x2 y2]
    (set! (.-fillStyle ctx) color)
    (.fillRect ctx x1 y1 x2 y2))

(defn paint-things []
  (let [ctx (.getContext my-canvas "2d")]
    ;; Red rectangle
    (draw-rect ctx "#FF0000" 0 0 150 75)

    ;; Paint a yellow circle
    (draw-circle ctx 55 207 50 0 (* 2 (.-PI js/Math)) "yellow")

    ;; Paint a blue circle
    (draw-circle ctx 155 407 100 0 (* 2 (.-PI js/Math)) "blue")

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

;; (paint-things)
;; (register-mouseclick)

;; (register-mousemove)
