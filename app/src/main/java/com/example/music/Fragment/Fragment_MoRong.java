package com.example.music.Fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.music.R;

public class Fragment_MoRong extends Fragment {
    View view ;
    TextView textViewSleepTimer,textViewCountry,textViewLanguage,textViewColor;
    Button btnBuy;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_morong,container,false);
        init();

        btnBuy.setOnClickListener(v -> {
            Buy();
        });
        return  view;
    }

    private void Buy() {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("Thong Bao");
            builder.setMessage("Ban co muon mua k ?");
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    btnBuy.setVisibility(View.INVISIBLE);


                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
    }

    private void init() {
        textViewColor = view.findViewById(R.id.musiccolor);
        textViewSleepTimer = view.findViewById(R.id.sleeptimer);
        textViewCountry = view.findViewById(R.id.Country);
        textViewLanguage = view.findViewById(R.id.Language);
        btnBuy = view.findViewById(R.id.btn);
    }
}
