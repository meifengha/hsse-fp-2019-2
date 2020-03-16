module Hamming (distance) where

distance :: String -> String -> Maybe Int
distance [] [] = Just 0
distance _ _ = Nothing
distance (x:xs) (y:ys)
      		 | x /= y = fmap (1 +)(distance xs ys)
     		 | otherwise = distance xs ys
