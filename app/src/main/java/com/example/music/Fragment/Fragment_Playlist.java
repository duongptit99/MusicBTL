package com.example.music.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Activity.DanhSachBaiHatActivity;
import com.example.music.Activity.DanhSachPlayListActivity;
import com.example.music.Adapter.PlayListAdapter;
import com.example.music.Model.PlayList;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Playlist extends Fragment {

    View view;
    TextView textViewTitle,textViewMorePlayList;
    ListView listView;
    PlayListAdapter playListAdapter;
    ArrayList<PlayList> mangPlayList;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_playlist,container,false);
        AnhXa();
        GetData();

        textViewMorePlayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(),DanhSachPlayListActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void AnhXa() {
        textViewTitle = view.findViewById(R.id.text_view_playlist);
        textViewMorePlayList = view.findViewById(R.id.text_view_more_playlist);
        recyclerView = view.findViewById(R.id.recycler_playlists);
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<PlayList>> callback = dataservice.GetPlaylistCurrent();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                mangPlayList = (ArrayList<PlayList>) response.body();
                playListAdapter = new PlayListAdapter(mangPlayList);
                recyclerView.setAdapter(playListAdapter);

            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }
}
