(ns advent-of-code.day4
  (:require [clojure.string :refer [split]]
            [clojure.tools.trace :refer :all]))

(def input (split (slurp "./src/advent_of_code/day4.input.txt") #"\n"))

;; only used during REPL-development, shadowing func args
;; tbd if valid approach, but did help a bit this time
#_(def pair (first input))

(defn parse-pair [pair]
  (map #(Integer/parseInt %) (split pair #"[,-]")))

(assert (= (parse-pair "4-8,10-15") [4 8 10 15]))

;; Learned more debugging:
;; - debugging by instrumenting a defun with C-u C-M-x
;; - un-instrument a defun by re-evaluating it
;; - instrumented functions are surrounded by a red-box in emacs.
;;
;; Should re-read https://docs.cider.mx/cider/debugging/debugger.html

(defn pairs-contained? [pair]
  (let [[a b c d] (parse-pair pair)]
    (or (and (<= a c) (>= b d))
        (and (>= a c) (<= b d)))))


(assert (pairs-contained? "1-1,1-10"))
(assert (pairs-contained? "7-8,6-9"))
(assert (not (pairs-contained? "7-10,6-9")))

;; build the in/out list to debug, my answer's off (376)
;; inspecting this large vector with cider is great! don't even need to write a file.
;; ",dvl" in spacemacs
#_(def what (map vector input (map pair-overlaps? input)))

;; at this point i got the wrong answer so I switched to python
;;  a = [[([int(k) for k in j.split('-')]) for j in i.strip().split(',')] for i in x]
;;  sum([(i[0][0] <= i[1][0] and i[0][1]>=i[1][1]) or (i[0][0] >= i[1][0] and i[0][1]<=i[1][1]) for i in a])

(defn solve-p1 []
  (count (filter identity (map pairs-contained? input))))

#_(solve-p1)

                                        ; part 2

;; best! https://clojure.org/guides/repl/enhancing_your_repl_workflow#_reproducing_the_context_of_an_expression
(def pair "1-1,1-10")

;; basically draw out the scenarios.. ihh
;; other ways?
(defn pairs-overlap? [pair]
  (let [[a b c d] (parse-pair pair)]
    (or (and (<= a c) (>= b c))
        (and (>= a c) (>= d a)))))

(defn solve-p2 []
  (count (filter identity (map pairs-overlap? input))))

#_(solve-p2)
