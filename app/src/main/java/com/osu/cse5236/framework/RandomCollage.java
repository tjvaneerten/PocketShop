package com.osu.cse5236.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.io.Serializable;

/**
 * Created by Nathan on 11/13/2014.
 */
public class RandomCollage implements Serializable {

    private Canvas canvas;
    private Bitmap collage;
    private static final int canvasWidth = 1000;
    private static final int canvasHeight = 1500;

    public RandomCollage(EditablePhoto editablePhoto) {
        collage = Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(collage);
        canvas.drawARGB(255, 255, 255, 255);
    }

    public Bitmap getCollage() {
        return collage;
    }

    public void addImage(EditablePhoto editablePhoto, float x, float y) {
        canvas.drawBitmap(editablePhoto.getCurrentImage(),
                (x * canvasWidth),
                (y * canvasHeight),
                null);
    }
}
