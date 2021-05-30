package com.example.music.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Activity.DanhSachBaiHatActivity;
import com.example.music.Model.ChuDevaTheLoai;
import com.example.music.Model.TheLoai;
import com.example.music.R;
import com.squareup.picasso.Picasso;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.ViewHolder> {
    private ChuDevaTheLoai data;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_the_loai, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TheLoaiAdapter.ViewHolder holder, int position) {
        TheLoai item = data.getTheLoai().get(position);
        Picasso.with(holder.itemView.getContext()).load(item.getHinhtheloai()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DanhSachBaiHatActivity.class);
                intent.putExtra("idtheloai",data.getTheLoai().get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (data != null && data.getTheLoai() != null) return data.getTheLoai().size();
        else return 0;
    }

    public void submitData(ChuDevaTheLoai data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }

}
