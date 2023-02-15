package com.example.capco;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class UtilsTest {

    @Test
    public void codepoints() {
        assertEquals(0,Utils.codepoints(""));
        assertEquals(5, Utils.codepoints("Hello"));
        assertEquals(8,Utils.codepoints("Hello 👍!"));
    }
}