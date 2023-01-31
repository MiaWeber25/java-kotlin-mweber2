package com.example.javabasics;

public class Hat {
    boolean isOld;
    byte age; // byte is signed -128..127
    short threatCount; // -32768..32767 (16 bits)
    int priceInCents; // -2 billion..2 billion
    long taxId; // -2^63..(2^63)-1
    float size; // base 2 representation (about 7 decimal digits of mantessa) * 10^(p/m 30)
    double distToCenterOfUniverseInAngstroms; // like float but 15 digits pm 300

    char x; // 0..65535 -> only unsigned data type (16 bits long = UTF16). Have to think of codepoints (real length of a string is the number of codepoints

    String name; // Object composed of the primitive types above
}
