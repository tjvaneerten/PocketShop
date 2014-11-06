package com.osu.cse5236.framework;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.osu.cse5236.pocketshop.OpenPictureActivity;

/**
 * Created by TJ on 11/6/2014.
 */
public class EditablePhoto {
    private Activity mainActivity;
    private Uri originalImage;

    public EditablePhoto(Uri selectedPhoto, Activity activity) {
        mainActivity = activity;
        originalImage = selectedPhoto;
    }

    public void startCropIntent() {
        Intent cropIntent = new Intent("com.android.camera.action.CROP");
        cropIntent.setDataAndType(originalImage, "image/*");
        cropIntent.putExtra("crop", "true");
        cropIntent.putExtra("aspectX", 1);
        cropIntent.putExtra("aspectY", 1);
        cropIntent.putExtra("outputX", 256);
        cropIntent.putExtra("outputY", 256);
        cropIntent.putExtra("return-data", true);
        mainActivity.startActivityForResult(cropIntent, ((OpenPictureActivity)mainActivity).CROP_PICTURE);
    }
}
