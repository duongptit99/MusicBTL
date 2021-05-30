package com.example.music.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.music.Adapter.AllAlbumAdapter;
import com.example.music.Model.Album;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachAlbumActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AllAlbumAdapter allAlbumAdapter;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_album);
        AnhXa();
        initRecyclerView();
        GetData();
    }

    private void initRecyclerView() {
        allAlbumAdapter = new AllAlbumAdapter(this);
        recyclerView.setAdapter(allAlbumAdapter);
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Album>> callback = dataservice.GetAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> albumArrayList = (ArrayList<Album>) response.body();
                allAlbumAdapter.submitList(albumArrayList);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void AnhXa() {
        recyclerView = findViewById(R.id.recycler_ds_album);
        toolbar = findViewById(R.id.toolbar_ds_album);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Album");
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