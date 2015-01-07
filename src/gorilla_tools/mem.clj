(ns gorilla-tools.mem
  (:require [taoensso.nippy :as nippy]
            [clojure.pprint :refer [ print-table]]
            [clojure.core.matrix :as m]
            ))

(defn short-to-string [value]
  (let [to-string (.toString value)]
    (clojure.string/join ["\""
                          (clojure.string/replace (subs to-string 0 (min 50 (count to-string)))"\n" " ")
                          "\""])  ))

(defn- format-var [var]
  (let [value (var-get var)
        bytes (try (taoensso.nippy/freeze value)
                   (catch Exception e (byte-array 0)))
        count-or-tostring (if (m/matrix? value)
                            (m/shape value)
                            (try (count value)
                                 (catch Exception e  (short-to-string value))))]
    {:var (.sym var)
     :class (subs (str (class value)) 5)
     :count-or-tostring count-or-tostring
     :size (count bytes)
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
