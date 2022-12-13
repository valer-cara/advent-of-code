(ns advent-of-code.day1
  (:require [clojure.string :as str]))

(defn solve
  "I don't do a whole lot."
  []

  (def elves
    (let [in (slurp "./src/advent_of_code/day1.input.txt")
          cals (str/split in #"\n\n")]
      (map (fn [elf] (->>
                      (str/split elf #"\n")
                      (map #(Integer/parseInt %))
                      (reduce +)))
           cals)
      ))

  (list
   "part 1" (apply max elves)
   "part 2" (apply + (take-last 3 (sort elves)))
   )
  )

#_(solve)

