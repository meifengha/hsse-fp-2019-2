import Graphics.UI.GLUT
import Data.IORef
import Control.Monad
import System.Random

data Direction = LeftD | RightD | UpD | DownD
data Colour = RedC | BlackC | WhiteC
type Coordinate = (Int, Int)
type Space = (Int, Int, Int)
type Board = [[Space]]

data GameState = GS {
                    snakeLength :: Int,
                    board :: Board,
                    currentPos :: Coordinate,
                    foodPos :: Coordinate,
                    currentDirection :: Direction,
                    gameOver :: Bool
                    }


numRows :: Int
numRows = 20

numCols :: Int
numCols = 20

squareWidth :: GLfloat
squareWidth = 2.0 / (fromIntegral numCols)

squareHeight :: GLfloat
squareHeight = 2.0 / (fromIntegral numRows)

startBoard :: Board
startBoard = [[(0, r, c) | c <- [0..numCols - 1]] | r <- [0..numRows - 1]]

startGS :: GameState
startGS = GS 1 startBoard (0, 0) (0, 1) RightD False

gapTime :: Timeout
gapTime = 100

startGap :: Timeout
startGap = 500


main :: IO ()
main = do
  (_progName, _args) <- getArgsAndInitialize
  _window <- createWindow "SNAKE"
  gameState <- newIORef startGS
  displayCallback $= display gameState
  keyboardMouseCallback $= Just (keyboardMouse gameState)
  addTimerCallback startGap (update gameState)
  mainLoop


display :: IORef GameState -> DisplayCallback
display gameState = do
  clear [ ColorBuffer ]
  initialDisplayMode $= [DoubleBuffered]
  gs <- get gameState
  renderPrimitive Quads $ (render gs)
  flush

render :: GameState -> IO ()
render (GS _ board _ foodPos _ _) = mapM_ helper (concat displayGrid)
  where displayGrid = toDisplayGrid board foodPos
        color3f r g b = color $ Color3 r g (b::GLfloat)
        vertex3f x y z = vertex $ Vertex3 x y z
        square row col = do vertex3f  (c'*squareWidth)      (r'*squareHeight)       0
                            vertex3f ((c'+1.0)*squareWidth) (r'*squareHeight)       0
                            vertex3f ((c'+1.0)*squareWidth) ((r'+1.0)*squareHeight) 0
                            vertex3f  (c'*squareWidth)      ((r'+1.0)*squareHeight) 0
          where c' = col - (fromIntegral numCols) / 2.0 -- OpenGL puts 0 in the centre
                r' = row - (fromIntegral numRows) / 2.0
        helper (colour, row, col) = do case colour of
                                            RedC   -> color3f 1 0 0
                                            BlackC -> color3f 0 0 0
                                            WhiteC -> color3f 1 1 1
                                       square row col


toDisplayGrid :: Board -> Coordinate -> [[(Colour, GLfloat, GLfloat)]]
toDisplayGrid board (row, col) = map (map helper) board
  where helper (val, r, c) | r == row && c == col = (RedC, r', c')
                           | val > 0              = (BlackC, r', c')
                           | val <= 0             = (WhiteC, r', c')
          where r' = fromIntegral r
                c' = fromIntegral c


keyboardMouse :: IORef GameState-> KeyboardMouseCallback
keyboardMouse gs key Down _ _ = do
  GS s b cp fp d g <- get gs
  writeIORef gs
             (GS s b cp fp
                 (case key of
                   (SpecialKey KeyLeft ) -> LeftD
                   (SpecialKey KeyRight) -> RightD
                   (SpecialKey KeyUp   ) -> UpD
                   (SpecialKey KeyDown ) -> DownD
                   _                     -> d)
                 g)
keyboardMouse _ _ _ _ _ = return ()


update ::  IORef GameState -> TimerCallback
update gs = do
  GS s b cp fp d g <- get gs
  let (r, c) = nextSpace cp d
      space = getSpace b r c
  case space of
       Nothing -> writeIORef gs (GS s b cp fp d False)
       Just (val, _, _) -> if val > 0
                           then writeIORef gs (GS s b cp fp d False)
                           else do let new = advanceSnake b (r, c) s
                                   when ((r, c) == fp) (score gs)
                                   GS s' _ _ fp' d' g' <- get gs
                                   writeIORef gs (GS s' new (r, c) fp' d' g')
                                   addTimerCallback gapTime (update gs)
  postRedisplay Nothing

score :: IORef GameState -> IO ()
score gs = do
  GS s b cp fp d g <- get gs
  writeIORef gs (GS (s+1) b cp fp d g)
  moveFood gs

moveFood :: IORef GameState -> IO ()
moveFood gs = do
  GS s b cp fp d g <- get gs
  r <- getStdRandom $ randomR (0, numRows - 1)
  c <- getStdRandom $ randomR (0, numCols - 1)
  let sp = getSpace b r c
  case sp of
    Nothing -> moveFood gs
    Just (val, _, _) -> if (val > 0)
                        then moveFood gs
                        else writeIORef gs (GS s b cp (r, c) d g)

nextSpace :: Coordinate -> Direction -> Coordinate
nextSpace (r, c) LeftD  = (r  , c-1)
nextSpace (r, c) RightD = (r  , c+1)
nextSpace (r, c) UpD    = (r+1, c  )
nextSpace (r, c) DownD  = (r-1, c  )


getSpace :: Board -> Int -> Int -> Maybe Space
getSpace board r c | r < 0        = Nothing
                   | c < 0        = Nothing
                   | r >= numRows = Nothing
                   | c >= numCols = Nothing
                   | otherwise    = Just ((board !! r) !! c)

advanceSnake :: Board -> Coordinate -> Int -> Board
advanceSnake board (nextRow, nextCol) snakeLength = map (map helper) board
  where helper (val, r, c) | r == nextRow && c == nextCol = (snakeLength, r, c)
                           | otherwise = (val - 1, r, c)
