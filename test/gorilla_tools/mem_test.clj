(ns gorilla-tools.mem-test
  (:require [clojure.test :refer :all]
            [gorilla-tools.mem :refer :all]))

(deftest a-test
  (testing "print var info"
    (print-var-info)
    ))
