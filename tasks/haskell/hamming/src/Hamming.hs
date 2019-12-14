module Hamming (distance) where
import Data.List
import Data.Maybe

distance :: String -> String -> Maybe Int

distance xs ys | (length xs == length ys) = Just (implDistance xs ys 0)
               | otherwise = Nothing

implDistance xs ys acc | (length xs == 0) = acc
               | (head xs /= head ys) = implDistance (tail xs) (tail ys) (acc + 1)
               | otherwise = implDistance (tail xs) (tail ys) (acc)
