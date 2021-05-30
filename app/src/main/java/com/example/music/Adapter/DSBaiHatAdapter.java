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

import com.example.music.Activity.DanhSachBaiHatActivity;
import com.example.music.Activity.PlayNhacActivity;
import com.example.music.Model.BaiHat;
import com.example.music.R;
import com.example.music.Service.APIService;
import com.example.music.Service.Dataservice;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DSBaiHatAdapter extends RecyclerView.Adapter<DSBaiHatAdapter.ViewHolder> {
    Context context;
    ArrayList<BaiHat> baiHatArrayList;


    public DSBaiHatAdapter(Context context) {
        this.context = context;
    }

    public void submitList(ArrayList<BaiHat> baiHats) {
        this.baiHatArrayList = baiHats;
        notifyDataSetChanged();
    }

    @NonNull
    @Override

    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_ds_bai_hat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BaiHat baiHat = baiHatArrayList.get(position);
        holder.txtdsbaihat.setText(baiHat.getTenBaiHat());
        holder.txtdscasi.setText(baiHat.getCaSi());
        holder.txtindex.setText(position + 1 + "");
        holder.imglike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.imglike.setImageResource(R.drawable.iconloved);
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
                holder.imglike.setEnabled(false);
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
        TextView txtindex, txtdscasi, txtdsbaihat;
        ImageView imglike;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtindex = itemView.findViewById(R.id.text_view_index);
            txtdscasi = itemView.findViewById(R.id.text_view_ds_tencasi);
            txtdsbaihat = itemView.findViewById(R.id.text_view_ds_tenbaihat);
            imglike = itemView.findViewById(R.id.imglike);

        }
    }
}
