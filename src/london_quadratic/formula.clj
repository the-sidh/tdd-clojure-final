(ns london-quadratic.formula
  (:require [london-quadratic.discriminant :refer :all]
            [schema.core :as s]
            [london-quadratic.schemas.coefficient :as coefficient]
            ))

(defn- sqrt [number]
  (let [result (Math/sqrt number)]
    (if (Double/isNaN result)
      (throw (ArithmeticException.))
      result)))

(s/defn quadratic-formula [a :- coefficient/bigdecimal-not-zero
                           b :- coefficient/bigdecimal-not-zero
                           c :- coefficient/bigdecimal-not-zero]
  (try
    (let [discriminant (double (discriminant a b c))
          sqrt-discriminant (bigdec (sqrt discriminant))
          minus-b (.negate b)
          two-times-a (.multiply a 2M)]
      (set [(-> minus-b (- sqrt-discriminant) (/ two-times-a))
            (-> minus-b (+ sqrt-discriminant) (/ two-times-a))]))
    (catch ArithmeticException _ #{})))
