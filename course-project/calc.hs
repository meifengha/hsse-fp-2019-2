module Calculator
( calculate
, calculateRPN
, convertToRPN
) where

import Data.Char
import Data.List

calculate :: String -> Int
calculate []   = 0
calculate expr = calculateRPN $ convertToRPN expr

calculateRPN :: String -> Int
calculateRPN expr = head (foldl compute [] expr)
    where
        compute (x:y:zs) '+'   = (x + y):zs
        compute (x:y:zs) '-'   = (y - x):zs
        compute (x:y:zs) '*'   = (x * y):zs
        compute (x:y:zs) '/'   = (y `div` x):zs
        compute zs digit       = (digitToInt digit):zs

convertToRPN :: String -> String
convertToRPN tokens = shuntingYard tokens [] []

shuntingYard :: String -> String -> String -> String
shuntingYard [] [] out = reverse out
shuntingYard [] (op:ops') out = shuntingYard [] ops' (op:out)
shuntingYard (token:tokens) ops out
    | isDigit token = shuntingYard tokens ops (token:out)
    | isOperator token = case ops of
        [] -> shuntingYard tokens (token:ops) out
        (op':ops') -> if ((opPrecedence token) <= (opPrecedence op'))
                     then shuntingYard (token:tokens) ops' (op':out)
                     else shuntingYard tokens (token:ops) out
    | isLeftParen token = shuntingYard tokens (token:ops) out
    | isRightParen token = case ops of
        ('(':ops') -> shuntingYard tokens ops' out
        (op:ops') -> shuntingYard (token:tokens) ops' (op:out)

isOperator :: Char -> Bool
isOperator '+' = True 
isOperator '-' = True
isOperator '*' = True
isOperator '/' = True
isOperator _   = False

isLeftParen :: Char -> Bool
isLeftParen '(' = True
isLeftParen _   = False

isRightParen :: Char -> Bool
isRightParen ')' = True
isRightParen _   = False

opPrecedence :: Char -> Int
opPrecedence '+' = 2
opPrecedence '-' = 2
opPrecedence '*' = 3
opPrecedence '/' = 3
opPrecedence _   = 0
