package com.example.music.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Model.BaiHat;
import com.example.music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.ViewHolder>{
    ArrayList<BaiHat> baiHatArrayList;

    public PlayNhacAdapter(ArrayList<BaiHat> baiHatArrayList) {
        this.baiHatArrayList = baiHatArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_play_ds_baihat,parent,false);
        return new ViewHolder(view);
    }

    public void submitList(ArrayList<BaiHat> baiHats){
        this.baiHatArrayList = baiHats;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull PlayNhacAdapter.ViewHolder holder, int position) {
        BaiHat baiHat = baiHatArrayList.get(position);
        holder.textViewTenCaSi.setText(baiHat.getCaSi());
        holder.textViewTenBaiHat.setText(baiHat.getTenBaiHat());
        holder.textViewIndex.setText(position+1 +"");
        Picasso.with(holder.itemView.getContext()).load(baiHat.getHinhBaiHat()).into(holder.imgHinh);
    }

    @Override
    public int getItemCount() {
        if(baiHatArrayList ==null) return 0;
        else return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewIndex,textViewTenCaSi,textViewTenBaiHat;
        ImageView imgHinh;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewIndex =itemView.findViewById(R.id.text_view_index);
            textViewTenBaiHat =itemView.findViewById(R.id.text_view_play_tenbaihat);
            textViewTenCaSi =itemView.findViewById(R.id.text_view_play_casi_baihat);
            imgHinh = itemView.findViewById(R.id.img_play_bai_hat);
        }
    }
}
