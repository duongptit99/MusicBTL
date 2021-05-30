package com.example.music.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.StrictMode;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.music.Adapter.ViewPagerPLayList;
import com.example.music.Fragment.Fragment_Dia_Nhac;
import com.example.music.Fragment.Fragment_Play_DS_BaiHat;
import com.example.music.Model.BaiHat;
import com.example.music.R;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlayNhacActivity extends AppCompatActivity {
    TextView textViewTimeStart, textViewTimeEnd,textViewTenBaiHat,textViewTenCaSi;
    SeekBar seekBar;
    ImageButton imgPreview, imgPlay, imgNext, imgRepeat, imgSuffle;
    ViewPager viewPager;
    public static ArrayList<BaiHat> baiHatArrayList = new ArrayList<>();
    public static ViewPagerPLayList viewPagerPLayList;
    Fragment_Dia_Nhac fragment_dia_nhac;
    Fragment_Play_DS_BaiHat fragment_play_ds_baiHat;
    private MediaPlayer mediaPlayer;
    androidx.appcompat.widget.Toolbar toolbarplaynhac;
    int position = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_nhac);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetData();
        AnhXa();
        eventClick();

    }

    private void eventClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(baiHatArrayList.size() >0){
                    fragment_dia_nhac.Playnhac(baiHatArrayList.get(0).getHinhBaiHat());
                    handler.removeCallbacks(this);
                }else {
                    handler.postDelayed(this,300);
                }
            }
        },500);
        imgPlay.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                imgPlay.setImageResource(R.drawable.iconplay);
                if (fragment_dia_nhac.objectAnimator != null) {
                    fragment_dia_nhac.objectAnimator.pause();
                }
            } else {
                mediaPlayer.start();
                imgPlay.setImageResource(R.drawable.ic_baseline_pause_24);
                if (fragment_dia_nhac.objectAnimator != null) {
                    fragment_dia_nhac.objectAnimator.resume();
                }
            }
        });
        imgRepeat.setOnClickListener(v -> {
            if(repeat == false){
                if(checkrandom == true ){
                    checkrandom = false;
                    imgRepeat.setImageResource(R.drawable.iconsyned);
                    imgSuffle.setImageResource(R.drawable.iconsuffle);
                }
                imgRepeat.setImageResource(R.drawable.iconsyned);
                repeat = true ;
            }else {
                imgRepeat.setImageResource(R.drawable.iconrepeat);
                repeat = false;
            }
        });
        imgSuffle.setOnClickListener(v -> {
            if(checkrandom == false){
                if(repeat == true ){
                    repeat = false;
                    imgSuffle.setImageResource(R.drawable.iconshuffled);
                    imgRepeat.setImageResource(R.drawable.iconrepeat);
                }
                imgSuffle.setImageResource(R.drawable.iconshuffled);
                checkrandom = true ;
            }else {
                imgSuffle.setImageResource(R.drawable.iconsuffle);
                checkrandom = false;
            }
        });
        imgNext.setOnClickListener(v -> {
            if(baiHatArrayList.size() > 0){
                if(mediaPlayer.isPlaying() || mediaPlayer != null){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null ;
                }
                if(position < (baiHatArrayList.size())){
                    imgPlay.setImageResource(R.drawable.ic_baseline_pause_24);
                    position++;
                    if(repeat == true){
                        if(position == 0) {
                            position = baiHatArrayList.size();
                        }
                        position -= 1;
                    }
                    if(checkrandom == true){
                        Random random = new Random();
                        int index = random.nextInt(baiHatArrayList.size());
                        if(index == position){
                            position = index - 1;
                        }
                        position = index;
                    }
                    if(position > (baiHatArrayList.size() - 1)){
                        position = 0;
                    }
                    fragment_dia_nhac.Playnhac(baiHatArrayList.get(position).getHinhBaiHat());
                    new PLayMp3().execute(baiHatArrayList.get(position).getLinkBaiHat());
                    getSupportActionBar().setTitle(baiHatArrayList.get(position).getTenBaiHat());
                    UpdateTime();
                }
            }
            imgPreview.setClickable(false);
            imgNext.setClickable(false);
            Handler handler1 = new Handler();
            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imgPreview.setClickable(true);
                    imgNext.setClickable(true);
                }
            },5000);
        });
        imgPreview.setOnClickListener(v -> {
            if(baiHatArrayList.size() > 0){
                if(mediaPlayer.isPlaying() || mediaPlayer != null){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null ;
                }
                if(position < (baiHatArrayList.size())){
                    imgPlay.setImageResource(R.drawable.ic_baseline_pause_24);
                    position--;
                    if(position < 0){
                        position = baiHatArrayList.size()-1;
                    }
                    if(repeat == true){
                        if(position == 0) {
                            position = baiHatArrayList.size();
                        }
                        position += 1;
                    }
                    if(checkrandom == true){
                        Random random = new Random();
                        int index = random.nextInt(baiHatArrayList.size());
                        if(index == position){
                            position = index - 1;
                        }
                        position = index;
                    }
                    new PLayMp3().execute(baiHatArrayList.get(position).getLinkBaiHat());
                    fragment_dia_nhac.Playnhac(baiHatArrayList.get(position).getHinhBaiHat());
                    getSupportActionBar().setTitle(baiHatArrayList.get(position).getTenBaiHat());
                    UpdateTime();
                }
            }
            imgPreview.setClickable(false);
            imgNext.setClickable(false);
            Handler handler1 = new Handler();
            handler1.postDelayed(new Runnable() {
                @Override
                public void run() {
                    imgPreview.setClickable(true);
                    imgNext.setClickable(true);
                }
            },5000);
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
    }

    private void GetData() {
        Intent intent = getIntent();
        baiHatArrayList.clear();
        if (intent != null) {
            if (intent.hasExtra("cakhuc")) {
                BaiHat baiHat = intent.getParcelableExtra("cakhuc");
                baiHatArrayList.add(baiHat);
            }
            if (intent.hasExtra("cacbaihat")) {
                ArrayList<BaiHat> baihats = intent.getParcelableArrayListExtra("cacbaihat");
                baiHatArrayList = baihats;
            }
        }
    }

    private void AnhXa() {
        textViewTimeStart = findViewById(R.id.text_view_timestartsong);
        textViewTimeEnd = findViewById(R.id.text_view_timeendsong);
        textViewTenBaiHat = findViewById(R.id.textViewtenbaihatplaynhac);
        textViewTenCaSi = findViewById(R.id.textViewtencasiplaynhac);
        imgNext = findViewById(R.id.next);
        imgPlay = findViewById(R.id.play);
        imgPreview = findViewById(R.id.preview);
        imgSuffle = findViewById(R.id.suffle);
        imgRepeat = findViewById(R.id.repeat);
        viewPager = findViewById(R.id.view_pager_nhac);
        seekBar = findViewById(R.id.seekbar);
        toolbarplaynhac = findViewById(R.id.toolbarplaynhac);
        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(v -> {
            finish();
            mediaPlayer.stop();
            baiHatArrayList.clear();
        });
        fragment_play_ds_baiHat = new Fragment_Play_DS_BaiHat();
        fragment_dia_nhac = new Fragment_Dia_Nhac();
        viewPagerPLayList = new ViewPagerPLayList(getSupportFragmentManager());
        viewPagerPLayList.AddFragment(fragment_play_ds_baiHat);
        viewPagerPLayList.AddFragment(fragment_dia_nhac);
        viewPager.setAdapter(viewPagerPLayList);
        fragment_dia_nhac = (Fragment_Dia_Nhac) viewPagerPLayList.getItem(1);
        if(baiHatArrayList.size() >0){
            getSupportActionBar().setTitle(baiHatArrayList.get(0).getTenBaiHat());
            new PLayMp3().execute(baiHatArrayList.get(0).getLinkBaiHat());
            imgPlay.setImageResource(R.drawable.ic_baseline_pause_24);
        }

    }


    class PLayMp3 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mediaPlayer.stop();
                        mediaPlayer.reset();
                    }
                });
                mediaPlayer.setDataSource(s);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }

    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        textViewTimeEnd.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        textViewTenBaiHat.setText(baiHatArrayList.get(position).getTenBaiHat());
        textViewTenCaSi.setText(baiHatArrayList.get(position).getCaSi());
        seekBar.setMax(mediaPlayer.getDuration());
    }

    private void UpdateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null){
                    seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    textViewTimeStart.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this, 300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }, 300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (next == true){
                    if (position < (baiHatArrayList.size())) {
                        position++;
                        if (repeat == true) {
                            position --;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(baiHatArrayList.size());
                            if (index == position) {
                                position = index - 1;
                            }
                            position = index;
                        }
                        if (position > baiHatArrayList.size() - 1) {
                            position = 0;
                        }
                        try {
                            fragment_dia_nhac.Playnhac(baiHatArrayList.get(position).getHinhBaiHat());
                            new PLayMp3().execute(baiHatArrayList.get(position).getLinkBaiHat());
                            getSupportActionBar().setTitle(baiHatArrayList.get(position).getTenBaiHat());
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    imgPreview.setClickable(false);
                    imgNext.setClickable(false);
                    Handler handler1 = new Handler();
                    handler1.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            imgPreview.setClickable(true);
                            imgNext.setClickable(true);
                        }
                    }, 3000);
                    next = false;
                    handler1.removeCallbacks(this);
                }else {
                    handler1.postDelayed(this, 1000);
                }
            }
        }, 1000);
    }
}
