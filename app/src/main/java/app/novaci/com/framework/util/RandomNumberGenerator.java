package app.novaci.com.framework.util;

import java.util.Random;

/**
 * Created by Sasha on 5/2/2016.
 */
public class RandomNumberGenerator {
    private static Random rand = new Random();

    public static int getRandIntBetween(int lowerBound, int upperBound) {
        return rand.nextInt(upperBound - lowerBound) + lowerBound;
    }

    public static int getRandInt(int upperBound) {
        return rand.nextInt(upperBound);
    }
}
