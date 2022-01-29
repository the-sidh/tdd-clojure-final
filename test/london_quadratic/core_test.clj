(ns london-quadratic.core-test
  (:require [clojure.test :refer :all]
            [london-quadratic.core :refer :all]))


(deftest not-zero?-test
  (testing "given zero, should return false"
    (is (false? (not-zero? 0M))))
  (testing "given a number bigger than zero, should return true"
    (is (true? (not-zero? 10M))))
  (testing "given a number lesser than zero, should return true"
    (is (true? (not-zero? -10M)))))
