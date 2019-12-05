module Hamming (distance) where

distance :: String -> String -> Maybe Int
distance [] [] = Just 0
distance _ [] = Nothing
distance [] _ = Nothing
distance (x:xs) (y:ys) = case distance xs ys of
                            Just n -> Just ((if x /= y then 1 else 0) + n)
                            Nothing -> Nothing
