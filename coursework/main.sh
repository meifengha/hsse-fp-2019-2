data Token = PlusToken | IntToken Int deriving (Show)

buildToken [] = []
buildToken value = [IntToken (read value::Int)]

tokenize :: [Char] -> [Token]
tokenize expr = let (a, b) = foldr breakToken ([], []) expr in (buildToken a) ++ b
      where
        breakToken '+' (current, output) = ([], PlusToken : ((buildToken current) ++ output))
        breakToken num (current, output) = (num:current, output)

main = putStrLn "Enter expression"
