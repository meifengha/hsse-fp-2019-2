import Graphics.UI.WX
import Data.Maybe
import Data.List.Split
import Text.Read (readMaybe)

main :: IO ()
main = start $ do
  f        <- frame [text := "Calculator"]
  display  <- textEntry f []
  btnPanel <- panel f []
  equals   <- button f [text := "=", on command := get display text >>= (\str -> set display [text := evaluate str])]
  buttons  <- mapM (\n -> button btnPanel [text := n, on command := appendText display n]) $ ["+","-","/","*","."] ++ (show <$> [0..9])
  set btnPanel [layout := boxed "buttons" (grid 2 2 (chunksOf 4 $ widget <$> buttons))]
  set f [layout := column 10 [widget display, widget equals, widget btnPanel]]

evaluate :: String -> String
evaluate "" = ""
evaluate str = fromMaybe "Error" $ do
  (astr,bstr) <- case splitOneOf "+-*/" str of a:b:_ -> Just (a,b)
                                               _     -> Nothing
  a <- readMaybe astr; b <- readMaybe bstr
  opch <- case drop (length astr) str of []   -> Nothing
                                         op:_ -> Just op
  op <- ch2op opch
  return $ show $ op a b

ch2op :: Char -> Maybe (Float -> Float -> Float)
ch2op '+' = Just (+)
ch2op '-' = Just (-)
ch2op '*' = Just (*)
ch2op '/' = Just (/)
ch2op  _  = Nothing

