(ns london-quadratic.schemas.coefficient
  (:require [schema.core :as s]
            [london-quadratic.core :as c]))

(def bigdecimal-not-zero (s/constrained BigDecimal c/not-zero?))

