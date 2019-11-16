module Hamming (distance) where

distance :: String -> String -> Maybe Int
distance (xl : xs) (yl : ys)
    | xl == yl = distance xs ys
    | otherwise = (+) 1 <$> distance xs ys
distance [] [] = Just 0
distance _ _ = Nothing
