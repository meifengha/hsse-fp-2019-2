package poly.company.calc

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            if (getScreenOrientation()!!) {
                initForPortrait()
            } else {
                initForLand()
            }
        } catch (e: Exception) {
            Log.d("Exception ", " message :" + e.message)
        }
    }


    private fun initForPortrait() {
        //Numbers + Operator
        initPress()
    }


    private fun initForLand() {
        initPress()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("Expressive", tvExpressive.text.toString())
        outState.putString("Result", tvResult.text.toString())

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        tvExpressive.text = savedInstanceState.getString("Expressive")
        tvResult.text = savedInstanceState.getString("Result")
    }

    private fun Boolean.appendOnExpression(string: String) {
        /** Variable Optics  tvExpressive.text = "" */
        if (tvResult.text.isNotEmpty()) {
            tvExpressive.text = tvResult.text
        }
        if (this) {
            tvResult.text = ""
            tvExpressive.append(string)
        } else {
            tvExpressive.append(tvResult.text)
            tvExpressive.append(string)
            tvResult.text = ""
        }
    }

    private fun getScreenOrientation(): Boolean? {
        return when (resources.configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> true
            Configuration.ORIENTATION_LANDSCAPE -> false
            else -> false
        }
    }

    private fun initPress() {
        //Numbers
        tvOne.setOnClickListener { true.appendOnExpression("1") }
        tvTwo.setOnClickListener { true.appendOnExpression("2") }
        tvThree.setOnClickListener { true.appendOnExpression("3") }
        tvFour.setOnClickListener { true.appendOnExpression("4") }
        tvFive.setOnClickListener { true.appendOnExpression("5") }
        tvSix.setOnClickListener { true.appendOnExpression("6") }
        tvSeven.setOnClickListener { true.appendOnExpression("7") }
        tvEight.setOnClickListener { true.appendOnExpression("8") }
        tvNine.setOnClickListener { true.appendOnExpression("9") }
        tvZero.setOnClickListener { true.appendOnExpression("0") }
        tvDot.setOnClickListener { true.appendOnExpression(".") }

        //Operators
        tvPlus.setOnClickListener { true.appendOnExpression("+") }
        tvMinus.setOnClickListener { true.appendOnExpression("-") }
        tvMul.setOnClickListener { true.appendOnExpression("*") }
        tvDivide.setOnClickListener { true.appendOnExpression("/") }
        tvOpen.setOnClickListener { true.appendOnExpression("(") }
        tvClose.setOnClickListener { true.appendOnExpression(")") }

        if (getScreenOrientation() == false) {
            tvSin?.setOnClickListener { true.appendOnExpression("sin") }
            tvCos?.setOnClickListener { true.appendOnExpression("cos") }
            tvTg?.setOnClickListener { true.appendOnExpression("tan") }
            tvCtg?.setOnClickListener { true.appendOnExpression("atan") }

            tvPi?.setOnClickListener { true.appendOnExpression("pi") }
            tvExp?.setOnClickListener { true.appendOnExpression("exp") }
            tvSqrt?.setOnClickListener { true.appendOnExpression("sqrt") }
            tvAbs?.setOnClickListener { true.appendOnExpression("abs") }
        }

        tvClear.setOnClickListener {
            tvExpressive.text = ""
            tvResult.text = ""
        }

        tvBack.setOnClickListener {
            val string = tvExpressive.text.toString()
            if (string.isNotEmpty()) {
                tvExpressive.text = string.substring(0, string.length - 1)
            }
            tvResult.text = ""
        }


        tvEquals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tvExpressive.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()

                if (result == longResult.toDouble())
                    tvResult.text = longResult.toString()
                else
                    tvResult.text = result.toString()

            } catch (e: Exception) {
                Log.d("Exception ", " message :" + e.message)
            }
        }
    }
}



