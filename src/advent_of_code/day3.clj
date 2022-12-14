(ns advent-of-code.day3
  (:require [clojure.string :as str]))

(defn solve []
  ;; TODO figure out this inline defs, they suck indeed, (let?)
  (def input (str/split (slurp "./src/advent_of_code/day3.input.txt") #"\n"))

  (list
   "part 1" 1234
   "part 2" 9999))

#_(solve)

(comment
  (reductions + [1 1 2 3 4 5 6])

  )
