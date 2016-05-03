package app.novaci.com.framework.util;

import android.graphics.Bitmap;

/**
 * Created by Sasha on 5/2/2016.
 */
public class Frame {
    private Bitmap image;
    private double duration;

    public Frame(Bitmap image, double duration) {
        this.image = image;
        this.duration = duration;
    }

    public double getDuration() {
        return duration;
    }

    public Bitmap getImage() {
        return image;
    }
}
