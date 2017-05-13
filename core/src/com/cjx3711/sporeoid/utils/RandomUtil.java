package com.cjx3711.sporeoid.utils;

import java.util.Random;

/**
 * Random stuff
 */

public class RandomUtil {
    private static Random rand = new Random();
    public static int randomInt(int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }
    public static float randomFloat(float min, float max) {
        return rand.nextFloat() * (max - min) + min;
    }
}
