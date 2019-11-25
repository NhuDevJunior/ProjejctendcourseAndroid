package com.example.projectendcourse.view.Newsfilm;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.projectendcourse.Adapter.AdapterListView;
import com.example.projectendcourse.R;
import com.example.projectendcourse.contact.Contact;
import com.example.projectendcourse.databinding.FragmentPlaylistBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class NewsListFragment extends Fragment {
    private static final String TAG = "NewsListFragment";
    FragmentPlaylistBinding binding;
    ArrayList<Contact> listnews=new ArrayList<>();
    String url="http://demo4781122.mockable.io/getnewfilm";
    AdapterListView adapterListView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_playlist, container, false);
        DoGetData1 home1=new DoGetData1(url);
        home1.execute();
        ArrayList<Contact>a=listnews;
        return binding.getRoot();
    }
    public class DoGetData1 extends AsyncTask<Void,Void,Void> {
        String url;
        String result="";

        public DoGetData1(String url) {
            this.url = url;
        }
        public void getArrayJson()
        {
            try {
                listnews.clear();
                JSONArray jsonArray = new JSONArray(result);
                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {

                    JSONObject ob = jsonArray.getJSONObject(i);
                    String name = ob.getString("title");
                    String date = "no";
                    String avatar =ob.getString("avatar");
                    String linkvideo=ob.getString("file_mp4");
                    Contact contact=new Contact(name,date,avatar,linkvideo);
                    listnews.add(contact);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            binding.progressbar3.setVisibility(View.VISIBLE);
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
            adapterListView=new AdapterListView(listnews);
            binding.listnewss.setAdapter(adapterListView);
            binding.progressbar3.setVisibility(View.GONE);
            binding.listnewss.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Contact contact=listnews.get(position);
                    Intent intent=new Intent(getContext(), Detailnews.class);
                    intent.putExtra("link",contact.getLinkvideo());
                    startActivity(intent);
                }
            });
        }
    }
}