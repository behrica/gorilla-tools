(ns gorilla-tools.mem-test
  (:require [midje.sweet :refer :all]
            [gorilla-tools.mem :refer :all]))

(fact "Can print-vars"
      (print-var-info) => nil)
