package com.osu.cse5236.pocketshop;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class OpenPictureActivity extends Activity {

    private final String TAG = ((Object)this).getClass().getSimpleName();
    private static final int SELECT_PICTURE = 1;
    private String selectedImagePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_picture);
        initializeGetPictureFromGallery();

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

    private void initializeGetPictureFromGallery() {
        Button fromGallery = (Button) findViewById(R.id.center);
        fromGallery.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // in onCreate or any event where your want the user to
                // select a file
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,
                        "Select Picture"), SELECT_PICTURE);
            }
        });
    }
}
