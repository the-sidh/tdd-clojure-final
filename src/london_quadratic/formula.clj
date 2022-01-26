(ns london-quadratic.formula
  (:require [london-quadratic.discriminant :refer :all]
            [schema.core :as s]))

(s/defn quadratic-formula [a :- BigDecimal
                           b :- BigDecimal
                           c :- BigDecimal]
  (let [discriminant (double (discriminant a b c))
        sqrt-discriminant (bigdec (Math/sqrt discriminant))
        minus-b (.negate b)
        two-times-a (.multiply a 2M)]
    #{(-> minus-b (- sqrt-discriminant) (/ two-times-a))
      (-> minus-b (+ sqrt-discriminant) (/ two-times-a))}))