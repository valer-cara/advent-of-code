(ns advent-of-code.core
  (:require [clojure.string :as str]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(def elves
  (let [in (slurp "./src/advent_of_code/day1.input.txt")
        cals (str/split in #"\n\n")]
    (map (fn [elf] (->>
                    (str/split elf #"\n")
                    (map #(Integer/parseInt %))
                    (reduce +)))
         cals)
    ))

(println (apply max elves))

