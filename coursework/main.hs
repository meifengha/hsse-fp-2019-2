data Token = PlusToken | IntToken Int deriving (Show)

buildIntToken [] = []
buildIntToken value = [IntToken (read value::Int)]

tokenize :: [Char] -> [Token]
tokenize list = let (a, b) = foldr breakToken ([], []) list in (buildIntToken a) ++ b
      where
        breakToken '+' (current, output) = ([], PlusToken : ((buildIntToken current) ++ output))
        breakToken num (current, output) = (num:current, output)

main = print $ tokenize "10+1+21+5009"