package com.example.codecracker;

import static com.example.codecracker.CalculateMD5.getMD5;

import java.util.ArrayList;

public class CrackThread extends Thread {
    private ArrayList<String> resultArray;
    private Integer startNum;
    private Integer endNum;
    private String hash;

    public CrackThread(ArrayList<String> resultArray, Integer start, Integer end, String hash) {
        this.resultArray = resultArray;
        this.startNum = start;
        this.endNum = end;
        this.hash = hash;
    }

    public void run() {
        String attempt;

        for (Integer i=startNum; i<= endNum; i++) {
            attempt = String.format("%03d", i);

            String attemptHash = getMD5(attempt);

            if (attemptHash.compareTo(hash) == 0) {
                resultArray.add(attempt);
                return;
            }
        }
        return;
    }

}
