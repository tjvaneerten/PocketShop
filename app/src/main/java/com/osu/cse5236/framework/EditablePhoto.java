package com.osu.cse5236.framework;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import com.osu.cse5236.pocketshop.OpenPictureActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Stack;

/**
 * Created by TJ on 11/6/2014.
 */
public class EditablePhoto implements Serializable {
    private Activity mainActivity;
    private Uri originalImageUri;
    private Bitmap currentImage;
    private Stack<Bitmap> imageHistory;

    public EditablePhoto(Uri selectedPhotoUri, Bitmap selectedPhoto, Activity activity) {
        mainActivity = activity;
        originalImageUri = selectedPhotoUri;
        currentImage = selectedPhoto;
        imageHistory = new Stack<Bitmap>();
        imageHistory.push(currentImage);
    }

    public Bitmap getCurrentImage() {
        return currentImage;
    }

    public boolean undo() {
        if (imageHistory.size() <= 1) {
            imageHistory.pop();
            currentImage = imageHistory.peek();
            return true;
        } else {
            return false;
        }
    }

    public Uri getOriginalImageUri() { return originalImageUri; }

    public void startCropIntent() {
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        cropIntent.setDataAndType(originalImageUri, "image/*");
        cropIntent.putExtra("aspectX", 1);
        cropIntent.putExtra("aspectY", 1);
        cropIntent.putExtra("outputX", 256);
        cropIntent.putExtra("outputY", 256);
        cropIntent.putExtra("return-data", true);
        mainActivity.startActivityForResult(cropIntent, ((OpenPictureActivity)mainActivity).CROP_PICTURE);
    }

    public void saveImage() throws Exception {
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        FileOutputStream out = null;
        File savedImage = new File(extStorageDirectory, "asdf.PNG");
        try {
            out = new FileOutputStream(savedImage);
            currentImage.compress(Bitmap.CompressFormat.PNG, 100, out);
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

    public void rotateImage() {
        Matrix matrix = new Matrix();
        matrix.postRotate(90);
        Bitmap rotatedImage = Bitmap.createBitmap(currentImage, 0, 0, currentImage .getWidth(), currentImage .getHeight(), matrix, true);
        currentImage = rotatedImage;
        imageHistory.push(currentImage);
    }

    public void extractCroppedBitmap(Bundle extras) {
        currentImage = extras.getParcelable("data");
        imageHistory.push(currentImage);
    }
}
