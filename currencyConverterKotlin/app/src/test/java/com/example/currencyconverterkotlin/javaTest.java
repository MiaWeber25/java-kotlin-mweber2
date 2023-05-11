package com.example.currencyconverter;

import static com.example.currencyconverter.UtilKt.calculate;

import org.junit.Test;
import static org.junit.Assert.*;

public class javaTest {

    @Test
    public void testConvertUSDToJPY() {
        double amount = 100.00;
        double exchangeRate = 0.907;
        double result = calculate(amount, exchangeRate);

        assertEquals(13616.50, result, 0.02);
    }

    @Test
    public void testConvertJPYToUSD() {
        double amount = 100.00;
        double exchangeRate = 0.007;
        double result = calculate(amount, exchangeRate);

        assertEquals(0.70, result, 0.02);
    }

    @Test
    public void testConvertEURToJPY() {
        double amount = 100.00;
        double exchangeRate = 150.092;
        double result = calculate(amount, exchangeRate);

        assertEquals(15009.20, result, 0.02);
    }

    @Test
    public void testConvertJPYToEUR() {
        double amount = 100.00;
        double exchangeRate = 0.006;
        double result = calculate(amount, exchangeRate);

        assertEquals(0.60, result, 0.02);
    }
}