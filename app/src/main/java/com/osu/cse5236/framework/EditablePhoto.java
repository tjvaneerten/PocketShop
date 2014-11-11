package com.osu.cse5236.framework;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.osu.cse5236.pocketshop.OpenPictureActivity;

import java.io.Serializable;

/**
 * Created by TJ on 11/6/2014.
 */
public class EditablePhoto implements Serializable {
    private Activity mainActivity;
    private Uri originalImageUri;
    private Bitmap originalImage;
    private Bitmap croppedImage;

    public EditablePhoto(Uri selectedPhotoUri, Bitmap selectedPhoto, Activity activity) {
        mainActivity = activity;
        originalImageUri = selectedPhotoUri;
        originalImage = selectedPhoto;
    }

    public Bitmap getOriginalImage() {
        return originalImage;
    }

    public Uri getOriginalImageUri() { return originalImageUri; }

    public Bitmap getCroppedImage() {
        return croppedImage;
    }

    public void startCropIntent() {
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        cropIntent.setDataAndType(originalImageUri, "image/*");
        cropIntent.putExtra("crop", "true");
        cropIntent.putExtra("aspectX", 1);
        cropIntent.putExtra("aspectY", 1);
        cropIntent.putExtra("outputX", 256);
        cropIntent.putExtra("outputY", 256);
        cropIntent.putExtra("return-data", true);
        mainActivity.startActivityForResult(cropIntent, ((OpenPictureActivity)mainActivity).CROP_PICTURE);
    }

    public void extractCroppedBitmap(Bundle extras) {
        croppedImage = extras.getParcelable("data");
    }
}
