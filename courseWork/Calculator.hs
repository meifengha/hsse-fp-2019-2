import Text.Parsec
import Text.Parsec.String
import Text.Parsec.Token
import Text.Parsec.Language
import Text.Parsec.Expr

lexis :: TokenParser ()
lexis = makeTokenParser (javaStyle { opStart  = oneOf "+-*/%"
                                   , opLetter = oneOf "+-*/%" })
                                  
getNumber :: Parser Double
getNumber = do
    value <- naturalOrFloat lexis
    case value of
        Left i -> return $ fromIntegral i
        Right num -> return $ num
        
input :: Parser Double
input = do
    whiteSpace lexis
    num <- analysisExpression
    eof
    return num
        
doubleMod :: Double -> Double -> Double
doubleMod top bottom = fromInteger $ (floor top) `mod` (floor bottom)

analysisExpression :: Parser Double
analysisExpression = (flip buildExpressionParser) term $ [
    [ Prefix (reservedOp lexis "-" >> return negate)
    , Prefix (reservedOp lexis "+" >> return id) ]
  , [ Infix  (reservedOp lexis "*" >> return (*)) AssocLeft
    , Infix  (reservedOp lexis "/" >> return (/)) AssocLeft 
    , Infix  (reservedOp lexis "%" >> return doubleMod) AssocLeft ] 
  , [ Infix  (reservedOp lexis "+" >> return (+)) AssocLeft
    , Infix  (reservedOp lexis "-" >> return (-)) AssocLeft ]
  ]

term :: Parser Double
term = parens lexis analysisExpression <|> getNumber

calculate :: String -> String
calculate str =
    case ret of
        Left e -> "Error: " ++ (show e)
        Right num -> "Answer: " ++ (show num)
    where
        ret = parse input "" str
    
main :: IO ()
main = interact (unlines . (map calculate) . lines)