package com.opencv.android.opencvandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    CameraBridgeViewBase cameraBridgeViewBase;
    // Initialize Matrices
    Mat mat1, mat2, mat3;
    BaseLoaderCallback baseLoader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Initialize Camera View
        cameraBridgeViewBase = (JavaCameraView)findViewById(R.id.cameraView);
        // Set Camera Visibility
        cameraBridgeViewBase.setVisibility(SurfaceView.VISIBLE);
        // Set Camera View listener
        cameraBridgeViewBase.setCvCameraViewListener(this);

        baseLoader = new BaseLoaderCallback(this) {
            @Override
            public void onManagerConnected(int status) {
                super.onManagerConnected(status);
                switch (status)
                {
                    case BaseLoaderCallback.SUCCESS:
                        cameraBridgeViewBase.enableView();
                        break;
                    default:
                        super.onManagerConnected(status);
                        break;
                }
            }
        };

        if(OpenCVLoader.initDebug()){
            Toast.makeText(getApplicationContext(), "OpenCV Loaded Successfully !!", Toast.LENGTH_LONG).show();
            Log.i("Info","OpenCV Loaded Successfully !!");
        }
        else{
            Toast.makeText(getApplicationContext(), "OpenCV Loading UnSuccessful !!", Toast.LENGTH_LONG).show();
            Log.i("Info","OpenCV Loading UnSuccessful !!");
        }
    }


       // On Camera View Started
    @Override
    public void onCameraViewStarted(int width, int height) {
        // Get a Matrix of Width and Height: Initialize Matrices
        mat1 = new Mat(width, height, CvType.CV_8UC4);
        //Log.i("Initialized Mat-1", mat1.toString());
        mat2 = new Mat(width, height, CvType.CV_8UC4);
        //Log.i("Initialized Mat-2", mat2.toString());
        mat3 = new Mat(width, height, CvType.CV_8UC4);
        //Log.i("Initialized Mat-3", mat3.toString());
    }


    // On Camera Frame
    // Camera frames come in as "inputFrame" object
    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        // Get RGBA Input Frame
        mat1 = inputFrame.rgba();
        Log.i("Mat-1 Input Frame", mat1.toString());
        return mat1;
    }


    // On Camera View Stopped, clear all matrices
    @Override
    public void onCameraViewStopped() {
        mat1.release();
        Log.i("Released Mat-1", mat1.toString());
    }

    // What to do when the app is in Background?
    // If app goes in background, disable camera
    @Override
    protected void onPause() {
        super.onPause();
        if (cameraBridgeViewBase != null){
            cameraBridgeViewBase.disableView();
        }
    }


    // When app comes from background to foreground, resume operation
    @Override
    protected void onResume() {
        super.onResume();
        if(!OpenCVLoader.initDebug()){
            Toast.makeText(getApplicationContext(), "Error Loading OpenCV !!", Toast.LENGTH_LONG).show();
            Log.i("Info","Error Loading OpenCV !!");
        }
        else {
            baseLoader.onManagerConnected(BaseLoaderCallback.SUCCESS);
        }
    }


    // If app is closed, disable camera view
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cameraBridgeViewBase != null){
            cameraBridgeViewBase.disableView();
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
