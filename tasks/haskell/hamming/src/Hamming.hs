module Hamming (distance) where

distance :: String -> String -> Maybe Int
distance "" "" = Just 0
distance xs ys 
  | (length xs /= length = Nothing 
  | (last xs == last ys)= space
  | otherwise = fmap (+1) space
  where space = distance (init xs) (init ys)