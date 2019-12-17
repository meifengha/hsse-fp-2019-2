module Hamming (distance) where

distance :: String -> String -> Maybe Int

distance xs ys =  if null xs || null ys then Just 0
                  else if (length xs /= length ys) then Nothing
                  else if (length xs == 0) then Just 0
                  else case distance (tail xs) (tail ys) of
                  Just n -> if head xs /= head ys then Just (1 + n) else Just n
                  Nothing -> Nothing