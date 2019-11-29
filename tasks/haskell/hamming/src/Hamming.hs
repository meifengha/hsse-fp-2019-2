module Hamming (distance) where

distance :: String -> String -> Maybe Int
distance xs ys  | (length xs) /= (length ys) = Nothing | otherwise = dist 0 xs ys                
  where
    dist counter [] [] = Just counter
    dist counter (x:xs) (y:ys) = 
      if x /= y
        then dist (counter+1) xs ys
      else dist counter xs ys