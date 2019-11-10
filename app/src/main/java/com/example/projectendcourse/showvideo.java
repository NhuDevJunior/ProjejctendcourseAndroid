package com.example.projectendcourse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.github.rtoshiro.view.video.FullscreenVideoLayout;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class showvideo extends AppCompatActivity  {
    View mBottomLayout;
    FrameLayout frameLayout;
    View mVideoLayout;
    UniversalVideoView mVideoView;
    UniversalMediaController mMediaController;
    TextView namevideo;
    ListView ivContact;
    TextView textvisible;
    Switch autovideo;
    LinearLayout linearLayout;
    AdapterListView adapterListView;
    int vt=0;
    private AudioManager audio;
    private int checkname(String name, ArrayList<Contact> list)
    {
        for(int i=0;i<list.size();i++)
        {
            if(name.equals(list.get(i).getName()))
            {
                return i;

            }

        }
        return -1;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showvideo);
        audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        ivContact=findViewById(R.id.listauto);
        frameLayout=findViewById(R.id.video_layout);
        namevideo = findViewById(R.id.namevideo);
        mVideoView = findViewById(R.id.videoView);
        mVideoLayout=findViewById(R.id.video_layout);
        //textvisible=findViewById(R.id.textdm);
        autovideo=findViewById(R.id.autook);
        Intent intent = getIntent();
        ListOject list =(ListOject) intent.getSerializableExtra("video");
        final ArrayList listvideo=list.arrayList;
        //ArrayList<Contact> listvideo=list.arrayList;
        final String name = intent.getStringExtra("name");
        final String link = intent.getStringExtra("link");
        String date=intent.getStringExtra("date");
        final String avatar=intent.getStringExtra("avatar");
        namevideo.setText(name);
        namevideo.setTextSize(20);
        namevideo.setTextColor(Color.WHITE);
        Uri uri = Uri.parse(link);
        mVideoView.setVideoURI(uri);
        mMediaController = findViewById(R.id.media_controller);
        mVideoView.setMediaController(mMediaController);
        mVideoView.start();
        vt=checkname(name,listvideo);
        Contact update=new Contact(name,"yes",avatar,link);
        listvideo.set(vt,update);
        adapterListView=new AdapterListView(listvideo);
        ivContact.setAdapter(adapterListView);
        ivContact.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact= (Contact) listvideo.get(position);
                Contact contact1= (Contact) listvideo.get(vt);
                Contact update1=new Contact(contact1.getName(),"no",contact1.getAvatar(),contact1.getLinkvideo());
                listvideo.set(vt,update1);
                vt=position;
                namevideo.setText(contact.getName());
                namevideo.setTextSize(20);
                namevideo.setTextColor(Color.WHITE);
                Uri uri = Uri.parse(contact.getLinkvideo());
                mVideoView.setVideoURI(uri);
                mVideoView.setMediaController(mMediaController);
                mVideoView.start();
                Contact update2=new Contact(contact.getName(),"yes",contact.getAvatar(),contact.getLinkvideo());
                listvideo.set(vt,update2);
                adapterListView=new AdapterListView(listvideo);
                ivContact.setAdapter(adapterListView);
            }
        });
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
                    layoutParams.height = 400;
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
        mVideoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (autovideo.isChecked()) {
                    Contact contact1= (Contact) listvideo.get(vt);
                    Contact update1=new Contact(contact1.getName(),"no",contact1.getAvatar(),contact1.getLinkvideo());
                    listvideo.set(vt,update1);
                    vt = vt + 1;
                    if (vt >= listvideo.size()) {
                        vt = 0;
                    }
                    Contact contact = (Contact) listvideo.get(vt);
                    Contact update=new Contact(contact.getName(),"yes",contact.getAvatar(),contact.getLinkvideo());
                    listvideo.set(vt,update);
                    namevideo.setText(contact.getName());
                    namevideo.setTextSize(20);
                    namevideo.setTextColor(Color.WHITE);
                    Uri uri = Uri.parse(contact.getLinkvideo());
                    mVideoView.setVideoURI(uri);
                    mVideoView.setMediaController(mMediaController);
                    mVideoView.start();
                    adapterListView=new AdapterListView(listvideo);
                    ivContact.setAdapter(adapterListView);
                }
            }
        });
      mMediaController.setOnTouchListener(new View.OnTouchListener() {
          int currentVolume;
          int currentmedia;
          private final int MAX_CLICK_DURATION = 400;
          private final int MAX_CLICK_DISTANCE = 100;
          private  final int MAX=100;
          private long startClickTime;
          private float x1;
          private float y1;
          private float x2;
          private float y2;
          private float dx;
          private float dy;
          private float wide;
          private float high;
          @Override
          public boolean onTouch(View v, MotionEvent event) {
                  AudioManager audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                  currentVolume = audio.getStreamVolume(AudioManager.STREAM_MUSIC);
                  currentmedia=mVideoView.getCurrentPosition();
                  int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                  int maxmedia=mVideoView.getDuration();
                  int volumeok;
                  int mediaok;
                  wide = mMediaController.getWidth();
                  high = mMediaController.getHeight();

                  switch (event.getAction()) {
                      case MotionEvent.ACTION_DOWN: {
                          startClickTime = Calendar.getInstance().getTimeInMillis();
                          x1 = event.getX();
                          y1 = event.getY();
                          mMediaController.invalidate();
                          return true;
                      }
                      case MotionEvent.ACTION_MOVE: {
                          long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                          x2 = event.getX();
                          y2 = event.getY();
                          dx = x2 - x1;
                          dy = y2 - y1;
                          float percenty = dy / high;
                          float percentx=dx/wide;
                          if(Math.abs(dx)<Math.abs(dy)+6) {
                              if (clickDuration < MAX_CLICK_DURATION && dx < MAX_CLICK_DISTANCE && dy < MAX_CLICK_DISTANCE) {
                                  //Log.v("", "On Item Clicked:: ");
                                  if (dy <= -1) {
                                      float addvolume0 = percenty * maxVolume / 3;
                                      volumeok = (int) (currentVolume - addvolume0);
                                      if (volumeok > 15) {
                                          volumeok = 15;
                                      }
                                      audio.setStreamVolume(AudioManager.STREAM_MUSIC, volumeok, AudioManager.FLAG_SHOW_UI);

                                  } else if (dy >= 1) {
                                      int addvolume1 =(int) (percenty * maxVolume);
                                      volumeok = (int) (currentVolume - addvolume1);
                                      if (volumeok < 0) {
                                          volumeok = 0;
                                      }
                                      audio.setStreamVolume(AudioManager.STREAM_MUSIC, volumeok, AudioManager.FLAG_SHOW_UI);
                                  }
                              }
                          }
                          else if(Math.abs(dx)>Math.abs(dy)+6){
                              if (dx >3) {
                                  float addmedia0 = percentx * maxmedia/3;
                                  mediaok = (int) (currentmedia + addmedia0);
                                  if (mediaok > maxmedia)
                                      mediaok = maxmedia;
                                  mVideoView.seekTo(mediaok);
                              } else if (dx <-3) {
                                  float addmedia = percentx * maxmedia/3;
                                  mediaok = (int) (currentmedia + addmedia);
                                  if (mediaok < 0)
                                      mediaok = 0;
                                  mVideoView.seekTo(mediaok);
                              }
                          }
                          mMediaController.invalidate();
                          return true;
                      }
                  }

                  return false;
              }

      });
    }

}
