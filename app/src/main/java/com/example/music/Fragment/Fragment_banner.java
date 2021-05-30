package com.example.music.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.music.Adapter.BannerAdapter;
import com.example.music.Model.QuangCao;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_banner extends Fragment {
    View view;
    ViewPager viewPager;
    CircleIndicator circleIndicator;
    BannerAdapter bannerAdapter;
    Runnable runnable;
    Handler handler;
    int currentItem;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_banner,container,false);
        GetData();
        AnhXa();
        return view;
    }
    public void AnhXa(){
        viewPager = view.findViewById(R.id.view_pager_banner);
        circleIndicator = view.findViewById(R.id.circleindicator);
    }
    private  void GetData(){
        Dataservice dataservice = APIService.getService();
        Call<List<QuangCao>> callback =dataservice.GetDataBanner();
        callback.enqueue(new Callback<List<QuangCao>>() {
            @Override
            public void onResponse(Call<List<QuangCao>> call, Response<List<QuangCao>> response) {
                ArrayList<QuangCao> banner = (ArrayList<QuangCao>) response.body();
                bannerAdapter = new BannerAdapter(getActivity(),banner);
                viewPager.setAdapter(bannerAdapter);
                circleIndicator.setViewPager(viewPager);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currentItem = viewPager.getCurrentItem();
                        currentItem++;
                        if(currentItem >= viewPager.getAdapter().getCount()){
                            currentItem = 0;
                        }
                        viewPager.setCurrentItem(currentItem,true);
                        handler.postDelayed(runnable,4500);
                    }
                };
                handler.postDelayed(runnable,4500);

            }

            @Override
            public void onFailure(Call<List<QuangCao>> call, Throwable t) {

            }
        });
    }

}
