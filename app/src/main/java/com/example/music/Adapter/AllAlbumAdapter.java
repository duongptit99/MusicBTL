package com.example.music.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Activity.DanhSachBaiHatActivity;
import com.example.music.Model.Album;
import com.example.music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class AllAlbumAdapter extends RecyclerView.Adapter<AllAlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> albumArrayList;

    public AllAlbumAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_all_album,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AllAlbumAdapter.ViewHolder holder, int position) {
        Album album =albumArrayList.get(position);
        Picasso.with(holder.itemView.getContext()).load(album.getHinhAlbum()).into(holder.imgAllAlbum);
        holder.textViewTenAlbum.setText(album.getTenAlbum());
        holder.imgAllAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("idalbum",albumArrayList.get(position));
                context.startActivity(intent);
            }
        });


    }
    public void submitList(ArrayList<Album> albums){
        this.albumArrayList = albums;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        if (albumArrayList == null) return 0;
        else return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgAllAlbum;
        TextView textViewTenAlbum;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAllAlbum = itemView.findViewById(R.id.imgallalbum);
            textViewTenAlbum = itemView.findViewById(R.id.text_view_allalbum);
        }
    }
}
