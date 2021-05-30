package com.example.music.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Adapter.TheLoaiAdapter;
import com.example.music.Model.ChuDevaTheLoai;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_TheLoai extends Fragment {
    RecyclerView recyclerView;
    TheLoaiAdapter adapter;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_theloai, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new TheLoaiAdapter();
        recyclerView.setAdapter(adapter);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<ChuDevaTheLoai> callback = dataservice.GetCategoryMusic();
        callback.enqueue(new Callback<ChuDevaTheLoai>() {
            @Override
            public void onResponse(Call<ChuDevaTheLoai> call, Response<ChuDevaTheLoai> response) {
                ChuDevaTheLoai chuDevaTheLoai = response.body();
                adapter.submitData(chuDevaTheLoai);
            }

            @Override
            public void onFailure(Call<ChuDevaTheLoai> call, Throwable t) {

            }
        });

    }
}
