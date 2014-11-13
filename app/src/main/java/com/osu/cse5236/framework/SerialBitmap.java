package com.osu.cse5236.framework;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by TJ on 11/13/2014.
 */
public class SerialBitmap implements Serializable {

    private Bitmap image;

    public SerialBitmap(Bitmap bitmap) {
        image = bitmap;
    }

    public Bitmap getImage() { return image; }

    public void setImage(Bitmap bitmap) { image = bitmap; }

    private void writeObject(ObjectOutputStream out) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 0, byteStream);
        byte bitmapBytes[] = byteStream.toByteArray();
        out.write(bitmapBytes, 0, bitmapBytes.length);
    }

    private void readObject(ObjectInputStream in) throws IOException, NoClassDefFoundError {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int b;
        while((b = in.read()) != -1)
            byteStream.write(b);
        byte bitmapBytes[] = byteStream.toByteArray();
        image = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
    }
}
