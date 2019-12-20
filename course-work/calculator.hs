module Calculator3 where
import Data.List
import Data.Char
calculator3 :: String -> Float
count :: Float -> String -> String -> Float
helper :: Float -> String -> [String] -> Float

count a b "+" = a + read b :: Float
count a b "-" = a - read b :: Float
count a b "*" = a * read b :: Float
count a b "/" = a / read b :: Float
count a b operation = error "Operation not permitted"

helper res op tokens | (length tokens == 1) = count res (head tokens) op
                     | otherwise = helper (count res (head (tokens)) op) (head (tail tokens)) (tail (tail tokens));

calculator3 expr = helper 0 "+" (words expr)
