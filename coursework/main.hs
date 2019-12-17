import Data.List

main = do
  z <- getLine
  putStrLn . show .calcRPN . read $ z


calcRPN :: (Num a, Read a) => [Char] -> a
calcRPN = head . foldl rpn [] . words
  where rpn (x:y:xs) "+" = (x + y) : xs  
        rpn (x:y:xs) "*" = (x * y) : xs
        rpn (x:y:xs) "-" = (x - y) : xs
        rpn xs nc = read nc : xs
