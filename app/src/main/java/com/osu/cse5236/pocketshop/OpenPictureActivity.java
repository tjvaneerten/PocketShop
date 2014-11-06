package com.osu.cse5236.pocketshop;

import android.app.Activity;
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


public class OpenPictureActivity extends Activity implements View.OnClickListener {

    private final String TAG = ((Object)this).getClass().getSimpleName();
    private static final int SELECT_PICTURE = 1;
    public static final int CROP_PICTURE = 2;
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
        FrameLayout pictureFrame = (FrameLayout)findViewById(R.id.pictureFrame);

        camera.setOnClickListener(this);
        collage.setOnClickListener(this);
        crop.setOnClickListener(this);
        rotate.setOnClickListener(this);
        save.setOnClickListener(this);
        share.setOnClickListener(this);
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
        }
    }
}
