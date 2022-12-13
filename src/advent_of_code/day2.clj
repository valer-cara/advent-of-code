(ns advent-of-code.day2
  (:require [clojure.string :as str]))

(defn solve
  []
                                        ; part 1

  (def rounds (str/split (slurp "./src/advent_of_code/day2.input.txt") #"\n"))

  ;; Just a function to get {0,1,2} from {ABC, XYZ}
  (defn rps [chr]
    (case chr
      (\A \X) 0
      (\B \Y) 1
      (\C \Z) 2))

  ;; Outcome of a round, given shapes of each player
  ;; outcome[shape of p1, shape of p2] => {0: loss, 1: draw, 2: win}
  (def outcome '((1 2 0)
                 (0 1 2)
                 (2 0 1)))

  (defn get2d [matrix x y]
    (-> matrix (nth x) (nth y)))

  (defn decode-round [round]
    ;; NOTE: destructuring, "unused argument" ignores space (see 'weird characters')
    (let [[p1 _ p2] round]
      (list (rps p1) (rps p2))))

  ;; return score given shapes of both players
  (defn score [shape-indexes]
    (let [[p1 p2] shape-indexes]
      (+
       ;; Add score for "shape"
       (+ 1 p2)
       ;; Add score for round outcome
       (* 3 (get2d outcome p1 p2)))))

                                        ; part 2

  ;; shape[shape of p1, outcome] => shape of p2, that achieves the outcome
  ;; Curiously, this p2-shape matrix is the same as the outcome matrix
  (def shape '((2 0 1)
               (0 1 2)
               (1 2 0)))

  ;; Takes an "encrypted round" like "A X" (where X is the outcome, not the shape of p2)
  ;; Return the shape-indexes for the two players [p1 p2]
  (defn decode-round-encrypted [round-encrypted]
    (let [[p1 _ outcome] round-encrypted
          p1 (rps p1)
          outcome (rps outcome)]
      (list p1 (get2d shape p1 outcome))))

  (list
   ;; NOTE: this use of threading + anonymous function #(-> % ...) to fit "map"
   "part 1" (apply + (map #(-> % decode-round score) rounds))
   "part 2" (apply + (map #(-> % decode-round-encrypted score) rounds)))
  )

(comment (solve))

