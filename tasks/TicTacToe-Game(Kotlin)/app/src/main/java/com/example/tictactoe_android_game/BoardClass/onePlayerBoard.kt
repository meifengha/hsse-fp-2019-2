package com.example.tictactoe.BoardClass

import com.example.tictactoe.Cell

class onePlayerBoard {
    companion object{
        const val PLAYER = "O"
        const val COMPUTOR = "X"
    }

    var board=Array(3){ arrayOfNulls<String>(3)}
    val availableCells: List<Cell>
        get(){
            val cells = mutableListOf<Cell>()
            for (i in board.indices) {
                for(j in board.indices) {
                    if(board[i][j].isNullOrEmpty()) {
                        cells.add(Cell(i, j))
                    }
                }
            }
            return cells
        }

    val isGameOver : Boolean
        get() = hasComputorWon() || hasPlayerWon() || availableCells.isEmpty()

    fun hasComputorWon():Boolean {
        if(board[0][0]==board[1][1] &&
            board[0][0]==board[2][2] &&
            board[0][0]== COMPUTOR ||
            board[0][2]==board[1][1] &&
            board[0][2]==board[2][0] &&
            board[0][2]== COMPUTOR
        ) {
            return true
        }
        for (i in board.indices) {
            if(board[i][0]==board[i][1] &&
                board[i][0]==board[i][2] &&
                board[i][0]== COMPUTOR ||
                board[0][i]==board[1][i] &&
                board[0][i]==board[2][i] &&
                board[0][i]== COMPUTOR
            ) {
                return true
            }
        }
        return false
    }

    fun hasPlayerWon():Boolean {
        if(board[0][0]==board[1][1] &&
            board[0][0]==board[2][2] &&
            board[0][0]== PLAYER ||
            board[0][2]==board[1][1] &&
            board[0][2]==board[2][0] &&
            board[0][2]== PLAYER
        ) {
            return true
        }
        for (i in board.indices) {
            if(board[i][0]==board[i][1] &&
                board[i][0]==board[i][2] &&
                board[i][0]== PLAYER ||
                board[0][i]==board[1][i] &&
                board[0][i]==board[2][i] &&
                board[0][i]== PLAYER
            ) {
                return true
            }
        }
        return false
    }

    var computersMove :  Cell? = null
    fun minimax(depth: Int , player : String) : Int {
        if(hasComputorWon()) return +1
        if(hasPlayerWon()) return -1
        if(availableCells.isEmpty()) return 1
        var min=Integer.MAX_VALUE
        var max=Integer.MIN_VALUE
        for(i in availableCells.indices) {
            val cell=availableCells[i]
            if(player== COMPUTOR) {
                placeMove(cell, COMPUTOR)
                val currentScore=minimax(depth +1, PLAYER)
                max =Math.max(currentScore,max)
                if(currentScore>=0) {
                    if(depth==0) computersMove = cell
                }
                if(currentScore==1) {
                    board[cell.i][cell.j] = ""
                    break
                }
                if(i==availableCells.size - 1 && max <0) {
                    if(depth==0) computersMove = cell
                }
            }
            else if(player== PLAYER) {
                placeMove(cell, PLAYER)
                val currentScore = minimax(depth+1, COMPUTOR)
                min = Math.min(currentScore,min)
                if(min==-1) {
                    board[cell.i][cell.j]=""
                    break
                }
            }
            board[cell.i][cell.j]=""
        }
        return if (player== COMPUTOR) max else min
    }

    fun placeMove(cell : Cell, player:String){
        board[cell.i][cell.j]=player
    }
}