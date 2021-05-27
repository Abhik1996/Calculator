package com.example.calculator

class Calculation {

    private var operand : Double =0.0
    private lateinit var operat : String

    fun mul(operand1: Double, value:Double): Double {
          operand = operand1*value
          return operand
        }
    fun div(operand1: Double, value:Double): Double {
        operand = operand1/value
        return operand
    }
    fun divbyzero(operand1: Double): String {
        if (operand1==0.0){
            operat="can't divide by zero"
        }
        else{
            operat=operand1.toString()
        }
        return operat.toString()
    }

    fun sub(operand1: Double, value:Double): Double {
        operand = operand1-value
        return operand
    }
    fun add(operand1: Double, value:Double): Double {
        operand = operand1+value
        return operand
    }
    fun per(operand1: Double,value: Double):Double {
        operand = (operand1*value)/100
        return operand
    }


//    public fun Add( operand1: Double ,  value:String): Double {
//
//            return  operand1
//
//        }
}


