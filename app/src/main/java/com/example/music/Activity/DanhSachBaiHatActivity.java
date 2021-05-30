package com.example.music.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.music.Adapter.DSBaiHatAdapter;
import com.example.music.Model.Album;
import com.example.music.Model.BaiHat;
import com.example.music.Model.ChuDe;
import com.example.music.Model.PlayList;
import com.example.music.Model.QuangCao;
import com.example.music.Model.TheLoai;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DanhSachBaiHatActivity extends AppCompatActivity {
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    QuangCao quangCao;
    ImageView imgDSbaihat;
    ArrayList<BaiHat> arraybaihat;
    DSBaiHatAdapter dsBaiHatAdapter;
    PlayList playList;
    ChuDe chude;
    TheLoai theLoai;
    Album album;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_bai_hat);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Dataintent();
        AnhXa();
        initRecycerView();
        if (quangCao != null && !quangCao.getTenBaiHat().equals("")) {
            setValueInView(quangCao.getTenBaiHat(), quangCao.getHinhBaiHat());
            GetDataQuangCao(quangCao.getIdQuangCao());

        }
        if (playList != null && !playList.getTen().equals("")) {
            setValueInView(playList.getTen(), playList.getHinhPlayList());
            GetDataPlayList(playList.getIdPlayList());
        }
        if (theLoai != null && !theLoai.getTentheloai().equals("")) {
            setValueInView(theLoai.getTentheloai(), theLoai.getHinhtheloai());
            GetDataTheLoai(theLoai.getIdtheloai());
        }
        if (chude != null && !chude.getTenchude().equals("")) {
            setValueInView(chude.getTenchude(), chude.getHinhchude());
            GetDataChuDe(chude.getIdkeychude());
        }
        if (album != null && !album.getTenAlbum().equals("")) {
            setValueInView(album.getTenAlbum(), album.getHinhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }
    }

    private void GetDataChuDe(String idkeychude) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhSachChuDe(idkeychude);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arraybaihat = (ArrayList<BaiHat>) response.body();
                dsBaiHatAdapter.submitList(arraybaihat);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataAlbum(String idAlbum) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhSachBaiHatTheoAlbum(idAlbum);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arraybaihat = (ArrayList<BaiHat>) response.body();
                dsBaiHatAdapter.submitList(arraybaihat);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataTheLoai(String idtheloai) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhSachBaiHatTheoTheLoai(idtheloai);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arraybaihat = (ArrayList<BaiHat>) response.body();
                dsBaiHatAdapter.submitList(arraybaihat);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void initRecycerView() {
        dsBaiHatAdapter = new DSBaiHatAdapter(this);
        recyclerView.setAdapter(dsBaiHatAdapter);
    }

    private void GetDataPlayList(String idPlayList) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhSachbaihatplaylist(idPlayList);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arraybaihat = (ArrayList<BaiHat>) response.body();
                dsBaiHatAdapter.submitList(arraybaihat);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void GetDataQuangCao(String idquangcao) {
        Dataservice dataservice = APIService.getService();
        Call<List<BaiHat>> callback = dataservice.GetDanhSachbaihatquangcao(idquangcao);
        callback.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                arraybaihat = (ArrayList<BaiHat>) response.body();
                dsBaiHatAdapter.submitList(arraybaihat);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                collapsingToolbarLayout.setBackground(bitmapDrawable);
                imgDSbaihat.setImageBitmap(bitmap);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void AnhXa() {
        coordinatorLayout = findViewById(R.id.coordinator);
        collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar);
        recyclerView = findViewById(R.id.recycler_ds_baihat);
        floatingActionButton = findViewById(R.id.floatingactionbutton);
        imgDSbaihat = findViewById(R.id.img_ds_cakhuc);
        toolbar = findViewById(R.id.toolbardsbaihat);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        floatingActionButton.setEnabled(false);
    }

    @Override
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

    private void Dataintent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("banner")) {
                quangCao = (QuangCao) intent.getSerializableExtra("banner");
            }
            if (intent.hasExtra("itemplaylist")) {
                playList = (PlayList) intent.getSerializableExtra("itemplaylist");
            }
            if (intent.hasExtra("idtheloai")) {
                theLoai = (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if (intent.hasExtra("idchude")) {
                chude = (ChuDe) intent.getSerializableExtra("idchude");
            }
            if (intent.hasExtra("idalbum")) {
                album = (Album) intent.getSerializableExtra("idalbum");
            }
        }
    }
    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachBaiHatActivity.this,PlayNhacActivity.class);
                intent.putExtra("cacbaihat",arraybaihat);
                startActivity(intent);
            }
        });

    }
}