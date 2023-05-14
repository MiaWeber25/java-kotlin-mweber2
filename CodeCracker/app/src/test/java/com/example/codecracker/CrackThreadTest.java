package com.example.codecracker;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class CrackThreadTest {

    @Test
    public void matchingHash1() {
        ArrayList<String> resultArray = new ArrayList<>();
        Integer startNum = 0;
        Integer endNum = 999;
        String hash = "202cb962ac59075b964b07152d234b70";

        CrackThread crackThread = new CrackThread(resultArray, startNum, endNum, hash);
        crackThread.run();

        Assert.assertFalse(resultArray.isEmpty());
        Assert.assertEquals("123", resultArray.get(0));
    }

    @Test
    public void matchingHash2() {
        ArrayList<String> resultArray = new ArrayList<>();
        Integer startNum = 0;
        Integer endNum = 999;
        String hash = "e46d0ac1f15ac7dac2a8948245a02da4";

        CrackThread crackThread = new CrackThread(resultArray, startNum, endNum, hash);
        crackThread.run();

        Assert.assertFalse(resultArray.isEmpty());
        Assert.assertEquals("045", resultArray.get(0));
    }

    @Test
    public void noMatchingHash() {
        ArrayList<String> resultArray = new ArrayList<>();
        Integer startNum = 0;
        Integer endNum = 999;
        String hash = "5f4dcc3b5aa765d61d8327deb882cf99";

        // Modify the hash to a different value
        hash = "7c4a8d09ca3762af61e59520943dc26494f8941b";

        CrackThread crackThread = new CrackThread(resultArray, startNum, endNum, hash);
        crackThread.run();

        Assert.assertTrue(resultArray.isEmpty());
    }
}