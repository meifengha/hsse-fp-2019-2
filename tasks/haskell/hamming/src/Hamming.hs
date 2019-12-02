module Hamming (distance) where

distance :: String -> String -> Maybe Int
distance (x:xs) (y:ys) | x /= y = fmap (+1) dis
                       | otherwise = dis
    where dis = distance xs ys
distance [] [] = Just 0
distance _ _ = Nothing
