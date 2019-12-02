module Hamming (distance) where
import Data.Char (isSpace)
trim :: String -> String
trim = f . f
  where f = reverse . dropWhile isSpace

distance :: String -> String -> Maybe Int
distance xs ys =
 if null xs || null ys then Just 0
 else if length (trim xs) /= length (trim ys) then Nothing
 else Just (length (filter id (zipWith (/=) (trim xs) (trim ys))))