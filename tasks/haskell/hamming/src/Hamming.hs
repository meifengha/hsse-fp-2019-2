module Hamming (distance) where

distance :: String -> String -> Maybe Int

distance xs ys 
| length xs /= length ys = Nothing
| null xs || null ys = Just 0
| otherwise = Just (sum [1 | pair <- zip xs ys, fst pair /= snd pair])
