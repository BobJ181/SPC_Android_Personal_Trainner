package com.johnston.spc.android.personal_trainer;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import layout.UserLoggedInFragment;

public class CustomerPictureActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, UserLoggedInFragment.OnFragmentInteractionListener {

    private Button photoButton;
    private ImageView photoView;
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_picture);
        photoButton = (Button) findViewById(R.id.btn_take);
        photoView = (ImageView) findViewById(R.id.img_take);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
        }
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
        if (resultCode == RESULT_OK && requestCode == 0) {
            try {
                Bundle extras = data.getExtras();

                Bitmap bmp = (Bitmap) extras.get("data");

                photoView.setImageBitmap(bmp);

                try {
                    FileOutputStream fos = new FileOutputStream(getFileForPic());

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] byteArray = stream.toByteArray();

                    fos.write(byteArray);
                    fos.close();

                    Toast.makeText(this, "Picture Saved - " + getFileForPic(), Toast.LENGTH_LONG).show();
                } catch (Exception e) {

                }

            } catch (Exception e) {

            }
        }
    }

    public void takePic(View v)
    {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        i.putExtra(MediaStore.EXTRA_OUTPUT, getFileForPic());
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        startActivityForResult(i, 0);
    }

    private File getFileForPic() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 100);
        File fileDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddMMss");
        String date = dateFormat.format(new Date());
        String photoFile = "Picture_" + date + ".jpg";

        String fileName = fileDir.getPath() + File.separator + photoFile;

        File pf = new File(fileName);
        return pf;
    }
    public static Boolean getPref(Context context, String key) {
        SharedPreferences myPrefs = context.getSharedPreferences("camera_pref",
                Context.MODE_PRIVATE);
        return (myPrefs.getBoolean(key, false));
    }
}

