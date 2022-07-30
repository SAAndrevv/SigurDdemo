package com.example.sigurddemo.additional;

import java.util.Random;

public class RandomCardGeneration {

    private static final Random rnd = new Random();

    public static byte[] randomGeneration(int byteCount) {
        byte[] b = new byte[byteCount];
        rnd.nextBytes(b);
        return b;
    }
}
