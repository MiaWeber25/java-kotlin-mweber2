package com.example.javabasics;

public class Utils {
    // Static keyword: effectivily a global variable. Exists even if I have no Caps at all. I should be able to call this without having to create a Cap
    public static int codepoints(String string) { // This is a pure function -> no dependencies on the characteristics of a Cap (which is why it makes sense for it to be static)
        // m = new Math();
        // m.sin(m.PI/2);
        //Math.sin(Math.PI/2);
        //Cap cap = new Cap();
        //Cap.codepoints("example text");
        return string.codePointCount(0,string.length()); // Length gives you the number of chars in string (different than UNICODE codepoints)
    }
}
