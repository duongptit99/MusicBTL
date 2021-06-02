package com.example.music.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Activity.PlayNhacActivity;
import com.example.music.Adapter.PlayNhacAdapter;
import com.example.music.R;

public class Fragment_PlayDSBaiHat extends Fragment {
    View view;
    RecyclerView recyclerView;
    PlayNhacAdapter playNhacAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater, @Nullable  ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_play_ds_baihat,container,false);
        recyclerView = view.findViewById(R.id.recycler_play_ds_baihat);
        if(PlayNhacActivity.baiHatArrayList.size() > 0){
            playNhacAdapter = new PlayNhacAdapter(PlayNhacActivity.baiHatArrayList);
            recyclerView.setAdapter(playNhacAdapter);
        }
        return view;
    }
}
