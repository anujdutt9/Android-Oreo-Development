package com.video.android.video;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    // Function with Code to Play Video
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create Video View
        VideoView videoView = (VideoView)findViewById(R.id.videoView);

        // Look inside android resources area
        videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.video);
        Log.i("Video File Path","android.resource://" + getPackageName() + "/" + R.raw.video);

        // Media Controller to Play/Pause Video
        MediaController mediaController = new MediaController(this);

        // Set Anchor Point for Media Controller
        mediaController.setAnchorView(videoView);

        // Set Media Controller for Video
        videoView.setMediaController(mediaController);

        // Set Video Screen Orientation to Landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        // Start Playing Video
        videoView.start();
    }
}
