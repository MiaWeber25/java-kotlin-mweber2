package com.example.currencyconverter

//import org.junit.Assert.*
//import com.example.currencyconverter.Util.calculate
import org.junit.Assert.assertEquals

import org.junit.Test


class UtilTest {
    //var activity = MainActivity()
    @Test
    fun testConvertUSDToJPY() {
        val amount = 100.00
        val exchangeRate = 0.907
        val result: Double = calculate(amount, exchangeRate)

        assertEquals(13616.50, result, 0.02)
    }

    @Test
    fun testConvertJPYToUSD() {
        val amount = 100.00
        val exchangeRate = 0.007
        val result: Double = calculate(amount, exchangeRate)
        assertEquals(0.70, result, 0.02)
    }

    @Test
    fun testConvertEURToJPY() {
        val amount = 100.00
        val exchangeRate = 150.092
        val result: Double = calculate(amount, exchangeRate)
        assertEquals(15009.20, result, 0.02)
    }

    @Test
    fun testConvertJPYToEUR() {
        val amount = 100.00
        val exchangeRate = 0.006
        val result: Double = calculate(amount, exchangeRate)
        assertEquals(0.60, result, 0.02)
    }
}