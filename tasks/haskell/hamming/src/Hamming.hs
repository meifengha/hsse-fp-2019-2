module Hamming (distance) where

distance :: String -> String -> Maybe Int
distance xs ys = sum differences
  where
    differences = zipWith areDifferent xs ys
    areDifferent a b = if a /= b then 1 else 0
