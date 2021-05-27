package com.example.calculator

import android.graphics.Insets.add
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
//class ExampleUnitTest {
//    @Test
//    fun addition_isCorrect() {
//        assertEquals(4, 2 + 2)
//    }
//}

class CalcutTest{

    @Test
    fun testdivbyzero(){
        val Second =Calculation()
        val divisionValue = 3.0
        val expectedValue:String = "can't divide by zero"
        assertEquals("zero division fail",expectedValue,Second.divbyzero(divisionValue))

    }


    @Test

    fun testmul(){
        val Second =Calculation()
        val firstValue=3.0
        val secondValue=2.0
        val expectedValue = 6.0
        assertEquals("mul failed",expectedValue,Second.mul(firstValue,secondValue), 0.0)
    }

    @Test
    fun testadd(){
        val Second =Calculation()
        val firstValue=3.0
        val secondValue=2.0
        val expectedValue = 5.0
        assertEquals("ADd failed",expectedValue,Second.add(firstValue,secondValue), 0.0)
    }

    @Test
    fun testsub(){
        val Second =Calculation()
        val firstValue=3.0
        val secondValue=2.0
        val expectedValue = 1.0
        assertEquals("sub failed",expectedValue,Second.sub(firstValue,secondValue), 0.0)
    }
    @Test
    fun testdivion(){
        val Second =Calculation()
        val firstValue=6.0
        val secondValue=2.0
        val expectedValue = 3.0
        assertEquals("mul failed",expectedValue,Second.div(firstValue,secondValue), 0.0)
    }
    @Test

    fun testPercent(){
        val Second =Calculation()
        val firstValue=50.0
        val secondValue=30.0
        val expectedValue = 15.0
        assertEquals("mul failed",expectedValue,Second.per(firstValue,secondValue), 0.0)
    }



}