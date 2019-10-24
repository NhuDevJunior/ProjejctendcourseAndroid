package com.example.projectendcourse.ui.home;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import com.example.projectendcourse.R;
import com.example.projectendcourse.databinding.FragmentHomeBinding;

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
    ArrayList<Contact> contacts=new ArrayList<>();
    String result="";
    TextView textView;
    FragmentHomeBinding binding;
    RecyclerView recyclerView;
    AdapterContact adapterContact;

    String url="https://demo5639557.mockable.io/getVideoHot";



    // main chinh cua fragment

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        binding.videohot.setText("Video Hot");
        binding.videohot.setTextSize(20);
        binding.videohot.setTextColor(Color.RED);
        DoGetData home=new DoGetData(url);
        home.execute();
        return binding.getRoot();
    }


    //class boc tach json


    public class DoGetData extends AsyncTask<Void,Void,Void> {
        String url;

        public DoGetData(String url) {
            this.url = url;
        }
        public void getArrayJson()
        {
            try {

                JSONArray jsonArray = new JSONArray(result);
                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {

                    JSONObject ob = jsonArray.getJSONObject(i);
                    String name = ob.getString("title");
                    String date = ob.getString("date_created");
                    String avatar =ob.getString("avatar");
                    String linkvideo=ob.getString("file_mp4");
                    Contact contact=new Contact(name,date,avatar,linkvideo);
                    contacts.add(contact);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.progressbar.setVisibility(View.VISIBLE);

        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
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
            RecyclerView.LayoutManager layoutManager  = new LinearLayoutManager(getContext(),
                    LinearLayoutManager.HORIZONTAL, false);
            adapterContact = new AdapterContact(contacts);
            binding.listhotvideo.setLayoutManager(layoutManager);
            binding.listhotvideo.setAdapter(adapterContact);
            binding.progressbar.setVisibility(View.GONE);
        }
    }
    // load anh tu internet


}