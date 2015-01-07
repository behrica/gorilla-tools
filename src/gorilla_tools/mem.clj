(ns gorilla-tools.mem
  (:require [taoensso.nippy :as nippy]
            [clojure.pprint :refer [ print-table]]
            ))

(defn- format-var [var]
  (let [value (var-get var)]
    {:var (.sym var)
     :class (subs (str (class value)) 5)
     :count (if (or (instance? clojure.lang.IPersistentCollection value) (string? value) ) (count  value)
       		   (let [to-string (.toString value)]
                (clojure.string/replace (subs to-string 0 (min 30 (count to-string)))"\n" " ")))
     :size (if (nippy/freezable? value {:allow-java-serializable? true}) (count (nippy/freeze value)) -1)
     ;;:size-1 (count (nippy/freeze value))
     }))

(defn print-var-info
  "Prints a table with memory info for the vars of the current namespace.

  It prints tow values usefull for a size estimation:
  - count: For seq-lie objects the 'count' is shown, the toString elsewhere
  - size: teh var gets serialized with ´nippy´and teh length of the resulting byte array is shown

  Doing this has two sideefects:
    - lazy sequences gets realised
    - vars get serialized with nippy to count the bytes
  "
  []
  (clojure.pprint/print-table (reverse (sort-by :size (map format-var (vals (ns-interns *ns*)))))))
