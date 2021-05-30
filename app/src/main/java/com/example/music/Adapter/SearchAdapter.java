package com.example.music.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Activity.PlayNhacActivity;
import com.example.music.Model.BaiHat;
import com.example.music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    ArrayList<BaiHat> baiHatArrayList;


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_search,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchAdapter.ViewHolder holder, int position) {
        BaiHat baiHat = baiHatArrayList.get(position);
        holder.textViewTenBaiHat.setText(baiHat.getTenBaiHat());
        holder.textViewCaSi.setText(baiHat.getCaSi());
        Picasso.with(holder.itemView.getContext()).load(baiHat.getHinhBaiHat()).into(holder.imgHinh);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), PlayNhacActivity.class);
            intent.putExtra("cakhuc",baiHatArrayList.get(holder.getPosition()));
            holder.itemView.getContext().startActivity(intent);
        });
    }

    public void subList(ArrayList<BaiHat> arrayList){
        this.baiHatArrayList = arrayList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return baiHatArrayList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewTenBaiHat,textViewCaSi;
        ImageView imgHinh;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewTenBaiHat = itemView.findViewById(R.id.text_search_tenbaihat);
            textViewCaSi = itemView.findViewById(R.id.text_search_casi);
            imgHinh = itemView.findViewById(R.id.img_search_baihat);


        }
    }
}
