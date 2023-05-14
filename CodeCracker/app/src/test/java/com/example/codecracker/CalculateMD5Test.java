package com.example.codecracker;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class CalculateMD5Test {

    @Test
    public void testGetMD51() {
        String code = "100";
        String expectedHash = "f899139df5e1059396431415e770c6dd";
        String actualHash = CalculateMD5.getMD5(code);
        Assert.assertEquals(expectedHash, actualHash);

    }

    @Test
    public void testGetMD52() {
        String code = "3458";
        String expectedHash = "e3ea33961a7c5b1ec04d6c97aa3b5379";
        String actualHash = CalculateMD5.getMD5(code);
        Assert.assertEquals(expectedHash, actualHash);
    }

    @Test
    public void testGetMD53() {
        String code = "99487";
        String expectedHash = "22a1206737daef1e33b1170d25c540c9";
        String actualHash = CalculateMD5.getMD5(code);
        Assert.assertEquals(expectedHash, actualHash);
    }

    @Test
    public void testGetMD54() {
        String code = "99999";
        String expectedHash = "d3eb9a9233e52948740d7eb8c3062d14";
        String actualHash = CalculateMD5.getMD5(code);
        Assert.assertEquals(expectedHash, actualHash);
    }

    @Test
    public void testGetMD55() {
        String code = "000";
        String expectedHash = "c6f057b86584942e415435ffb1fa93d4";
        String actualHash = CalculateMD5.getMD5(code);
        Assert.assertEquals(expectedHash, actualHash);
    }
}