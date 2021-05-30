package com.example.music.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Activity.DanhSachBaiHatActivity;
import com.example.music.Model.PlayList;
import com.example.music.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.ViewHolder>   {
    ArrayList<PlayList> arrayList;

    public PlayListAdapter(ArrayList<PlayList> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_playlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayListAdapter.ViewHolder holder, int position) {
        PlayList playList = arrayList.get(position);
        holder.textViewPlayList.setText(playList.getTen());
        Picasso.with(holder.itemView.getContext()).load(playList.getHinhPlayList()).into(holder.imgBackground);
        Picasso.with(holder.itemView.getContext()).load(playList.getIcon()).into(holder.imgPlayList);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(holder.itemView.getContext(), DanhSachBaiHatActivity.class);
            intent.putExtra("itemplaylist",arrayList.get(position));
            holder.itemView.getContext().startActivity(intent);
        });

    }
    public void subList(ArrayList<PlayList> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(arrayList == null) return 0;
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewPlayList;
        ImageView imgBackground,imgPlayList;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewPlayList = itemView.findViewById(R.id.text_view_playlist);
            imgBackground = itemView.findViewById(R.id.img_background_playlist);
            imgPlayList = itemView.findViewById(R.id.img_playlist);
        }
    }


//    public PlayListAdapter(@NonNull Context context, int resource, @NonNull List<PlayList> objects) {
//        super(context, resource, objects);
//    }
//
//    class ViewHolder{
//        TextView textViewPlaylist;
//        ImageView imgbackground,imgPlaylist;
//    }
//
//    @NonNull
//    @Override
//    //dung de gan layout trong phan item vao trong list
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        ViewHolder viewHolder = null;
//        if(convertView == null){
//            LayoutInflater inflater = LayoutInflater.from(getContext());
//            convertView = inflater.inflate(R.layout.dong_playlist,null);
//            viewHolder = new ViewHolder();
//            viewHolder.textViewPlaylist = convertView.findViewById(R.id.text_view_playlist);
//            viewHolder.imgbackground = convertView.findViewById(R.id.img_background_playlist);
//            viewHolder.imgPlaylist = convertView.findViewById(R.id.img_playlist);
//            convertView.setTag(viewHolder);
//        }else{
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//        PlayList playList = getItem(position);
//        Picasso.with(getContext()).load(playList.getHinhPlayList()).into(viewHolder.imgbackground);
//        Picasso.with(getContext()).load(playList.getIcon()).into(viewHolder.imgPlaylist);
//        viewHolder.textViewPlaylist.setText(playList.getTen());
//        return convertView;
//    }
}
