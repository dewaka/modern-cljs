(ns modern-cljs.game1
  (:require-macros [cljs.core.match.macros :refer [match]]
                   [cljs.core.async.macros :as m :refer [go]])
  (:require [cljs.core.match]
            [cljs.core.async :refer [chan close!]]
            [modern-cljs.modern :as modern]))

(defn ^:export start-game1 [canvas]
  (js/alert "Starting game1")
  (let [ctx (.getContext canvas "2d")]
    (modern/draw-circle ctx 155 407 100 0 (* 2 (.-PI js/Math)) "blue")))

(defn log [s]
  (.log js/console s))

(defn ^:export handle-click [e]
  (js/alert "You clicked the canvas")
  (log (str "x = " (.-x e) ", y = " (.-y e)))
  (log e))
