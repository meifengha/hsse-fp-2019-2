module Main where
import Data.Char
import Data.List
main :: IO ()

data Token = Number Int | Add | Subt
             | Mult | Divide  | PowerOf | Lparen | Rparen
            deriving (Show, Eq)
            
toStr :: Token -> String
toStr Add  = "+"
toStr Subt = "-"
toStr Mult = "*"
toStr Divide  = "/"
toStr PowerOf = "^"
toStr Lparen = "("
toStr Rparen = ")"

toToken :: String -> Token
toToken "+" = Add   
toToken "-" = Subt  
toToken "*" = Mult  
toToken "/" = Divide    
toToken "^" = PowerOf  
toToken "(" = Lparen  
toToken ")" = Rparen  
toToken "0" = Number 0  
toToken "1" = Number 1  
toToken "2" = Number 2  
toToken "3" = Number 3  
toToken "4" = Number 4  
toToken "5" = Number 5  
toToken "6" = Number 6  
toToken "7" = Number 7  
toToken "8" = Number 8  
toToken "9" = Number 9  
toToken _ = error "invalid token"

--Shunting Yard algorithm to convert infix to postfix
--             input    operators output    return list
toPostfix' :: [Char] -> [Char] -> [Char] -> [Char]
toPostfix' [] [] outQueue = reverse outQueue
toPostfix' [] (op:ops) outQueue = toPostfix' [] ops (op:outQueue)
toPostfix' (token:tokens) opStack outQueue
    | isDigit token = toPostfix' tokens opStack (token:outQueue)
    | isOp token = case opStack of
        [] -> toPostfix' tokens (token:opStack) outQueue
        (op2:ops) -> if ((leftAssc token) && ((precedence token) <= (precedence op2))) || ((rightAssc token) && ((precedence token) < (precedence op2)))
        then toPostfix' (token:tokens) ops (op2:outQueue) 
        else toPostfix' tokens (token:opStack) outQueue
    | lParen token = toPostfix' tokens (token:opStack) outQueue
    | rParen token = case opStack of
        ('(':ops) -> toPostfix' tokens ops outQueue
        (op:ops) -> toPostfix' (token:tokens) ops (op:outQueue)
        
--Evaluate Postfix Expression
toPostfix :: [Char] -> [Char]
toPostfix tokens = toPostfix' tokens [] []

postfixEval :: [Char] -> Int
postfixEval ex = head (foldl func [] ex)
    where func (x:y:xs) '+' = (x + y):xs
          func (x:y:xs) '-' = (y - x):xs
          func (x:y:xs) '*' = (x * y):xs
          func (x:y:xs) '/' = (y `div` x):xs
          func (x:y:xs) '^' = (y ^ x):xs                              
          func xs digit = (digitToInt digit):xs

-- checks whether token is operator
isOp :: Char -> Bool
isOp '+' = True
isOp '-' = True
isOp '*' = True
isOp '/' = True
isOp '^' = True
isOp _ = False

--Left associative operators
leftAssc ::Char -> Bool
leftAssc '+' = True
leftAssc '-' = True
leftAssc '*' = True
leftAssc '/' = True
leftAssc _ = False

--Right assosciative operators
rightAssc ::Char -> Bool
rightAssc '^' = True
rightAssc _ = False

--set operator precedence
precedence :: Char -> Int
precedence '+' = 2
precedence '-' = 2
precedence '*' = 3
precedence '/' = 3
precedence '^' = 4
precedence _ = 0

--parentheses

lParen :: Char -> Bool
lParen  '(' = True
lParen _ = False

rParen :: Char -> Bool
rParen  ')' = True
rParen _ = False

main = do 
putStrLn "Enter expression (no spaces):"
input <- getLine

let postFix = toPostfix input
putStrLn " "
putStrLn "The expression in postFix:"
putStrLn postFix
putStrLn "/n"
let result = postfixEval postFix
putStr "result:"
print result
