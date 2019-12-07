module Hamming (distance) where

helper :: String -> String -> Int;
helper xs ys
      | (length xs) == 0 = 0
      | head xs == head ys = helper (tail xs) (tail ys)
      | otherwise = 1 + helper (tail xs) (tail ys)

distance :: String -> String -> Maybe Int;
distance xs ys
      | (length xs) /= (length ys) = Nothing
      | otherwise = Just (helper xs ys)

main = print(distance "bba" "aab");
