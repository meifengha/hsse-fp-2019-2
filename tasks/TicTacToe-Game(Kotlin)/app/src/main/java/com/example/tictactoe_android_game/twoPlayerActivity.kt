package com.example.tictactoe_android_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.example.tictactoe.BoardClass.twoPlayerBoard
import com.example.tictactoe.Cell
import kotlinx.android.synthetic.main.activity_one_player.*
import kotlinx.android.synthetic.main.activity_two_player.*
import kotlinx.android.synthetic.main.twoplayerdialog.*

class twoPlayerActivity : AppCompatActivity() {

    //define array for board of null
    private  val boardCells=Array(3){ arrayOfNulls<ImageView>(3)}

    var move : Int =1
    var player1Score : Int = 0
    var player1name : String = ""
    var player2name : String  = ""
    var player2Score : Int = 0

    var board = twoPlayerBoard()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two_player)
        //load board
        loadBoard()

        player1name= intent.getStringExtra("player1name")
        player2name= intent.getStringExtra("player2name")

        tvplayer1.setText(player1name+" : 0")
        tvplayer2.setText(player2name+" : 0")

        button_restart_twoplayer.setOnClickListener{
            board= twoPlayerBoard()
            text_view_result_twoplayer.text=""
            mapBoardToUi()
        }

        btn_exitt.setOnClickListener {
            val intent = Intent(this,startActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }

    private fun mapBoardToUi() {
        for (i in board.board.indices) {
            for (j in board.board.indices) {
                when(board.board[i][j]) {
                    twoPlayerBoard.PLAYER1 -> {
                        boardCells[i][j]?.setImageResource(R.drawable.circle)
                        boardCells[i][j]?.isEnabled=false
                    }
                    twoPlayerBoard.PLAYER2 -> {
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
                layout_board_twoplayer.addView(boardCells[i][j])
            }
        }
    }

    inner class CellClickListener(private  val i:Int,private  val j:Int) : View.OnClickListener{
        override fun onClick(p0: View) {
            if(!board.isGameOver) {
                val cell = Cell(i,j)
                if(move==0) {
                    board.placeMove(cell, twoPlayerBoard.PLAYER1)
                    move=1
                }
                else{
                    board.placeMove(cell, twoPlayerBoard.PLAYER2)
                    move=0
                }
                mapBoardToUi()
            }
            when{
                board.hasPlayer1() -> {
                    player1Score++
                    tvplayer1.setText(player1name+" : "+player1Score)
                    text_view_result_twoplayer.text="PLAYER1 WON"
                }
                board.hasPlayer2() ->{
                    player2Score++
                    tvplayer2.setText(player2name+" : "+player2Score)
                    text_view_result_twoplayer.text="PLAYER2 WON"
                }
                board.isGameOver -> text_view_result_twoplayer.text="GAME Tied"
            }
        }
    }
    override fun onBackPressed() {
        val build= AlertDialog.Builder(this)
        build.setTitle("END GAME?")
        build.setMessage("Are you Sure you want to End Game")
        build.setPositiveButton("YES") {build,which ->
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
