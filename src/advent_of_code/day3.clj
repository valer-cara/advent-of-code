(ns advent-of-code.day3
  (:require [clojure.string :as str]))

(defn solve []
  ;; TODO figure out this inline defs, they suck indeed, (let?)
  (def input (str/split (slurp "./src/advent_of_code/day3.input.txt") #"\n"))

  ;; TODO: use tests to provide input like
  ;; https://github.com/rjray/advent-2020-clojure/blob/master/test/advent_of_code/day01_test.clj

  ;; TODO: find a project, input pattern looking at
  ;; https://github.com/Bogdanp/awesome-advent-of-code#clojure

  (list
   "part 1" 1234
   "part 2" 9999))

#_(solve)

(comment
  (reductions + [1 1 2 3 4 5 6])

  )
