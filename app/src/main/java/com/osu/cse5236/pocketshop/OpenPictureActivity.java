package com.osu.cse5236.pocketshop;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;
import com.osu.cse5236.framework.EditablePhoto;

import java.io.IOException;
import java.io.Serializable;

public class OpenPictureActivity extends FragmentActivity
        implements View.OnClickListener, OpenExistingPicture.OnOpenExistingPictureListener,
        PictureFrame.OnEditablePictureInteractionListener, Serializable, FacebookLogin.OnFragmentInteractionListener{

    private final String TAG = ((Object)this).getClass().getSimpleName();
    private static final int SELECT_PICTURE = 1;
    private static final int TAKE_PICTURE = 2;
    public static final int CROP_PICTURE = 3;
    private static final String SAVED_STATE = "saved state";
    private String selectedImagePath;
    private EditablePhoto editablePhoto;

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

        camera.setOnClickListener(this);
        collage.setOnClickListener(this);
        crop.setOnClickListener(this);
        rotate.setOnClickListener(this);
        save.setOnClickListener(this);
        share.setOnClickListener(this);
        gallery.setOnClickListener(this);

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            OpenExistingPicture pictureFrame = new OpenExistingPicture();
            fragmentTransaction.add(R.id.fragmentPlaceholder, pictureFrame).commit();
        }
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
                if (editablePhoto != null) {
                    editablePhoto.startCropIntent();
                }
                break;
            case R.id.rotate:
                if (editablePhoto != null) {
                    editablePhoto.rotateImage();
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentPlaceholder, PictureFrame.newInstance(editablePhoto)).commit();
                }
                break;
            case R.id.save:
                if (editablePhoto != null) {
                    editablePhoto.saveImage();
                }
                break;
            case R.id.share:
                if (editablePhoto != null) {
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentPlaceholder, new FacebookLogin()).commit();
                }
                break;
            case R.id.gallery:
                onOpenExistingPictureSelected();
                break;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle bundle) {
        Log.e("asd", "in onsaveinstancestat");
        super.onSaveInstanceState(bundle);
        //bundle.putSerializable(SAVED_STATE, );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == TAKE_PICTURE) {
                // user has taken a new picture
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth() * 0.5), (int)(bitmap.getHeight() * 0.5), false);
                    editablePhoto = new EditablePhoto(data.getData(), scaledBitmap, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_PICTURE) {
                // user has selected an existing photo
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), data.getData());
                    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth() * 0.5), (int)(bitmap.getHeight() * 0.5), false);
                    editablePhoto = new EditablePhoto(data.getData(), scaledBitmap, this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (requestCode == CROP_PICTURE) {
                // user has cropped the editable picture
                editablePhoto.extractCroppedBitmap(data.getExtras());
            }
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentPlaceholder, PictureFrame.newInstance(editablePhoto));
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onOpenExistingPictureSelected() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,
                "Select Picture"), SELECT_PICTURE);
    }

    @Override
    public void onEditablePictureInteraction(Uri uri) {
        // TODO
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
