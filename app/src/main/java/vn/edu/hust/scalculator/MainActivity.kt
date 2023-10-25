package vn.edu.hust.scalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import net.objecthunter.exp4j.ExpressionBuilder
import vn.edu.hust.scalculator.databinding.ActivityMainBinding
import java.time.temporal.Temporal
import java.util.Stack

class MainActivity : AppCompatActivity() {

    private var canAddOperation = false

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var displayText = binding.display


        binding.zeroBTN.setOnClickListener({
            if (displayText.text.length != 0) {
                val ch = displayText.text.get(displayText.text.length - 1)
                if (ch != '/') displayText.text.append("0")
            }
            else displayText.text.append("0")
            canAddOperation = true
        })

        binding.oneBTN.setOnClickListener({
            displayText.text.append("1")
            canAddOperation = true
        })

        binding.twoBTN.setOnClickListener({
            displayText.text.append("2")
            canAddOperation = true
        })

        binding.threeBTN.setOnClickListener({
            displayText.text.append("3")
            canAddOperation = true
        })

        binding.fourBTN.setOnClickListener({
            displayText.text.append("4")
            canAddOperation = true
        })

        binding.fiveBTN.setOnClickListener({
            displayText.text.append("5")
            canAddOperation = true
        })

        binding.sixBTN.setOnClickListener({
            displayText.text.append("6")
            canAddOperation = true
        })

        binding.sevenBTN.setOnClickListener({
            displayText.text.append("7")
            canAddOperation = true
        })

        binding.eightBTN.setOnClickListener({
            displayText.text.append("8")
            canAddOperation = true
        })

        binding.nineBTN.setOnClickListener({
            displayText.text.append("9")
            canAddOperation = true
        })

        binding.plusBTN.setOnClickListener({
            if (canAddOperation) {
                displayText.text.append("+")
                canAddOperation = false
            }
        })

        binding.minusBTN.setOnClickListener({
            if (canAddOperation){
                displayText.text.append("-")
                canAddOperation = false
            }
        })

        binding.divideBTN.setOnClickListener({
            if (canAddOperation){
                displayText.text.append("/")
                canAddOperation = false
            }
        })

        binding.multiplyBTN.setOnClickListener({
            if (canAddOperation){
                displayText.text.append("x")
                canAddOperation = false
            }
        })

        binding.ceBTN.setOnClickListener({
            if (displayText.text.length != 0) {
                for (i in (displayText.text.length - 1) downTo 0) {
                    if (!(displayText.text.get(i) <= '9' && displayText.text.get(i) >= '0')) {
                        displayText.text.delete(i + 1, displayText.text.length)
                        break;
                    }
                    if (i == 0)
                        displayText.text.clear()
                }
            }
        })

        binding.cBTN.setOnClickListener({
            displayText.setText("")
        })

        binding.bsBTN.setOnClickListener({
            if (displayText.text.length != 0)
                displayText.text.delete(displayText.text.length - 1, displayText.text.length)
        })

        binding.negateBTN.setOnClickListener({
            if (displayText.text.length != 0) {
                for (i in displayText.text.length - 1 downTo 0) {
                    if (displayText.text.get(i) > '9' || displayText.text.get(i) < '0') {
                        if (displayText.text.get(i) != '-') {
                            displayText.text.insert(i + 1, "-")
                        }
                        else {
                            displayText.text.replace(i, i + 1, "+")
                            if (i == 0){
                                if (displayText.text.get(0) == '+') displayText.text.delete(0, 1)
                            }
                            else {
                                if (displayText.text.get(i - 1) == '+'
                                    || displayText.text.get(i - 1) == 'x'
                                    || displayText.text.get(i - 1) == '/' ) displayText.text.delete(i, i + 1)
                            }
                        }
                        return@setOnClickListener
                    }
                }

                displayText.text.insert(0, "-")
            }
        })

        binding.equalBTN.setOnClickListener({

            //using exp4j to evaluate math expressions
            var expression = displayText.text.toString()

            if (expression.isNotEmpty()) {
                expression = expression.replace("x", "*")
                val exp = ExpressionBuilder(expression).build().evaluate().toInt().toString()
                displayText.setText(exp)
            }
        })
    }
}

