module Calc
( Expression(..),
  parse,
  calculate
) where

import Control.Applicative
import Parser

data Expression = Add Expression Expression
                | Sub Expression Expression
                | Mul Expression Expression
                | Div Expression Expression

evaluate :: Expression -> Integer
evaluate (Add lhs rhs) = evaluate lhs + evaluate rhs
evaluate (Sub lhs rhs) = evaluate lhs - evaluate rhs
evaluate (Mul lhs rhs) = evaluate lhs * evaluate rhs
evaluate (Div lhs rhs) = evaluate lhs `div` evaluate rhs

whitespace :: Char -> Parser String Char
whitespace c = between spaces spaces (char c)

literal :: Parser String Expression
literal = Lit . read <$> (spaces *> many1 digit <* spaces)

add :: Parser String Expression
add = Add <$> term <*> (whitespace '+' *> expr)

sub :: Parser String Expression
sub = Sub <$> term <*> (whitespace '-' *> expr)

mul :: Parser String Expression
mul = Mul <$> factor <*> (whitespace '*' *> term)

divide :: Parser String Expression
divide = Div <$> factor <*> (whitespace '/' *> term)

parens :: Parser String Expression
parens = between (whitespace '(') (whitespace ')') expr

factor :: Parser String Expression
factor = literal <|> parens

term :: Parser String Expression
term = mul <|> divide <|> factor

expr :: Parser String Expression
expr = add <|> sub <|> term

parse :: String -> Maybe Expression
parse = fmap fst . runParser (expr <* eos)

calculate :: String -> Maybe Integer
calculate = fmap evaluate . parse
