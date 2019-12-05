module Hamming (distance) where

distance :: String -> String -> Maybe Int
distance (x : xs) (y : ys)
    | x /= y    = (+) 1 <$> distance xs ys
    | otherwise = distance xs ys
distance [] []  = Just 0
distance _ _    = Nothing
