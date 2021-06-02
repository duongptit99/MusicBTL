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
import com.example.music.Model.PlayList;
import com.example.music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DSPlayListAdapter extends RecyclerView.Adapter<DSPlayListAdapter.ViewHolder>{
    Context context;
    ArrayList<PlayList> playListArrayList;

    public DSPlayListAdapter(Context context) {
        this.context = context;
    }


    public void submitList(ArrayList<PlayList> playLists) {
        this.playListArrayList = playLists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.dong_ds_playlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayList playList = playListArrayList.get(position);
        Picasso.with(context).load(playList.getHinhPlayList()).into(holder.imgdsplaylist);
        holder.textViewdsplaylist.setText(playList.getTen());
    }

    @Override
    public int getItemCount() {
        if (playListArrayList == null) return 0;
        else return playListArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgdsplaylist;
        TextView textViewdsplaylist,textViewdscasiplaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgdsplaylist = itemView.findViewById(R.id.img_background_ds_playlist);
            textViewdsplaylist = itemView.findViewById(R.id.text_view_dsplaylist);
            imgdsplaylist.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemplaylist",playListArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
