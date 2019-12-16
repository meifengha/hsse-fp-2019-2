module Main where

import Calculator

main :: IO()
main = do
    putStrLn "Enter the expression, please: "
    input <- getLine
    putStr "The result: "
    print $ calculate input