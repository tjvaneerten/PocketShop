package com.osu.cse5236.framework;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.osu.cse5236.pocketshop.OpenPictureActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Stack;

/**
 * Created by TJ on 11/6/2014.
 */
public class EditablePhoto implements Serializable {
    public static final long serialVersionUID = 1L;
    private String originalImageUri;
    private SerialBitmap currentImage;
    private Stack<SerialBitmap> imageHistory;

    public EditablePhoto(Uri selectedPhotoUri, Bitmap selectedPhoto) {
        originalImageUri = selectedPhotoUri.toString();
        currentImage = new SerialBitmap(selectedPhoto);
        imageHistory = new Stack<SerialBitmap>();
        imageHistory.push(currentImage);
    }

    public Bitmap getCurrentImage() {
        return currentImage.getImage();
    }

    public boolean undo() {
        if (imageHistory.size() > 1) {
            imageHistory.pop();
            currentImage = imageHistory.peek();
            return true;
        } else {
            return false;
        }
    }

    public Uri getOriginalImageUri() { return Uri.parse(originalImageUri); }

    public void saveImage() throws Exception {
        String extStorageDirectory = Environment.DIRECTORY_PICTURES + "PocketShop/";
        Log.e("storageDirectoryName", extStorageDirectory);
        FileOutputStream out = null;
        File savedImage = new File(extStorageDirectory, "asdf.PNG");
        savedImage.createNewFile();
        try {
            out = new FileOutputStream(savedImage);
            currentImage.getImage().compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void rotateImage(boolean direction) {
        Matrix matrix = new Matrix();
        matrix.postRotate(direction ? 90 : -90);
        Bitmap rotatedImage = Bitmap.createBitmap(currentImage.getImage(), 0, 0, currentImage.getImage().getWidth(), currentImage.getImage().getHeight(), matrix, true);
        imageHistory.push(new SerialBitmap(rotatedImage));
        currentImage = imageHistory.peek();
    }

    public void extractCroppedBitmap(Bundle extras) {
        currentImage.setImage((Bitmap) extras.getParcelable("data"));
        imageHistory.push(currentImage);
    }
}
