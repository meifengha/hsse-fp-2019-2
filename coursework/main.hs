module Main where

import System.Environment (getArgs)
import Calculator

main :: IO ()
main = do
  args <- getArgs
  case calculate (unwords args) of
    Nothing -> putStrLn "Error"
    Just val -> print val
