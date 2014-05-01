(ns modern-cljs.game1
  (:require-macros [cljs.core.match.macros :refer [match]]
                   [cljs.core.async.macros :as m :refer [go]])
  (:require [cljs.core.match]
            [cljs.core.async :refer [chan close!]]
            [modern-cljs.modern :as modern]))

(defn log [s]
  (.log js/console s))

(defn timeout [ms]
  (let [c (chan)]
    (js/setTimeout (fn [] (close! c)) ms)
    c))

(defn ^:export start-game1 [canvas]
  (log "Starting game1")
  (let [ctx (.getContext canvas "2d")]
    (modern/draw-circle ctx 155 407 100 0 (* 2 (.-PI js/Math)) "blue")))

(defn game-canvas [] (.getElementById js/document "game1Canvas"))

(defn draw-small-circle [x y]
  (let [ctx (.getContext (game-canvas) "2d")]
    (log (str "x = " x ", y = " y))
    (go 
        (modern/draw-circle ctx x y 5 0 (* 2 (.-PI js/Math)) "red")
        (<! (timeout 500))
        (modern/draw-circle ctx y x 5 0 (* 2 (.-PI js/Math)) "green"))))


(defn ^:export handle-click [e]
  ;; (js/alert "You clicked the canvas")
  (log (str "x = " (.-x e) ", y = " (.-y e)))
  (log e)
  (draw-small-circle (- (.-pageX e) (.-offsetLeft (game-canvas))) 
                     (- (.-pageY e) (.-offsetTop (game-canvas)))))
