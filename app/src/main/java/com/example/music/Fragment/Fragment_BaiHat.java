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

import com.example.music.Adapter.BaiHatAdapter;
import com.example.music.Model.BaiHat;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_BaiHat extends Fragment {
    View view;
    RecyclerView recyclerView;
    BaiHatAdapter baiHatAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_baihat,container,false);
        recyclerView = view.findViewById(R.id.recycler_baihat);
        GetData();
        baiHatAdapter = new BaiHatAdapter(getActivity());
        recyclerView.setAdapter(baiHatAdapter);
        return view;
    }
    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback =   dataservice.GetBaiHat();
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> baiHatArrayList = (ArrayList<BaiHat>) response.body();
                baiHatAdapter.submitList(baiHatArrayList);

            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
