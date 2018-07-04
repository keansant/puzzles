(ns ashikasoft.puzzles.dcube-test
  (:require [ashikasoft.puzzles.dcube :refer :all]
            [clojure.test :refer :all]))

(defn repeat-on-data
  "Call a function f on the given data, then call it again on the return value, a total of n times."
  [data n f]
  (reduce #(%2 %1) data (repeat n f)))

(deftest test-initial-cube
  (testing "initial position of top row"
    (is (= [7 6 5
            8   4
            1 2 3]
           (subvec initial-cube 0 8))))
  (testing "initial position of bottom row"
    (is (= [17 16 15
            18    14
            11 12 13]
           (subvec initial-cube 12)))))

(deftest test-rtop-
  (testing "rtop- once turns a side once."
    (is (= [1 8 7
            2   6
            3 4 5]
           (-> initial-cube rtop- (subvec 0 8))))) 
  (testing "rtop- 3 times is the same as rtop+ once."
    (is (= (rtop+ initial-cube)
           (repeat-on-data initial-cube 3 rtop-)))) 
  (testing "rtop- 4 times goes back to the original."
    (is (= initial-cube
           (repeat-on-data initial-cube 4 rtop-)))))

(deftest test-rtop+
  (testing "rtop+ once turns a side once."
    (is (= [5 4 3
            6   2
            7 8 1]
           (-> initial-cube rtop+ (subvec 0 8))))) 
  (testing "rtop+ 3 times is the same as rtop- once."
    (is (= (rtop- initial-cube)
           (repeat-on-data initial-cube 3 rtop+)))) 
  (testing "rtop+ 4 times goes back to the original."
    (is (= initial-cube
           (repeat-on-data initial-cube 4 rtop+)))))

(deftest test-rbottom-
  (testing "rbottom- once turns a side once."
    (is (= [11 18 17
            12    16
            13 14 15]
           (-> initial-cube rbottom- (subvec 12)))))
  (testing "rbottom- 3 times is the same as rbottom+ once."
    (is (= (rbottom+ initial-cube)
           (repeat-on-data initial-cube 3 rbottom-))) 
   (testing "rbottom- 4 times goes back to the original."
    (is (= initial-cube
           (repeat-on-data initial-cube 4 rbottom-))))))

(deftest test-rbottom+
  (testing "rbottom+ once turns a side once."
    (is (= [15 14 13
            16    12
            17 18 11]
           (-> initial-cube rbottom+ (subvec 12)))))
  (testing "rbottom+ 3 times is the same as rbottom- once."
    (is (= (rbottom- initial-cube)
           (repeat-on-data initial-cube 3 rbottom+))) 
   (testing "rbottom+ 4 times goes back to the original."
    (is (= initial-cube
           (repeat-on-data initial-cube 4 rbottom+))))))

(deftest test-rleft-
  (testing "rleft- once steps once."
    (is (= [1 6 5 9 4 17 2 3 8 18 10 20 11 16 15 19 14 7 12 13]
           (rleft- initial-cube)))) 
  (testing "rleft- 3 times is the same as rleft+ once."
    (is (= (rleft+ initial-cube)
           (repeat-on-data initial-cube 3 rleft-))) 
   (testing "rleft- 4 times goes back to the original."
    (is (= initial-cube
           (repeat-on-data initial-cube 4 rleft-))))))

(deftest test-rleft+
  (testing "rleft+ once steps once."
    (is (= [11 6 5 19 4 7 2 3 18 8 10 20 1 16 15 9 14 17 12 13]
           (rleft+ initial-cube)))) 
  (testing "rleft+ 3 times is the same as rleft- once."
    (is (= (rleft- initial-cube)
           (repeat-on-data initial-cube 3 rleft+))) 
   (testing "rleft+ 4 times goes back to the original."
    (is (= initial-cube
           (repeat-on-data initial-cube 4 rleft+))))))

(deftest test-rright-
  (testing "rright- once steps once."
    (is (= [7 6 3 8 10 1 2 15 19 9 14 4 17 16 13 18 20 11 12 5]
           (rright- initial-cube)))) 
  (testing "rright- 3 times is the same as rright+ once."
    (is (= (rright+ initial-cube)
           (repeat-on-data initial-cube 3 rright-))) 
   (testing "rright- 4 times goes back to the original."
    (is (= initial-cube
           (repeat-on-data initial-cube 4 rright-))))))

(deftest test-rright+
  (testing "rright+ once steps once."
    (is (= [7 6 13 8 20 1 2 5 19 9 4 14 17 16 3 18 10 11 12 15]
           (rright+ initial-cube)))) 
  (testing "rright+ 3 times is the same as rright- once."
    (is (= (rright- initial-cube)
           (repeat-on-data initial-cube 3 rright+))) 
   (testing "rright+ 4 times goes back to the original."
    (is (= initial-cube
           (repeat-on-data initial-cube 4 rright+))))))

(deftest test-next-steps
  (testing "step should generate all 8 next available steps when previous is empty."
    (is (= 8 (count (next-steps initial-cube #{}))))))
