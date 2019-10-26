package com.example.projectendcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;

public class showvideo extends AppCompatActivity {
    TextView namevideo;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showvideo);
        namevideo=findViewById(R.id.namevideo);
        videoView=findViewById(R.id.videoview);
        Intent intent=getIntent();
        String name=intent.getStringExtra("name");
        String link=intent.getStringExtra("link");
        namevideo.setText(name);
        namevideo.setTextSize(20);
        namevideo.setTextColor(Color.WHITE);
        Uri uri = Uri.parse(link);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

    }
}
