package com.example.projectendcourse.ui.dashboard;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.projectendcourse.AdapterContact;
import com.example.projectendcourse.Contact;
import com.example.projectendcourse.R;
import com.example.projectendcourse.databinding.FragmentCategoriesBinding;
import com.example.projectendcourse.databinding.FragmentHomeBinding;
import com.example.projectendcourse.ui.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class CategoriesFragment extends Fragment {
    TextView textView;
    FragmentCategoriesBinding binding;
    private static final String TAG = "CategoriesFragment";
    ArrayList<Contact> contacts=new ArrayList<>();
    String result="";
    RecyclerView recyclerView;
    AdapterContactCate adapterContactCate;

    String url="http://demo6416315.mockable.io/getProduct";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_categories, container, false);
        //textView=view.findViewById(R.id.text_categories);
        binding.textCategories.setText("Categories");
        binding.textCategories.setTextSize(25);
        binding.textCategories.setTextColor(Color.RED);
        DoGetData home=new DoGetData(url);
        home.execute();
        return binding.getRoot();
    }
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
                    String date = "";
                    String avatar =ob.getString("thumb");
                    String linkvideo="";
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
            binding.progressbar1.setVisibility(View.VISIBLE);

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

            //RecyclerView.LayoutManager layoutManager  = new LinearLayoutManager(getContext(),
                   // LinearLayoutManager.VERTICAL, false);
            RecyclerView.LayoutManager layoutManager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
            adapterContactCate = new AdapterContactCate(contacts);
            binding.listcategories.setLayoutManager(layoutManager);
            binding.listcategories.setAdapter(adapterContactCate);
            binding.progressbar1.setVisibility(View.GONE);
        }
    }


}