module Expr where

import Control .Monad ( liftM2 )
import Text . Printf

data Op = Add | Sub | Mul | Div deriving (Eq)

data Expr = Con Double
    | Var String
    | Bin Op Expr Expr

instance Show Op where
    show Add = "+"
    show Sub = "−"
    show Mul = "∗"
    show Div = "/"
instance Show Expr where
show (Con c) = show c
show (Var v) = v
show (Bin op a b) = printf "(%s%s%s)" (show a) (show op) (show b)

type Env = [(String, Double)]

eval :: Env −> Expr −> Maybe Double
eval env (Con c) = Just c
eval env (Var v) = lookup v env
eval env (Bin op a b) = liftM2 (fn op) (eval env a) (eval env b)
where fn Add = (+)
      fn Sub = (−)
      fn Mul = (∗)
      fn Div = (/)
