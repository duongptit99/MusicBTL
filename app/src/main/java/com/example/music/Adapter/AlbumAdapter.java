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

import com.example.music.Activity.DanhSachAlbumActivity;
import com.example.music.Activity.DanhSachBaiHatActivity;
import com.example.music.Model.Album;
import com.example.music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    Context context;
    ArrayList<Album> albumArrayList;

    public AlbumAdapter(Context context) {
        this.context = context;
    }

    public void submitList(ArrayList<Album> albumArrayList) {
        this.albumArrayList = albumArrayList;
        notifyDataSetChanged();
    }

    public AlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumArrayList.get(position);
        holder.textViewTenAlbum.setText(album.getTenAlbum());
        holder.textViewCaSiAlbum.setText(album.getTenCaSiAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgHinhAlbum);
        holder.imgHinhAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), DanhSachBaiHatActivity.class);
                intent.putExtra("idalbum",albumArrayList.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(albumArrayList == null) return 0;
        return albumArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHinhAlbum;
        TextView textViewTenAlbum,textViewCaSiAlbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhAlbum = itemView.findViewById(R.id.img_album);
            textViewTenAlbum = itemView.findViewById(R.id.text_view_ten_album);
            textViewCaSiAlbum = itemView.findViewById(R.id.text_view_ten_casi_album);
        }
    }
}
