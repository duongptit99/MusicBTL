package com.example.music.Adapter;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Activity.DanhSachBaiHatActivity;
import com.example.music.Model.Album;
import com.example.music.Model.ChuDe;
import com.example.music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChuDeAdapter extends RecyclerView.Adapter<ChuDeAdapter.ViewHolder>{
    ArrayList<ChuDe> chuDeArrayList;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_chude,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChuDeAdapter.ViewHolder holder, int position) {
        ChuDe chude = chuDeArrayList.get(position);
        Picasso.with(holder.itemView.getContext()).load(chude.getHinhchude()).into(holder.imgChude);
        holder.imgChude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DanhSachBaiHatActivity.class);
                intent.putExtra("idchude",chuDeArrayList.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(chuDeArrayList == null) return 0;
        return chuDeArrayList.size();
    }
    public void submitList(ArrayList<ChuDe> chuDeArrayList) {
        this.chuDeArrayList = chuDeArrayList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgChude;
        public ViewHolder(View itemView) {
            super(itemView);
            imgChude = itemView.findViewById(R.id.img_chude);
        }
    }
}
