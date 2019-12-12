
import Control.Monad.State
import Control.Monad.Reader
import UI.NCurses
import qualified Data.Vector as V
import Data.Maybe
import Control.Monad.Catch


data FieldItem = Wall | Target | Road | FieldError | Border | StartPos
    deriving(Eq, Show)

toFieldItem :: Char -> FieldItem
toFieldItem c = case c of
		'x' -> Target
		'#' -> Wall
		' ' -> Road
		'I' -> StartPos
		_ -> FieldError


toChar :: FieldItem -> Char
toChar item = case item of
		Wall -> '#'
		Border -> 'o'
		Target -> 'x'
		Road -> ' '
		StartPos -> ' '
		FieldError -> '$'

type Field = V.Vector(V.Vector FieldItem)


fieldStrExample = "    #   \n\
                  \   ###  \n\
                  \ I # x  \n\
                  \  ###   \n\
                  \        "

tests_toField = [ isJust $ toField fieldStrExample 
        , isJust $ toField "I"
        , isNothing $ toField "    #   \n\
  \   ###   "
        , isNothing $ toField"  @I#   \n\
  \   ###  "
        ]


data Ending = Died | Win | Interrupted | Quit
  deriving Eq

data GameState = GameState {pos::Either Ending (Int, Int)}
type Game a = StateT GameState (ReaderT Field Curses) a

startGame::Field -> (Int, Int) -> Curses()
startGame field startPos = runReaderT (evalStateT (drawField >> drawGame >> (gameScript startPos))(GameState (Right startPos))) field

gameScript::(Int, Int) -> Game()
gameScript startPos = do 
    gameStep
    GameState cur <- get
    if (cur == Left Quit) then
        return()    
    else do
        put $ case cur of 
            Left Interrupted -> GameState $ Right startPos
            Right p -> GameState $ Right p
            Left err -> GameState $ Left err
        drawGame
        gameScript startPos
        

fieldX = 0
fieldY = 1

messageX = 0
messageY = 0

drawMessage :: String -> Update()
drawMessage str = moveCursor messageY messageX >> drawString str 

ui = lift.lift

drawField::Game()
drawField = do
        field <- ask
        ui $ do
            w <- defaultWindow
            updateWindow w $ do
                moveCursor fieldY fieldX
                drawString $ unlines  $ V.toList $ (V.map $ (map toChar).V.toList) field
            render        
        return()

moveCursorInField::(Int, Int) -> Update()
moveCursorInField(x, y) = moveCursor(fieldY + toInteger y)(fieldX + toInteger x)


drawGame::Game()
drawGame = do
    GameState cur <- get
    ui $ do
        w <- defaultWindow
        updateWindow w $ do
            case cur of 
                Left Win -> drawMessage "you win :) , press r to restart"
                Left Died -> drawMessage "you lose :( , press r to restart"
                Right pos -> drawMessage"                               " >> moveCursorInField pos
        render 
    return() 


gameStep::Game()
gameStep = do
    mev <- ui $ defaultWindow >>= \w -> getEvent w Nothing
    case mev of
      Just ev -> case ev of
        EventSpecialKey KeyUpArrow -> move (0, -1)
        EventSpecialKey KeyDownArrow -> move (0,  1)
        EventSpecialKey KeyLeftArrow -> move (-1, 0)
        EventSpecialKey KeyRightArrow -> move ( 1, 0)
        EventCharacter 'q' -> put $ GameState $ Left Quit 
        EventCharacter 'r' -> put $ GameState $ Left Interrupted
                
        _ -> pure()
      Nothing -> pure()
    where
        move::(Int, Int) -> Game()
        move(dx, dy) = do
            GameState cur <- get
            field <- ask
            case cur of
                Right(x, y) -> do
                    put $ 
                        case field V.!(y + dy)V.!(x + dx) of 
                            Wall -> GameState $ cur
                            Road -> GameState $ Right (x + dx, y + dy)
                            StartPos -> GameState $ Right (x + dx, y + dy)
                            Border -> GameState $ Left Died
                            Target -> GameState $ Left Win            
                Left _ -> return ()
            drawGame

main::IO()
main = do
    print "tests: "
    print tests_toField
    print "after tests"
    case toField fieldStrExample of
        Just (field, p) -> runCurses $ do setEcho False; startGame field p
        _ -> putStrLn "Wrong field"
        
 
fieldToList::Field -> [[FieldItem]]
fieldToList field = V.toList $ V.map V.toList field


toField::String -> Maybe(Field, (Int, Int))
toField s = if (checkSymbols s && checkShape s) then 
                let field' = field s in do 
                    pos <- findStartPos $ fieldToList field'
                    return (field', pos)
            else Nothing
    where
        toFieldItems::String -> [[FieldItem]]
        toFieldItems s = map(map toFieldItem)(lines s) 
          
        checkSymbols::String -> Bool
        checkSymbols s = (and $ map (not.(elem FieldError)) (toFieldItems s)) &&
                         (1 == (sum.(map length).(map $ filter (== StartPos)).toFieldItems) s)
        
        checkShape::String -> Bool
        checkShape [] = False
        checkShape s  = let lens = map length (lines s) in 
            ((==) <$> Prelude.maximum <*> Prelude.minimum) lens

        addBorder::[[FieldItem]] -> [[FieldItem]]
        addBorder items = let (line:lines) = map(\x -> [Border] ++ x ++ [Border]) items 
                              n = length line
                              topBorder = replicate n Border
                          in
                              [topBorder] ++ (line:lines) ++ [topBorder]

        field::String -> Field
        field s = V.fromList $ map V.fromList (addBorder $ toFieldItems s)
    
        findStartPos::[[FieldItem]] -> Maybe(Int, Int)
        findStartPos = helper 0
            where        
                helper::Int -> [[FieldItem]] -> Maybe(Int, Int)
                helper y [] = Nothing
                helper y (items:itemss) = let lst = filter (\p -> snd p == StartPos) (zip [0..] items) in
                                          case lst of
                                               ((x, _):_) -> Just (x, y)
                                               [] -> helper (y + 1) itemss
