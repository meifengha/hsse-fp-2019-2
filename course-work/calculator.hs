module Calculator where
import Data.List
import Data.Char
calculator2 :: String -> Int
count :: Int -> String -> String -> Int
helper :: Int -> String -> [String] -> Int

count a b "+" =  a + read b :: Int
count a b "-" =  a - read b :: Int
count a b "*" =  a * read b :: Int
count a b operation = error " not permitted"

helper res op tokens | (length tokens == 1) = count res (head tokens) op
                     | otherwise = helper (count res (head (tokens)) op) (head (tail tokens)) (tail (tail tokens));

calculator expr = helper 0 "+" (words expr)
