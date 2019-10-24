package com.example.projectendcourse.ui.notifications;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.projectendcourse.R;
import com.example.projectendcourse.databinding.FragmentPlaylistBinding;

public class PlayListFragment extends Fragment {
    FragmentPlaylistBinding binding;
    /*

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_playlist, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
    */
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //View view=inflater.inflate(R.layout.fragment_playlist,container,false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_playlist, container, false);
        binding.textPlaylist.setText("welcome to playlist");
        binding.textPlaylist.setTextSize(30);
        binding.textPlaylist.setTextColor(Color.WHITE);
        return binding.getRoot();
    }
}