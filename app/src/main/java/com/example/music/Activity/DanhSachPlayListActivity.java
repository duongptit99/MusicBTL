package com.example.music.Activity;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.music.Adapter.DSPlayListAdapter;
import com.example.music.Model.PlayList;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachPlayListActivity extends AppCompatActivity {


    RecyclerView recyclerViewplaylist;
    DSPlayListAdapter adapter;
    androidx.appcompat.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_play_list);
        AnhXa();
        init();
        initRecyclerView();
        GetData();
    }

    private void initRecyclerView() {
        adapter = new DSPlayListAdapter(this);
        recyclerViewplaylist.setAdapter(adapter);
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<PlayList>> callback = dataservice.GetDSPLayList();
        callback.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                ArrayList<PlayList> playListArrayList = (ArrayList<PlayList>) response.body();
                adapter.submitList(playListArrayList);
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    private void init() {
        toolbar = findViewById(R.id.toolbarplaynhac);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void AnhXa() {
        recyclerViewplaylist = findViewById(R.id.recycler_playlist);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}