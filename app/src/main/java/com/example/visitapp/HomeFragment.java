package com.example.visitapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.visitapp.adapter.RecyclerViewAdapter;
import com.example.visitapp.adapter.ulasanDataViewAdapter;
import com.example.visitapp.database.db.MyApp;
import com.example.visitapp.database.entity.Ulasan;
import com.example.visitapp.database.entity.Wisata;
import com.example.visitapp.ui.ViewDataActivity;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {
    public final static String TAG_DATA_INTENT = "data_wisata";

    RecyclerView myRecycleview, searchRecyclerview;
    RecyclerViewAdapter recyclerAdapter;
    List<Wisata> listWisata = new ArrayList<>();
    private RecyclerViewAdapter adapter, adapter1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_home,container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ImageView ivPantai = (ImageView) getView().findViewById(R.id.ivPantai);
        ImageView ivAir = (ImageView) getView().findViewById(R.id.ivAir);
        ImageView ivMuseum = (ImageView) getView().findViewById(R.id.ivMuseum);
        ImageView ivCandi = (ImageView) getView().findViewById(R.id.ivCandi);



        Button search = (Button) getView().findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_res();
            }

            private void search_res() {
                searchRecyclerview =(RecyclerView)getView().findViewById(R.id.searchRecyclerview);
                EditText searchtext = (EditText) getView().findViewById(R.id.searchtext);
                String searchkey =searchtext.getText().toString();
                String seac = "%" +searchkey+ "%";
                adapter = new RecyclerViewAdapter();
                searchRecyclerview.setAdapter(adapter);
                HomeFragment.super.onResume();
                List<Wisata> dataa = MyApp.getInstance().getDatabase().userDao().getWisata(seac);
                adapter.setData(dataa);
            }
        });

        ivPantai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kat = "pantai";
                Intent intent = new Intent(getActivity(), KategoriActivity.class);
                intent.putExtra(DetailActivity.TAG_DATA_INTENT, kat);
                startActivity(intent);
            }
        });

        ivAir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kat = "air terjun";
                Intent intent = new Intent(getActivity(), KategoriActivity.class);
                intent.putExtra(DetailActivity.TAG_DATA_INTENT, kat);
                startActivity(intent);
            }
        });

        ivMuseum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kat = "museum";
                Intent intent = new Intent(getActivity(), KategoriActivity.class);
                intent.putExtra(DetailActivity.TAG_DATA_INTENT, kat);
                startActivity(intent);
            }
        });

        ivCandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kat = "candi";
                Intent intent = new Intent(getActivity(), KategoriActivity.class);
                intent.putExtra(DetailActivity.TAG_DATA_INTENT, kat);
                startActivity(intent);
            }
        });


        myRecycleview =(RecyclerView)getView().findViewById(R.id.myRecyclerview);

        adapter = new RecyclerViewAdapter();
        myRecycleview.setAdapter(adapter);
        super.onResume();
        List<Wisata> datas = MyApp.getInstance().getDatabase().userDao().getWisataPopuler();
        adapter.setData(datas);

    }


}