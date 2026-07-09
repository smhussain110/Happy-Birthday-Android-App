package com.example.happybirthday

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class TipCalculatorUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun calculateTipAmount_20Percent_WholeNumber() {
        val totalAmount = 100.0
        val tipPercent = 20.0

        assertEquals(
            20.0,
            calculateTipAmount(
                totalAmount = totalAmount,
                tipPercentage = tipPercent
            ),
            0.000
        )
    }
}