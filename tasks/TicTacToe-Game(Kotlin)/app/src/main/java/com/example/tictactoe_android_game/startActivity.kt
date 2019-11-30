package com.example.tictactoe_android_game

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_start.*
import kotlinx.android.synthetic.main.twoplayerdialog.view.*

class startActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        oneplayer.setOnClickListener() {
            val intent = Intent(this,onePlayerActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        twoplayer.setOnClickListener() {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.twoplayerdialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Enter Details")
            val  mAlertDialog = mBuilder.show()
            mDialogView.ok.setOnClickListener {
                mAlertDialog.dismiss()
                val player1name = mDialogView.player1.text.toString()
                val player2name = mDialogView.player2.text.toString()
                if(player1name.isEmpty() || player2name.isEmpty())
                    Toast.makeText(this,"Check the Accuracy of Details",Toast.LENGTH_SHORT).show()
                else
                {
                    val intent= Intent(this,twoPlayerActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    intent.putExtra("player1name",player1name)
                    intent.putExtra("player2name",player2name)
                    startActivity(intent)
                }

            }

            mDialogView.cancel.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }
          exit.setOnClickListener(){
              finish()
              System.exit(0)
        }
    }

    override fun onBackPressed() {
        val build= AlertDialog.Builder(this)
        build.setTitle("END GAME?")
        build.setMessage("Are you Sure you want to End Game")
        build.setPositiveButton("YES") {build,which ->
            build.dismiss()
            System.exit(0)
        }
        build.setNegativeButton("NO"){build, which ->
            build.dismiss()
        }
        val dialog: AlertDialog = build.create()
        dialog.show()
    }
}
