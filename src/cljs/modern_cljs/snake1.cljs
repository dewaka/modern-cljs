(ns modern-cljs.snake1
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
    (log "Going to start the Snake game")))

(defn game-canvas [] (.getElementById js/document "snake1Canvas"))
