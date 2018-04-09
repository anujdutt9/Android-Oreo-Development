package com.rgb2gray.android.rgb2grayscale;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorSpace;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    // Grayscale Image View
    ImageView imageView;
    // Image Browse and Selection
    Uri imageUri;
    Bitmap grayBitmap, imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Define Grayscale Image View
        imageView = (ImageView)findViewById(R.id.imageView);
        // Initialize and Load OpenCV
        OpenCVLoader.initDebug();
    }

    // Function to Browse for Images on Button Click from External Memory
    public void clickFunction(View view){
        Intent myIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(myIntent, 100);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // If Image is selected and is valid image, then load the image
        if (requestCode == 100 && resultCode == RESULT_OK && data != null){
            imageUri = data.getData();

            try {
                // Convert URI to Image Bitmap
                imageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            }
            catch (Exception e){
                Log.i("ERROR", "Bitmap Error!!");
            }
            imageView.setImageBitmap(imageBitmap);
        }
    }

    // Function to convert RGB Image to Grayscale Image on Button Press
    public void convertToGrayscale(View view){
        Mat Rgba = new Mat();
        Mat grayMat = new Mat();
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inDither = false;
        o.inSampleSize=4;
        // Set Width and Height of Bitmap
        int width = imageBitmap.getWidth();
        int height = imageBitmap.getHeight();
        // Create Bitmap for Image
        grayBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        // Bitmap to MAT Conversion
        Utils.bitmapToMat(imageBitmap, Rgba);
        // Convert RGB Image to Grayscale Image
        Imgproc.cvtColor(Rgba, grayMat, Imgproc.COLOR_RGB2GRAY);
        // Convert Grayscale Image from Mat to Bitmap
        Utils.matToBitmap(grayMat, grayBitmap);
        // Show Grayscale Image in ImageView
        imageView.setImageBitmap(grayBitmap);
    }


    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
