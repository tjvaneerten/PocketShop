package com.osu.cse5236.pocketshop;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.osu.cse5236.framework.EditablePhoto;


public class OpenPictureActivity extends Activity implements View.OnClickListener {

    private final String TAG = ((Object)this).getClass().getSimpleName();
    private static final int SELECT_PICTURE = 1;
    private static final int TAKE_PICTURE = 2;
    public static final int CROP_PICTURE = 3;
    private String selectedImagePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_picture);
        ImageView camera = (ImageView)findViewById(R.id.camera);
        ImageView collage = (ImageView)findViewById(R.id.collage);
        ImageView crop = (ImageView)findViewById(R.id.crop);
        ImageView rotate = (ImageView)findViewById(R.id.rotate);
        ImageView save = (ImageView)findViewById(R.id.save);
        ImageView share = (ImageView)findViewById(R.id.share);
        ImageView gallery = (ImageView)findViewById(R.id.gallery);
        FrameLayout pictureFrame = (FrameLayout)findViewById(R.id.pictureFrame);

        camera.setOnClickListener(this);
        collage.setOnClickListener(this);
        crop.setOnClickListener(this);
        rotate.setOnClickListener(this);
        save.setOnClickListener(this);
        share.setOnClickListener(this);
        gallery.setOnClickListener(this);
        pictureFrame.setOnClickListener(this);
        Log.e(TAG, "++ In onCreate() ++");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "++ In onStart() ++");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "++ In onResume() ++");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "++ In onPause() ++");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "++ In onStop() ++");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "++ In onDestroy() ++");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.open_picture, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void takePicture() {
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.camera:
                try {
                    //use standard intent to capture an image
                    Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //we will handle the returned data in onActivityResult
                    startActivityForResult(captureIntent, TAKE_PICTURE);
                } catch(ActivityNotFoundException ex){
                    //display an error message
                    String errorMessage = "Error: This device does not support capturing images";
                    Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.collage:
                break;
            case R.id.crop:
                break;
            case R.id.rotate:
                break;
            case R.id.save:
                break;
            case R.id.share:
                break;
            case R.id.pictureFrame:
                break;
            case R.id.gallery:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == TAKE_PICTURE) {
                // user has taken a new picture
                EditablePhoto editablePhoto = new EditablePhoto(data.getData(), this);
            } else if (requestCode == SELECT_PICTURE) {
                // user has selected an existing picture
                EditablePhoto editablePhoto = new EditablePhoto(data.getData(), this);
            } else if (requestCode == CROP_PICTURE) {
                // user has cropped the editable picture

            }
        }
    }
}
