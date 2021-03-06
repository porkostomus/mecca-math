(ns mecca-math.latex-test
  (:require [clojure.test :refer [deftest testing is run-tests]]
            [mecca-math.latex :as SUT]))

(deftest poly->latex-test
  (testing "All coefficients greater than 1"
    (is (= "2x^2+7x+6" (SUT/poly->latex [2 7 6] "x"))))
  (testing "All negative coefficients"
    (is (= "-2x^2-7x-6" (SUT/poly->latex [-2 -7 -6] "x"))))
  (testing "All coefficients 1"
    (is (= "x^2+x+1" (SUT/poly->latex [1 1 1] "x"))))
  (testing "Coefficient of 0"
    (is (= "x+1" (SUT/poly->latex [0 1 1] "x")))))

(deftest div-test
  (testing "Render result of polynomial division with no remainder"
    (is (= (SUT/div {:variable "x"
                 :term-list [5 0 0 0]})
           "5x^3")))
  (testing "Render result of polynomial division with positive remainder"
    (is (= (SUT/div {:variable "x"
                 :term-list [5 0 0 0]
                 :remainder 9})
           "5x^3+\\dfrac{9}{x}")))
  (testing "Render result of polynomial division with negative remainder"
    (is (= (SUT/div {:variable "x"
                 :term-list [5 0 0 0]
                 :remainder -9})
           "5x^3-\\dfrac{9}{x}"))))

(run-tests)