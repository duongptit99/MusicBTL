package com.example.music.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.music.Activity.PlayNhacActivity;
import com.example.music.Model.BaiHat;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BaiHatAdapter extends RecyclerView.Adapter<BaiHatAdapter.ViewHolder>{
    Context context;
    ArrayList<BaiHat> baiHatArrayList;

    public BaiHatAdapter(Context context) {
        this.context = context;
    }

    public void submitList(ArrayList<BaiHat> baiHatArrayList){
        this.baiHatArrayList = baiHatArrayList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_baihat,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = baiHatArrayList.get(position);
        holder.textViewBaiHat.setText(baiHat.getTenBaiHat());
        holder.textViewCaSi.setText(baiHat.getCaSi());
        Picasso.with(context).load(baiHat.getHinhBaiHat()).into(holder.imgHinh);
        holder.imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imgLike.setImageResource(R.drawable.iconloved);
                Dataservice dataservice = APIService.getService();
                Call<String> callback = dataservice.UpdateLuotThich("1",baiHatArrayList.get(holder.getPosition()).getIdBaiHat());
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String kq = response.body();
                        if(kq.equals("Success")){
                            Toast.makeText(context, "Like", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Unlike", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
                holder.imgLike.setEnabled(false);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), PlayNhacActivity.class);
                intent.putExtra("cakhuc",baiHatArrayList.get(holder.getPosition()));
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (baiHatArrayList == null) return 0;
        else return baiHatArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewBaiHat,textViewCaSi,textViewMore;
        ImageView imgHinh,imgLike;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewBaiHat = itemView.findViewById(R.id.text_view_tenbaihat);
            textViewCaSi = itemView.findViewById(R.id.text_view_casi_baihat);
            imgHinh = itemView.findViewById(R.id.img_bai_hat_hot);
            imgLike= itemView.findViewById(R.id.icon_love);
        }
    }
}
