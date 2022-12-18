(ns advent-of-code.day3
  (:require [clojure.string :as str]
            [clojure.tools.trace :refer :all]))

(def input (str/split (slurp "./src/advent_of_code/day3.input.txt") #"\n"))

(defn prio [chr]
  (let [base (if (>= (int chr) (int \a))
               (int \a)
               (- (int \A) 26))]
    (inc (- (int chr) base)))
  )

(defn revprio [prio]
  (let [base (if (< prio 27)
               (dec (int \a))
               (- (int \A) 27))]
    (char (+ prio base)))
  )

(assert (= (revprio (prio \m)) \m))
(assert (= (revprio (prio \M)) \M))

;; encode encodes the Prio and the position as a single number
;; by appending a 0/1 digit indicating whther it's on the left
(defn encode [pr left]
  (cond-> (* 10 pr) left inc)) ;; NOTE: cond-> is my favorite function so far!

(def encode-all (fn [len idx val]
                  (encode (prio val) (< idx (/ len 2)))))

;; Returns the first value in the reduction that's followed by it's consecutive
(def reduce-consecutive
  (fn [acc val]
    (if (= val (inc acc))
      (reduced (int (/ val 10))) ;; divide by 10 reverse the encoding
      val)))

(comment 
  (reduce reduce-consecutive [4 5])
  (reduce reduce-consecutive [4 8 10 15 15 20 22 23 50 55 60]))

(defn solve-single [in]
  (reduce reduce-consecutive
          (sort (map-indexed (partial encode-all (count in)) in))))

(assert (= (revprio (solve-single "vJrwpWtwJgWrhcsFMMfFFhFp")) \p))

(defn solve-p1 []
  (apply + (map solve-single input)))

#_(solve-p1)

                                        ; Part 2

;; Things I learned
;; Key functions:
;; - group-by
;; - partition (see related partition-by: really interesting)
;;
;; Ideas
;; - when threading, use double paren around ((partial ...)) because the threading macro splits the partial otherwise
;;   https://stackoverflow.com/questions/29188854/the-macro-and-partial-function
;;
;; Debugging
;; - use #break in cider to insert a breakpoint in a form
;; - see more https://valer.dev/posts/clojure-debugging/

(defn mapper [coll]
  (-> coll ((partial group-by prio)) keys))

(defn find-badge [items]
  ;; return the key of the first found element (ehh..)
  (first (first
    (filter #(= (count (second %)) 3) items))))

(find-badge {8 [8 8], 10 [10 10 10]})

(defn solve-group [group]
  (find-badge
  (group-by identity 
            (apply concat (map mapper group)))))


(defn solve-p2 []
  (apply + (map solve-group (partition 3 input))))

#_(solve-p2)

