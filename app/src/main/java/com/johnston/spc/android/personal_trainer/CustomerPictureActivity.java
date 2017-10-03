package com.johnston.spc.android.personal_trainer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import java.io.IOException;
import java.io.InputStream;

import layout.UserLoggedInFragment;

public class CustomerPictureActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, UserLoggedInFragment.OnFragmentInteractionListener {

    private Bitmap bitMap;
    private Camera camera;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_picture);
        checkCameraHardware();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        InputStream is = null;
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            try {
                if (bitMap != null) bitMap.recycle();

                is = getContentResolver().openInputStream(data.getData());
                bitMap = BitmapFactory.decodeStream(is);

                iv.setImageBitmap(bitMap);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (is != null){
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    public void onClick(View View) {
        camera.startPreview();
        camera.takePicture(null, null, new PhotoHandler(getApplicationContext()));
    }

    private boolean checkCameraHardware() {
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            int ID = findFrontCamera();
            if (ID < 0) {
                Toast.makeText(this, "No front facing camera found.",
                        Toast.LENGTH_LONG).show();
            } else {
                camera = Camera.open(ID);
            }
            return true;
        } else {
            //Toast.makeText()//
            return false;
        }
    }
    private int findFrontCamera() {
        int camera = -1;
        // Search for the front facing camera
        int Cameras = Camera.getNumberOfCameras();
        for (int i = 0; i < Cameras; i++) {
            CameraInfo info = new CameraInfo();
            Camera.getCameraInfo(i, info);
            if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
                camera = i;
                break;
            }
        }
        return camera;
    }
}
