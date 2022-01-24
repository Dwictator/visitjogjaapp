package com.example.visitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.visitapp.adapter.RecyclerViewAdapter;
import com.example.visitapp.database.db.MyApp;
import com.example.visitapp.database.entity.Wisata;

import java.util.ArrayList;
import java.util.List;

public class KategoriActivity extends AppCompatActivity {
    TextView tvKategori;
    RecyclerView myRecycleview;
    RecyclerViewAdapter recyclerAdapter;
    List<Wisata> listWisata = new ArrayList<>();
    public final static String TAG_DATA_INTENT = "data_wisata";
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kategori);
        String kategori = getIntent().getStringExtra("data_wisata");
        myRecycleview =findViewById(R.id.myRecyclerview);

        tvKategori = findViewById(R.id.tvKategori);
        tvKategori.setText(kategori);


        adapter = new RecyclerViewAdapter();
        myRecycleview.setAdapter(adapter);
        super.onResume();
        List<Wisata> datas = MyApp.getInstance().getDatabase().userDao().getPantai(kategori);
        adapter.setData(datas);
    }
}