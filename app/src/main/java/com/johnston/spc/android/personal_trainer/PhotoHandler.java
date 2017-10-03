package com.johnston.spc.android.personal_trainer;

import android.content.Context;
import android.hardware.Camera;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 3johnr181 on 10/2/2017.
 */

public class PhotoHandler implements Camera.PictureCallback{
    private final Context ctx;
    public PhotoHandler(Context c)
    {
        ctx = c;
    }

    @Override
    public void onPictureTaken(byte[] data, Camera camera) {
        File fileDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (fileDir.exists() && !fileDir.mkdir()) {
            Toast.makeText(ctx, "Can't make Dir", Toast.LENGTH_LONG).show();
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddMMss");
        String date = dateFormat.format(new Date());
        String photoFile = "Picture_" + date + ".jpg";

        String fileName = fileDir.getPath() + File.separator + photoFile;

        File pf = new File(fileName);

        try {
            FileOutputStream fos = new FileOutputStream(pf);
            fos.write(data);
            fos.close();

            Toast.makeText(ctx, "Picture Saved", Toast.LENGTH_LONG).show();
        } catch (Exception e) {

        }
    }
}
