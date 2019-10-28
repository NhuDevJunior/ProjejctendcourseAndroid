package com.example.projectendcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.VideoView;

import com.github.rtoshiro.view.video.FullscreenVideoLayout;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import java.io.IOException;

public class showvideo extends AppCompatActivity {
    View mBottomLayout;
    View mVideoLayout;
    UniversalVideoView mVideoView;
    UniversalMediaController mMediaController;
    TextView namevideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showvideo);
        namevideo = findViewById(R.id.namevideo);
        mVideoView = findViewById(R.id.videoView);
        mVideoLayout=findViewById(R.id.video_layout);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String link = intent.getStringExtra("link");
        namevideo.setText(name);
        namevideo.setTextSize(20);
        namevideo.setTextColor(Color.WHITE);
        Uri uri = Uri.parse(link);
        mVideoView.setVideoURI(uri);
        mMediaController = findViewById(R.id.media_controller);
        mVideoView.setMediaController(mMediaController);
        
        
        // xu li su kien


        mVideoView.setVideoViewCallback(new UniversalVideoView.VideoViewCallback() {
            final  String TAG="nhu";
            @Override
            public void onScaleChange(boolean isFullscreen) {
                if (isFullscreen) {
                    ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    mVideoLayout.setLayoutParams(layoutParams);
                    //GONE the unconcerned views to leave room for video and controller
                    //mBottomLayout.setVisibility(View.GONE);
                } else {
                    ViewGroup.LayoutParams layoutParams = mVideoLayout.getLayoutParams();
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    mVideoLayout.setLayoutParams(layoutParams);
                    //mBottomLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPause(MediaPlayer mediaPlayer) { // Video pause
                Log.d(TAG, "onPause UniversalVideoView callback");
            }

            @Override
            public void onStart(MediaPlayer mediaPlayer) { // Video start/resume to play
                Log.d(TAG, "onStart UniversalVideoView callback");
            }

            @Override
            public void onBufferingStart(MediaPlayer mediaPlayer) {// steam start loading
                Log.d(TAG, "onBufferingStart UniversalVideoView callback");
            }

            @Override
            public void onBufferingEnd(MediaPlayer mediaPlayer) {// steam end loading
                Log.d(TAG, "onBufferingEnd UniversalVideoView callback");
            }

        });


    }

}
