package com.example.projectendcourse.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectendcourse.AdapterContact;
import com.example.projectendcourse.Contact;
import com.example.projectendcourse.IonClickContact;
import com.example.projectendcourse.R;
import com.example.projectendcourse.SliderAdapterExample;
import com.example.projectendcourse.databinding.FragmentHomeBinding;
import com.example.projectendcourse.showvideo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    FragmentHomeBinding binding;
    AdapterContact adapterContact;
    AdapterContact adapterContact1;
    ArrayList<Contact> contacthot=new ArrayList<>();
    ArrayList<Contact> contactnew=new ArrayList<>();

    String url1="https://demo5639557.mockable.io/getVideoHot";
    String url2="http://demo6416315.mockable.io/getnew";



    // main chinh cua fragment

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {




        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);




        SliderAdapterExample adapter = new SliderAdapterExample(getContext());

        binding.imageSlider.setSliderAdapter(adapter);
        binding.imageSlider.startAutoCycle();
        // set hot video
        binding.videohot.setText("hot video");
        binding.videohot.setTextSize(20);
        binding.videohot.setTextColor(Color.RED);
        DoGetData1 home1=new DoGetData1(url1);
        home1.execute();
        RecyclerView.LayoutManager layoutManager  = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        adapterContact = new AdapterContact(contacthot);
        binding.listhotvideo.setLayoutManager(layoutManager);
        binding.listhotvideo.setAdapter(adapterContact);
       contacthot.clear();
       // recyclerView.removeAllViews();
        //set new video

        binding.videonew.setText("recent video");
        binding.videonew.setTextSize(20);
        binding.videonew.setTextColor(Color.RED);
        DoGetData2 home2=new DoGetData2(url2);
        home2.execute();
        RecyclerView.LayoutManager layoutManager1  = new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false);
        binding.listnewvideo.setLayoutManager(layoutManager1);
        adapterContact1 = new AdapterContact(contactnew);
        binding.listnewvideo.setAdapter(adapterContact1);
        contactnew.clear();
        //bat su kien hot video
        adapterContact.setIonClickContact(new IonClickContact() {
            @Override
            public void onclickName(Contact contact) {
                Toast.makeText(getContext(), contact.getName(), Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getContext(), showvideo.class);
                intent.putExtra("name",contact.getName());
                intent.putExtra("link",contact.getLinkvideo());
                startActivity(intent);
            }

            @Override
            public void onclickAvatar(Contact contact) {
                Toast.makeText(getContext(), contact.getName(), Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getContext(), showvideo.class);
                intent.putExtra("name",contact.getName());
                intent.putExtra("link",contact.getLinkvideo());
                startActivity(intent);
            }
        });
        // bat su kien recent video
        adapterContact1.setIonClickContact(new IonClickContact() {
            @Override
            public void onclickName(Contact contact) {
                Toast.makeText(getContext(), contact.getName(), Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getContext(), showvideo.class);
                intent.putExtra("name",contact.getName());
                intent.putExtra("link",contact.getLinkvideo());
                startActivity(intent);
            }

            @Override
            public void onclickAvatar(Contact contact) {
                Toast.makeText(getContext(), contact.getName(), Toast.LENGTH_LONG).show();
                Intent intent=new Intent(getContext(), showvideo.class);
                intent.putExtra("name",contact.getName());
                intent.putExtra("link",contact.getLinkvideo());
                startActivity(intent);

            }
        });

        return binding.getRoot();
    }


    //class boc tach json


    public class DoGetData1 extends AsyncTask<Void,Void,Void> {
        String url;
        String result="";

        public DoGetData1(String url) {
            this.url = url;
        }
        public void getArrayJson()
        {
            try {
                contacthot.clear();
                JSONArray jsonArray = new JSONArray(result);
                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {

                    JSONObject ob = jsonArray.getJSONObject(i);
                    String name = ob.getString("title");
                    String date = ob.getString("date_created");
                    String avatar =ob.getString("avatar");
                    String linkvideo=ob.getString("file_mp4");
                    Contact contact=new Contact(name,date,avatar,linkvideo);
                    contacthot.add(contact);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.progressbar1.setVisibility(View.VISIBLE);

        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                result="";
                URL url1 = new URL(url);
                URLConnection connection = url1.openConnection();
                InputStream is = connection.getInputStream();
                int byteCharacter;

                while ((byteCharacter = is.read()) != -1) {
                    result += (char) byteCharacter;
                }

                Log.d(TAG, "doInBackground: " + result);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            getArrayJson();
            binding.progressbar1.setVisibility(View.GONE);

        }
    }
    // load anh tu internet

    public class DoGetData2 extends AsyncTask<Void,Void,Void> {
        String url;
        String result="";

        public DoGetData2(String url) {
            this.url = url;
        }
        public void getArrayJson()
        {
            try {
                contactnew.clear();
                JSONArray jsonArray = new JSONArray(result);
                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {

                    JSONObject ob = jsonArray.getJSONObject(i);
                    String name = ob.getString("title");
                    String date = ob.getString("date_created");
                    String avatar =ob.getString("avatar");
                    String linkvideo=ob.getString("file_mp4");
                    Contact contact=new Contact(name,date,avatar,linkvideo);
                    contactnew.add(contact);

                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.progressbar2.setVisibility(View.VISIBLE);

        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                result="";
                URL url1 = new URL(url);
                URLConnection connection = url1.openConnection();
                InputStream is = connection.getInputStream();
                int byteCharacter;

                while ((byteCharacter = is.read()) != -1) {
                    result += (char) byteCharacter;
                }

                Log.d(TAG, "doInBackground: " + result);

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            getArrayJson();
            binding.progressbar2.setVisibility(View.GONE);

        }
    }


}