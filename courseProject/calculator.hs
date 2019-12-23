import Text.Parsec
import Text.Parsec.String
import Text.Parsec.Token
import Text.Parsec.Expr
import Text.Parsec.Language

import qualified Data.Map as M

import qualified Control.Monad.State as S

import Control.Monad.Error
import Control.Monad.Identity

def = emptyDef { identStart  = letter,
                   identLetter = alphaNum,
                   opLetter    = oneOf "+-*/="
                 }

lexer :: TokenParser ()
lexer = makeTokenParser def

data Expr = Constant Double
                | Identifier String
                | Add Expr Expr
                | Sub Expr Expr
                | Mul Expr Expr
                | Div Expr Exapr
--Parser
parseNumber :: Parser Expr
parseNumber = do
    v <- naturalOrFloat lexer
    case v of
        Left  i -> return $ Constant $ fromIntegral i
        Right n -> return $ Constant n

parseIdentifier :: Parser Expr
parseIdentifier = do
   i <- identifier lexer
   return $ Identifier i
   
parseExpression :: Parser Expr
parseExpression = (flip buildExpressionParser) parseTerm [
   [ Infix (reservedOp lexer "*" >> return Mul) AssocLeft
   , Infix (reservedOp lexer "/" >> return Div) AssocLeft
   ]
 , [ Infix (reservedOp lexer "+" >> return Add) AssocLeft
   , Infix (reservedOp lexer "-" >> return Sub) AssocLeft 
   ]

 ]

parseTerm :: Parser Expr
parseTerm = parens lexer parseExpression 
        <|> parseNumber
        <|> parseIdentifier

parseInput :: Parser Expr
parseInput = do
    whiteSpace lexer
    ex <- parseExpression
    eof
    return ex

-- Calculator

type SymTab = M.Map String Double

type Evaluator a = S.StateT SymTab (ErrorT String Identity) a

runEvaluator :: Evaluator Double -> SymTab -> Either String (Double, SymTab)
runEvaluator calc symTab = runIdentity $ runErrorT $ S.runStateT calc symTab

evaluate :: Expr -> Evaluator Double

evaluate (Constant x) = return x

evaluate (Identifier i) = do
    symtab <- S.get
    case M.lookup i symtab of
        Nothing -> fail $ "Undefined variable " ++ i
        Just e  -> return e

evaluate (Add lhs rhs) = do
    lft <- evaluate lhs
    rgt <- evaluate rhs
    return $ lft + rgt

evaluate (Sub lhs rhs) = do
    lft <- evaluate lhs
    rgt <- evaluate rhs
    return $ lft - rgt

evaluate (Mul lhs rhs) = do
    lft <- evaluate lhs
    rgt <- evaluate rhs
    return $ lft * rgt

evaluate (Div lhs rhs) = do
    lft <- evaluate lhs
    rgt <- evaluate rhs
    return $ lft / rgt


defaultVars :: M.Map String Double
defaultVars = M.fromList
   [ ("e", exp 1)
   , ("pi", pi)
   ]
   
calculate :: SymTab -> String -> (String, SymTab)
calculate symTab s = 
    case parse parseInput "" s of
    Left  err -> ("error: " ++ (show err), symTab)
    Right exp -> case runEvaluator (evaluate exp) symTab of
                 Left  err              -> ("error: " ++ err, symTab)
                 Right (val, newSymTab) -> (show val, newSymTab)

loop :: SymTab -> IO ()
loop symTab = do
    line <- getLine
    if null line
    then return ()
    else do
        let (result, symTab') = calculate symTab line
        putStrLn result
        loop symTab'
   
main = loop defaultVars