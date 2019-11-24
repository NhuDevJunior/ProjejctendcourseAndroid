package com.example.projectendcourse.view.Playlist;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.projectendcourse.R;
import com.example.projectendcourse.databinding.FragmentPlaylistBinding;

public class PlayListFragment extends Fragment {
    FragmentPlaylistBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_playlist, container, false);
        binding.textPlaylist.setText("welcome to playlist");
        binding.textPlaylist.setTextSize(30);
        binding.textPlaylist.setTextColor(Color.WHITE);
        return binding.getRoot();
    }
}