package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvOne.setOnClickListener() { appendExpression("1", true) }
        tvTwo.setOnClickListener() { appendExpression("2", true) }
        tvThree.setOnClickListener() { appendExpression("3", true) }
        tvFour.setOnClickListener() { appendExpression("4", true) }
        tvFive.setOnClickListener() { appendExpression("5", true) }
        tvSix.setOnClickListener() { appendExpression("6", true) }
        tvSeven.setOnClickListener() { appendExpression("7", true) }
        tvEight.setOnClickListener() { appendExpression("8", true) }
        tvNine.setOnClickListener() { appendExpression("9", true) }
        tvZero.setOnClickListener() { appendExpression("0", true) }
        tvDot.setOnClickListener() { appendExpression(".", true) }

        tvPlus.setOnClickListener() { appendExpression("+", false) }
        tvMinus.setOnClickListener() { appendExpression("-", false) }
        tvMult.setOnClickListener() { appendExpression("*", false) }
        tvDiv.setOnClickListener() { appendExpression("/", false) }
        tvOpenPar.setOnClickListener() { appendExpression("(", false) }
        tvClosePar.setOnClickListener() { appendExpression(")", false) }

        tvCE.setOnClickListener() {
            tvExpression.text = ""
            tvResult.text = ""
        }

        tvBack.setOnClickListener() {
            val string = tvExpression.text.toString()
            if (string.isNotEmpty()) {
                tvExpression.text = string.substring(0, string.length - 1)
            }
            tvResult.text = ""
        }

        tvEquals.setOnClickListener() {
            try {
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    tvResult.text = longResult.toString()
                } else {
                    tvResult.text = result.toString()
                }
            } catch (e:Exception) {
                //Log.d ("Exception", " message: " + e.message)
            }
        }
    }

    fun appendExpression(string:String, canClear:Boolean) {
        if (tvResult.text.isNotEmpty()) {
            tvExpression.text = ""
        }

        if (canClear) {
            tvResult.text = ""
            tvExpression.append(string)
        } else {
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text = ""
        }
    }
}
