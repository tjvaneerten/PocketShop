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
    private float screenHeight;
    private float screenWidth;

    public RandomCollage(EditablePhoto editablePhoto, float width, float height) {
        collage = Bitmap.createBitmap(canvasWidth, canvasHeight, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(collage);
        canvas.drawARGB(255, 255, 255, 255);
        screenHeight = height;
        screenWidth = width;
    }

    public Bitmap getCollage() {
        return collage;
    }

    public void addImage(EditablePhoto editablePhoto, float x, float y) {
        float marginX = (screenWidth - canvasWidth) / 2;
        float marginY = (screenHeight - canvasHeight) / 2;
        if ((x < marginX) || (x > screenWidth - marginX)
                || (y < marginY) || (y > screenHeight - marginY)) return;   // clicked outside canvas

        canvas.drawBitmap(editablePhoto.getCurrentImage(),
                x - marginX - (editablePhoto.getCurrentImage().getWidth() * 0.5f),
                y - marginY - (editablePhoto.getCurrentImage().getHeight() * 0.5f),
                null);
    }
}
