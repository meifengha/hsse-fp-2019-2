module Hamming (distance) where

distance :: String -> String -> Maybe Int

distance xs ys 
<<<<<<< HEAD
 | length xs /= length ys = Nothing
 | null xs || null ys = Just 0
 | otherwise = Just (sum [1 | pair <- zip xs ys, fst pair /= snd pair])
=======
| length xs /= length ys = Nothing
| null xs || null ys = Just 0
| otherwise = Just (sum [1 | pair <- zip xs ys, fst pair /= snd pair])
>>>>>>> aa5413d61f501c1dc463e6f9123ef671f3632b62
