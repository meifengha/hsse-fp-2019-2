package com.example.tictactoe_android_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.tictactoe.BoardClass.onePlayerBoard
import com.example.tictactoe.Cell
import kotlinx.android.synthetic.main.activity_one_player.*

class onePlayerActivity : AppCompatActivity() {

    //define array for board of null
    private  val boardCells=Array(3){ arrayOfNulls<ImageView>(3)}
    var computorScore:Int = 0
    var playerscore:Int  = 0

    var board = onePlayerBoard()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_player)

        //load board
        loadBoard()

        button_restart_oneplayer.setOnClickListener{
            board= onePlayerBoard()
            text_view_result_oneplayer.text=""
            mapBoardToUi()
        }
        
        btn_exit.setOnClickListener {
            val intent = Intent(this,startActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    private fun mapBoardToUi() {
        for (i in board.board.indices) {
            for (j in board.board.indices) {
                when(board.board[i][j]) {
                    onePlayerBoard.PLAYER -> {
                        boardCells[i][j]?.setImageResource(R.drawable.circle)
                        boardCells[i][j]?.isEnabled=false
                    }
                    onePlayerBoard.COMPUTOR -> {
                        boardCells[i][j]?.setImageResource(R.drawable.cross)
                        boardCells[i][j]?.isEnabled=false
                    }
                    else -> {
                        boardCells[i][j]?.setImageResource(0)
                        boardCells[i][j]?.isEnabled=true
                    }
                }
            }
        }
    }

    private fun loadBoard() {
        for(i in boardCells.indices){
            for (j in boardCells.indices){
                boardCells[i][j]= ImageView(this)
                boardCells[i][j]?.layoutParams= GridLayout.LayoutParams().apply {
                    rowSpec = GridLayout.spec(i)
                    columnSpec = GridLayout.spec(j)
                    width = 250
                    height = 230
                    bottomMargin = 5
                    topMargin = 5
                    leftMargin = 5
                    rightMargin = 5
                }
                boardCells[i][j]?.setBackgroundColor(ContextCompat.getColor(this,R.color.colorPrimary))
                boardCells[i][j]?.setOnClickListener(CellClickListener(i,j))
                layout_board_oneplayer.addView(boardCells[i][j])
            }
        }
    }

    inner class CellClickListener(private  val i:Int,private  val j:Int) : View.OnClickListener{
        override fun onClick(p0: View) {
            if(!board.isGameOver) {
                val cell = Cell(i,j)
                board.placeMove(cell, onePlayerBoard.PLAYER)
                board.minimax(0, onePlayerBoard.COMPUTOR)
                board.computersMove?.let {
                    board.placeMove(it, onePlayerBoard.COMPUTOR)
                }
                mapBoardToUi()
            }
            when{
                board.hasComputorWon() -> {
                    text_view_result_oneplayer.text="COMPUTOR WON"
                    computorScore++
                    tvcomp.setText("Computor : "+computorScore)
                }
                board.hasPlayerWon() -> {
                    text_view_result_oneplayer.text="PLAYER WON"
                    playerscore++
                    tvplayer.setText("Player : "+playerscore)
                }
                board.isGameOver -> text_view_result_oneplayer.text="GAME Tied"
            }
        }
    }

    override fun onBackPressed() {
       val build=AlertDialog.Builder(this)
        build.setTitle("END GAME?")
        build.setMessage("Are you Sure you want to End Game")
        build.setPositiveButton("YES") {build,which ->
            finish()
            val intent = Intent(this,startActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        build.setNegativeButton("NO"){build, which ->
        }
        val dialog: AlertDialog = build.create()
        dialog.show()
    }
}
