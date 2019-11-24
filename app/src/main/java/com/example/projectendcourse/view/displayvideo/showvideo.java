package com.example.projectendcourse.view.displayvideo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.example.projectendcourse.Adapter.AdapterListView;
import com.example.projectendcourse.contact.Contact;
import com.example.projectendcourse.R;
import com.example.projectendcourse.databinding.ActivityShowvideoBinding;
import com.universalvideoview.UniversalMediaController;
import com.universalvideoview.UniversalVideoView;

import java.util.ArrayList;
import java.util.Calendar;

public class showvideo extends AppCompatActivity  {
    ActivityShowvideoBinding binding;
    ArrayList listvideo;
    ListOject list;
    AdapterListView adapterListView;
    int vt=0;
    public AudioManager audio;
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
        binding = DataBindingUtil.setContentView(this, R.layout.activity_showvideo);

        audio = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        Intent intent = getIntent();
        list =(ListOject) intent.getSerializableExtra("video");
        listvideo=list.arrayList;

        final String name = intent.getStringExtra("name");
        final String link = intent.getStringExtra("link");
        final String avatar=intent.getStringExtra("avatar");
        binding.namevideo.setText(name);
        binding.namevideo.setTextSize(20);
        binding.namevideo.setTextColor(Color.WHITE);
        Uri uri = Uri.parse(link);
        binding.videoView.setVideoURI(uri);
       // mMediaController = findViewById(R.id.media_controller);
        binding.videoView.setMediaController(binding.mediaController);
        binding.videoView.start();
        vt=checkname(name,listvideo);
        Contact update=new Contact(name,"yes",avatar,link);
        listvideo.set(vt,update);
        adapterListView=new AdapterListView(listvideo);
        binding.listauto.setAdapter(adapterListView);
        binding.listauto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Contact contact= (Contact) listvideo.get(position);
                Contact contact1= (Contact) listvideo.get(vt);
                Contact update1=new Contact(contact1.getName(),"no",contact1.getAvatar(),contact1.getLinkvideo());
                listvideo.set(vt,update1);
                vt=position;
                binding.namevideo.setText(contact.getName());
                binding.namevideo.setTextSize(20);
                binding.namevideo.setTextColor(Color.WHITE);
                Uri uri = Uri.parse(contact.getLinkvideo());
                binding.videoView.setVideoURI(uri);
                binding.videoView.setMediaController(binding.mediaController);
                binding.videoView.start();
                Contact update2=new Contact(contact.getName(),"yes",contact.getAvatar(),contact.getLinkvideo());
                listvideo.set(vt,update2);
                adapterListView=new AdapterListView(listvideo);
                binding.listauto.setAdapter(adapterListView);
            }
        });
        // xu li su kien


        binding.videoView.setVideoViewCallback(new UniversalVideoView.VideoViewCallback() {
            final  String TAG="nhu";
            @Override
            public void onScaleChange(boolean isFullscreen) {
                if (isFullscreen) {
                    ViewGroup.LayoutParams layoutParams = binding.videoLayout.getLayoutParams();
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    binding.videoLayout.setLayoutParams(layoutParams);
                    //GONE the unconcerned views to leave room for video and controller
                    //mBottomLayout.setVisibility(View.GONE);
                } else {
                    ViewGroup.LayoutParams layoutParams = binding.videoLayout.getLayoutParams();
                    layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    layoutParams.height = 400;
                    binding.videoLayout.setLayoutParams(layoutParams);
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
        binding.videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (binding.autook.isChecked()) {
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
                    binding.namevideo.setText(contact.getName());
                    binding.namevideo.setTextSize(20);
                    binding.namevideo.setTextColor(Color.WHITE);
                    Uri uri = Uri.parse(contact.getLinkvideo());
                    binding.videoView.setVideoURI(uri);
                    binding.videoView.setMediaController(binding.mediaController);
                    binding.videoView.start();
                    adapterListView=new AdapterListView(listvideo);
                    binding.listauto.setAdapter(adapterListView);
                }
            }
        });
      binding.mediaController.setOnTouchListener(new View.OnTouchListener() {
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
                  currentmedia=binding.videoView.getCurrentPosition();
                  int maxVolume = audio.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
                  int maxmedia=binding.videoView.getDuration();
                  int volumeok;
                  int mediaok;
                  wide = binding.mediaController.getWidth();
                  high = binding.mediaController.getHeight();

                  switch (event.getAction()) {
                      case MotionEvent.ACTION_DOWN: {
                          startClickTime = Calendar.getInstance().getTimeInMillis();
                          x1 = event.getX();
                          y1 = event.getY();
                          binding.mediaController.invalidate();
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
                                  binding.videoView.seekTo(mediaok);
                              } else if (dx <-3) {
                                  float addmedia = percentx * maxmedia/3;
                                  mediaok = (int) (currentmedia + addmedia);
                                  if (mediaok < 0)
                                      mediaok = 0;
                                  binding.videoView.seekTo(mediaok);
                              }
                          }
                          binding.mediaController.invalidate();
                          return true;
                      }
                  }

                  return false;
              }

      });
    }

}
