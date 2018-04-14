// Music Player App with "Play", "Pause" and "Loop" Functionality.
// Multithreading for "Faster Response" for "Play/Pause".

// To-Do: Song File Browsing and Selection from Internal Memory Support to be added.

package com.audio.android.audio;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    // Set up Media Player
    private MediaPlayer mediaPlayer;
    // Song File data retriever
    private MediaMetadataRetriever mediaMetadataRetriever;
    // Audio Manager to Control Volume
    AudioManager audioManager;
    public int seeked_progress = 0;
    private boolean isPaused = false;
    private int currentLength = 0;
    private boolean loopingAudio = false;
    private boolean loopPressed = false;
    private Handler mHandler = new Handler();


    // Function to Play audio on press of Play Button
    public void playAudio(View view){
        //Log.i("Info","Button Pressed: PLAY");

        // Set up Media Player
        mediaPlayer = MediaPlayer.create(this, R.raw.suit_suit_guru_randhawa_arjun);

        // Song Control "SeekBar"
        final SeekBar songSeekbar = (SeekBar)findViewById(R.id.songSeekBar);
        songSeekbar.setProgress(0);

        // Maximum song length
        final int maxSongLength = mediaPlayer.getDuration();

        // Set Image on Screen
        ImageView songImage = (ImageView)findViewById(R.id.imageView);

        // Get Audio File Metadata
        mediaMetadataRetriever = (MediaMetadataRetriever) new MediaMetadataRetriever();
        Uri uri = (Uri) Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.suit_suit_guru_randhawa_arjun);
        mediaMetadataRetriever.setDataSource(this, uri);

        // Get Song Title and Display on Top
        String title = (String) mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        TextView songTitle = (TextView) findViewById(R.id.titleTextView);
        songTitle.setText(title);

        // Get Song Album Name and Display on Top
        String album = (String) mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
        final TextView songAlbum = (TextView) findViewById(R.id.albumTextView);
        songAlbum.setText(album);

        // Get Song Duration
        String songDuration = (String) mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
        final int duration = Integer.valueOf(songDuration);

        // If Song Picture is Available then Display it
        if (mediaMetadataRetriever.getEmbeddedPicture() != null){
            // Get Song Picture
            Bitmap rawImage = BitmapFactory.decodeByteArray(mediaMetadataRetriever.getEmbeddedPicture(), 0, mediaMetadataRetriever.getEmbeddedPicture().length);
            songImage.setImageBitmap(rawImage);
        }
        else{
            //Log.i("Info","Song Image Not Available !!");
            songImage.setImageResource(R.drawable.img);
        }

        if (isPaused){
            mediaPlayer.seekTo(currentLength);
            mediaPlayer.start();
        }
        else {
            // Play Audio File from Beginning
            mediaPlayer.start();
        }

        // Code to Sync Music with SeekBar
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                songSeekbar.setMax(duration);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while ((mediaPlayer != null) && (mediaPlayer.getCurrentPosition() < mediaPlayer.getDuration())){
                            songSeekbar.setProgress(mediaPlayer.getCurrentPosition());
                            Message msg = new Message();
                            int ms = mediaPlayer.getCurrentPosition();
                            msg.obj = ms / 1000;
                            mHandler.sendMessage(msg);
                            try {
                                Thread.sleep(100);
                            }
                            catch (InterruptedException e){
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }
        });
    }


    // Function to Pause Audio
    public void pauseAudio(View view){
        //Log.i("Info","Button Pressed: PAUSE");
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            Log.i("Is Audio Playing ?", String.valueOf(mediaPlayer.isPlaying()));
            isPaused = true;
            // Get current position where song is Pause to Play from this Length and
            // not from Beginning
            currentLength = mediaPlayer.getCurrentPosition();
        }
    }


    // Function to Loop Audio i.e. Play same audio again and again
    public void loopAudio(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                if ((!loopPressed) && (!loopingAudio)){
                    mediaPlayer.setLooping(true);
                    loopingAudio = true;
                    loopPressed = true;
                    Log.i("Is Audio Looping ?", "True");
                }
                else{
                    mediaPlayer.setLooping(false);
                    loopingAudio = false;
                    loopPressed = false;
                    Log.i("Is Audio Looping ?", "False");
                }
            }
        }).start();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize "audioManager"
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);

        // Get Max Volume of Device for Music
        int maxVol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        // Get Current Volume of Phone
        int currentVol = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        // "Volume Control" using "SeekBar"
        SeekBar volumeControl = (SeekBar)findViewById(R.id.volumeSeekBar);

        // Set Max Value for Volume Seekbar
        volumeControl.setMax(maxVol);
        // Set Progressbar to Current Volume of Phone
        volumeControl.setProgress(currentVol);

        // This waits till seekbar is changed and then calls this listener
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            // Function to Increase Volume on Change in "SeekBar"
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Seekbar to set Volume of Music on Device
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
                //Log.i("Seekbar Changed",Integer.toString(progress));
            }

            // Function to track touch at starting
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            // Function to track stop touch
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
