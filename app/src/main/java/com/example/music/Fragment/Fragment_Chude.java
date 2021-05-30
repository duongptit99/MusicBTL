package com.example.music.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Adapter.AlbumAdapter;
import com.example.music.Adapter.ChuDeAdapter;
import com.example.music.Model.Album;
import com.example.music.Model.ChuDe;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Chude extends Fragment {
    RecyclerView recyclerView;
    ChuDeAdapter chuDeAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chude,container,false);
        recyclerView = view.findViewById(R.id.recycler_chude);
        initRecyclerView();
        GetData();
        return view;
    }

    private void initRecyclerView() {
        chuDeAdapter = new ChuDeAdapter();
        recyclerView.setAdapter(chuDeAdapter);
    }
    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<ChuDe>> callback = dataservice.GetChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> chuDes = (ArrayList<ChuDe>) response.body();
                chuDeAdapter.submitList(chuDes);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });
    }
}
