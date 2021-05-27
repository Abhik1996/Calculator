package com.example.calculator

import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView



private  const val STATE_PENDING_OPERATION = "pendingOperation"
private  const val   STATE_OPERAND1  = "Operand1"
private const val STATE_OPERA1_STORE = "Operand1_Stored"

class MainActivity : AppCompatActivity() {

    private lateinit var result: TextView
    private lateinit var newNumber: EditText
    private val displayOperation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation) }

    private var operand1: Double? = null

    //    private var operand2: Double = 0.0
    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)
        newNumber = findViewById(R.id.NewNum)

        val button0: ImageButton = findViewById(R.id.button0)
        val button1: ImageButton = findViewById(R.id.button10)
        val button2: ImageButton = findViewById(R.id.button2)
        val button3: ImageButton = findViewById(R.id.button3)
        val button4: ImageButton = findViewById(R.id.button4)
        val button5: ImageButton = findViewById(R.id.button5)
        val button6: ImageButton = findViewById(R.id.button6)
        val button7: ImageButton = findViewById(R.id.button7)
        val button8: ImageButton = findViewById(R.id.button8)
        val button9: ImageButton = findViewById(R.id.button9)
        val buttonDot: ImageButton = findViewById(R.id.buttonDot)

        val buttonEqual: ImageButton = findViewById<ImageButton>(R.id.buttonEqual)
        val buttonDivide: ImageButton = findViewById<ImageButton>(R.id.buttonDivide)
        val buttonSub: ImageButton = findViewById<ImageButton>(R.id.buttonSub)
        val buttonAdd: ImageButton = findViewById<ImageButton>(R.id.buttonAdd)
        val buttonMulti: ImageButton = findViewById<ImageButton>(R.id.buttonMulti)
        val buttonNeg: ImageButton = findViewById(R.id.buttonNeg)
        val buttonClear: ImageButton = findViewById<ImageButton>(R.id.buttonClear)
        val buttonbackClear: ImageButton = findViewById<ImageButton>(R.id.buttonbackClear)
        val buttonPer: ImageButton = findViewById<ImageButton>(R.id.buttonPer)

        val listner = View.OnClickListener {  v ->

            when (v) {
                button0 -> newNumber.append("0")
                button1 -> newNumber.append("1")
                button2 -> newNumber.append("2")
                button3 -> newNumber.append("3")
                button4 -> newNumber.append("4")
                button5 -> newNumber.append("5")
                button6 -> newNumber.append("6")
                button7 -> newNumber.append("7")
                button8 -> newNumber.append("8")
                button9 -> newNumber.append("9")
                buttonDot -> newNumber.append(".")


            }
        }
        button0.setOnClickListener(listner)
        button1.setOnClickListener(listner)
        button2.setOnClickListener(listner)
        button3.setOnClickListener(listner)
        button4.setOnClickListener(listner)
        button5.setOnClickListener(listner)
        button6.setOnClickListener(listner)
        button7.setOnClickListener(listner)
        button8.setOnClickListener(listner)
        button9.setOnClickListener(listner)
        buttonDot.setOnClickListener(listner)
//        buttonNeg.setOnClickListener(listner)

        val opListner = View.OnClickListener { v ->
            var op = ""

            when (v) {
                buttonAdd -> op = "+"
                buttonDivide -> op = "/"
                buttonEqual -> op = "="
                buttonMulti -> op = "*"
                buttonSub -> op = "-"
                buttonPer -> op = "%"
            }

            try {
                val value = newNumber.text.toString().toDouble()
                performOperation(value, op)
            } catch (e: NumberFormatException) {
                newNumber.setText("")
            }

            pendingOperation = op
            displayOperation.text = pendingOperation
        }
        buttonEqual.setOnClickListener(opListner)
        buttonMulti.setOnClickListener(opListner)
        buttonDivide.setOnClickListener(opListner)
        buttonAdd.setOnClickListener(opListner)
        buttonSub.setOnClickListener(opListner)
        buttonPer.setOnClickListener(opListner)

        buttonClear.setOnClickListener { view ->
            newNumber.setText("")
            result.setText("")
            displayOperation.text =("")
            operand1=null

        }

        buttonbackClear.setOnClickListener { view ->
            newNumber.setText("")
        }




        buttonNeg.setOnClickListener { view ->
            val value = newNumber.text.toString()
            if (value.isEmpty()) {
                newNumber.setText("-")
            } else {
                try {
                    var doubleValue = value.toDouble()
                    doubleValue *= -1
                    newNumber.setText(doubleValue.toString())

                } catch (e: NumberFormatException) {
                    newNumber.setText("")
                }
            }
        }
    }

    private fun performOperation(value: Double, operation: String) {
        if (operand1 == null) {
            operand1 = value
        } else {
//                    operand2 = value.toDouble()
            if (pendingOperation == "=") {
                pendingOperation = operation
            }
            val func = Calculation()
//            val ap:second=second()

            when (pendingOperation) {
                "=" -> operand1 = value
                "/" -> if (operand1 == 0.0 || value == 0.0 ) {
                    func.divbyzero(operand1!!)
                    operand1 = Double.NaN

                } else {
                    operand1 = func.div(operand1!!, value)
                }
                "*" -> operand1 = func.mul(operand1!!, value)                     //operand1=operand1!!*value
                "-" -> operand1 = func.sub(operand1!!, value)
                "+" -> operand1 = func.add(operand1!!, value)
                "%" -> operand1 = func.per(operand1!!, value)
            }
        }

        result.setText(operand1.toString())
        newNumber.setText("")

    }


    override fun onSaveInstanceState(outState: Bundle) {

        super.onSaveInstanceState(outState)

        if (operand1 != null) {
            outState.putDouble(STATE_OPERAND1, operand1!!)
            outState.putBoolean(STATE_OPERA1_STORE, true)
        }
        outState.putString(STATE_PENDING_OPERATION, pendingOperation)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        operand1 = if (savedInstanceState.getBoolean(STATE_OPERA1_STORE, false)) {
            savedInstanceState.getDouble(STATE_OPERAND1)
        } else {
            null
        }

        pendingOperation = savedInstanceState.getString(STATE_PENDING_OPERATION)!!
        displayOperation.text = pendingOperation
    }
}
