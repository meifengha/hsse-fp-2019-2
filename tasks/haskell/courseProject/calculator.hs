import Text.Parsec
import Text.Parsec.String
import Text.Parsec.Token
import Text.Parsec.Language
import Text.Parsec.Expr

lexer :: TokenParser() --Lexical analysis
lexer = makeTokenParser (javaStyle { opStart  = oneOf "+-*/"
                                   , opLetter = oneOf "+-*/" })

parseNumber :: Parser Double 
parseNumber = do
  num <- naturalOrFloat lexer 
  case num of
    Left int -> return $ fromIntegral int --convert Int in different Num
    Right n -> return $ n --return Double

parseExpression :: Parser Double
parseExpression = (flip buildExpressionParser) parseItem $  [
    [ Prefix (reservedOp lexer "-" >> return negate) ],
    [ Infix  (reservedOp lexer "+" >> return (+)) AssocLeft,
      Infix  (reservedOp lexer "-" >> return (-)) AssocLeft],
    [ Infix  (reservedOp lexer "*" >> return (*)) AssocLeft,
      Infix  (reservedOp lexer "/" >> return (/)) AssocLeft]
  ] --evaluate the function flipping the order of arg

parseItem :: Parser Double
parseItem = parens lexer parseExpression <|> parseNumber --expr with brackets

parseInput :: Parser Double
parseInput = do
  n <- parseExpression
  eof
  return n

calculate :: String -> String
calculate s =
  case ret of
    Left e -> "error: " ++ (show e)
    Right n -> "answer: " ++ (show n)
  where
    ret = parse parseInput "" s

eachLine :: (String -> String) -> (String -> String)
eachLine calculate = unlines . (map calculate) . lines

main :: IO ()
main = interact (eachLine calculate)
