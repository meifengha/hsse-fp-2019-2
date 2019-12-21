module Calculator where
import Data.List
import Data.Char

calculator :: String -> Float
calculator expr = processToken [] [] (words expr)

processToken :: [Float] -> [String] -> [String] -> Float
processToken numbers [] [] | (length numbers == 1) = head numbers
                           | otherwise = error "Error while counting expression"
processToken numbers operations [] = processToken (count n2 n1 op : restN) restOp []
                                     where op = head operations
                                           restOp = tail operations
                                           n1 = head numbers
                                           n2 = head (tail numbers)
                                           restN = tail (tail numbers)

processToken numbers operations tokens | (isOp t == True) =
                                          if (lenOp == 0) then processToken numbers (t : operations) (tail tokens)
                                             else if (prior op >= prior t) then processToken (count n2 n1 op : restN) (t : restOp) (tail tokens)
                                                else processToken numbers (t : operations) (tail tokens)
                                       | otherwise = processToken ((read t :: Float) : numbers) operations (tail tokens)
                                       where t = head tokens
                                             op = head operations
                                             restOp = tail operations
                                             n1 = head numbers
                                             n2 = head (tail numbers)
                                             restN = tail (tail numbers)
                                             lenOp = length operations

count :: Float -> Float -> String -> Float
count a b "+" = a + b
count a b "-" = a - b
count a b "*" = a * b
count a b "/" = a / b
count a b operation = error "Operation not permitted"

isOp :: String -> Bool
isOp "+" = True
isOp "-" = True
isOp "*" = True
isOp "/" = True
isOp op = False

prior "*" = 2
prior "/" = 2
prior "+" = 1
prior "-" = 1
prior op = 0
