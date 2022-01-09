(ns london-quadratic.formula-test
  (:require [clojure.test :refer :all]
            [mockfn.macros :refer [providing]]
            [london-quadratic.fixtures :as f]
            [london-quadratic.discriminant :refer :all]
            [london-quadratic.formula :as formula])
  (:import (clojure.lang ExceptionInfo)))

(use-fixtures :once f/schema-validation)

(deftest quadratic-test
  (testing "given a =1, b=-1 and c = -12 when calculating the roots of the quadratic equation should return a set containing 4 and -3"
    (providing [(discriminant 1M -1M -12M) 49M]
               (is (= #{4M -3M} (formula/quadratic-formula 1M -1M -12M)))))
  (testing "given a is not a BigDecimal should throw an exception"
    (is (thrown? ExceptionInfo (formula/quadratic-formula 1 -1M -12M))))
  (testing "given b is not a BigDecimal should throw an exception"
    (is (thrown? ExceptionInfo (formula/quadratic-formula 1M -1 -12M))))
  (testing "given c is not a BigDecimal should throw an exception"
    (is (thrown? ExceptionInfo (formula/quadratic-formula 1M -1M -12)))))
