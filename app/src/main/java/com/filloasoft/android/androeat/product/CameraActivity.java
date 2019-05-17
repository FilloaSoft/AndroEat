package com.filloasoft.android.androeat.product;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.filloasoft.android.androeat.MainActivity;
import com.filloasoft.android.androeat.R;
import com.filloasoft.android.androeat.model.ProductListView;
import com.filloasoft.android.androeat.model.Recipe;
import com.filloasoft.android.androeat.recipe.RecipeFragment;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CameraActivity extends AppCompatActivity {

    static final int REQUEST_TAKE_PHOTO = 1;
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
    private static final int MY_PERMISSIONS_EXTERNAL_STORAGE = 2;
    String currentPhotoPath;
    File photoFile = null;
    private OnBasketAdapterListener callback;


    Toast toast;

    public interface OnBasketAdapterListener{
        ShoppingBasketListAdapter onGetAdapter();
    }

    public void setOnCameraActivityListener(OnBasketAdapterListener callback) {
        this.callback = callback;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        toast = Toast.makeText(getApplicationContext(),
//                getBaseContext().toString(), Toast.LENGTH_LONG);
//        toast.show();


        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            toast = Toast.makeText(getApplicationContext(),
                    "Camera not available, please use simple search.", Toast.LENGTH_SHORT);
            toast.show();
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    MY_PERMISSIONS_REQUEST_CAMERA);
        } else {
            dispatchTakePictureIntent();
            }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CAMERA: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatchTakePictureIntent();
                } else {
                    this.finish();
                    toast = Toast.makeText(getApplicationContext(),
                            "Camera not allowed, please use simple search.", Toast.LENGTH_SHORT);
                    toast.show();
                }
                return;
            }
            case MY_PERMISSIONS_EXTERNAL_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatchTakePictureIntent();
                } else {
                    this.finish();
                    toast = Toast.makeText(getApplicationContext(),
                            "Storage not allowed, please use simple search.", Toast.LENGTH_SHORT);
                    toast.show();
                }
                return;
            }
        }
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
//        We must decide where to save our images
//        File storageDir = getExternalFilesDir(Environment.getExternalStoragePublicDirectory());
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "AndroEat" );
        storageDir.mkdir();
        storageDir.setReadable(true);

        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private void dispatchTakePictureIntent() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)    {

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MY_PERMISSIONS_EXTERNAL_STORAGE);
        }
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                /*toast = Toast.makeText(getApplicationContext(),
                        ex.toString(), Toast.LENGTH_SHORT);
                toast.show();*/
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.filloasoft.android.fileprovider",
                        photoFile);
                /*toast = Toast.makeText(getApplicationContext(),
                        photoURI.toString(), Toast.LENGTH_SHORT);
                toast.show();*/
//                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
//                takePictureIntent.putExtra("crop", "true");
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {

            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            try {
                FileOutputStream fos = new FileOutputStream(photoFile);
                fos.flush();
                fos.write(stream.toByteArray());
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            Intent returnIntent = new Intent();
            returnIntent.putExtra("mPhotoPath", currentPhotoPath);
            setResult(Activity.RESULT_OK, returnIntent);

            finish();
            galleryAddPic();
        } else {
            this.finish();
        }
    this.finish();
    }

    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
        this.finish();
    }
}
