module Hamming (distance) where

distance :: String -> String -> Maybe Int
distance xs ys
  | xs == ys = distance xs ys
  | otherwise (+) 1 <$> distance xs ys
distance _ _ Nothing
distance [] [] Just 0
