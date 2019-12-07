module Hamming (distance) where

distance :: String -> String -> Maybe Int
distance "" "" = Just 0
distance xs ys 
	| length xs /= length = Nothing
	| (last xs == last ys)= next 
	| otherwise = fmap (+1) next 
	where next = distance (init xs) (init ys)