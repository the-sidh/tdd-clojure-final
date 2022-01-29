(ns london-quadratic.formula-test
  (:require [clojure.test :refer :all]
            [mockfn.macros :refer [providing]]
            [london-quadratic.fixtures :as f]
            [london-quadratic.discriminant :refer :all]
            [london-quadratic.formula :as formula]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.properties :as prop]
            )
  (:import (clojure.lang ExceptionInfo)))

(use-fixtures :once f/schema-validation)

(deftest quadratic-test
  (testing "given the discriminant is positive should return a set with two items"
    (providing [(discriminant 1M -1M -12M) 49M]
               (is (= #{4M -3M} (formula/quadratic-formula 1M -1M -12M)))))
  (testing "given the discriminant is Zero should return a set with only one item"
    (providing [(discriminant 4M -4M -1M) 0M]
               (is (= #{0.5M} (formula/quadratic-formula 4M -4M -1M)))))
  (testing "given the discriminant is negative should return an empty set"
    (providing [(discriminant 1M -4M 5M) -4M]
               (is (= #{} (formula/quadratic-formula 1M -4M 5M)))))
  (testing "given a is not a BigDecimal should throw an exception"
    (is (thrown? ExceptionInfo (formula/quadratic-formula 1 -1M -12M))))
  (testing "given b is not a BigDecimal should throw an exception"
    (is (thrown? ExceptionInfo (formula/quadratic-formula 1M -1 -12M))))
  (testing "given c is not a BigDecimal should throw an exception"
    (is (thrown? ExceptionInfo (formula/quadratic-formula 1M -1M -12))))
  (testing "given a is zero should throw an exception"
    (is (thrown? ExceptionInfo (formula/quadratic-formula 0M -1M -12M))))
  (testing "given b is zero should throw an exception"
    (is (thrown? ExceptionInfo (formula/quadratic-formula 1M 0M -12M))))
  (testing "given a is zero should throw an exception"
    (is (thrown? ExceptionInfo (formula/quadratic-formula 2M -1M 0M)))))


(def non-zero-big-decimal
  (gen/fmap #(bigdec %) (gen/such-that #(not (= 0 %)) gen/large-integer)))

(defspec size-is-expected 100
         (prop/for-all [a non-zero-big-decimal
                        b non-zero-big-decimal
                        c non-zero-big-decimal]
                       (contains? #{0 1 2} (count (london-quadratic.formula/quadratic-formula
                                                    a
                                                    b
                                                    c)))))