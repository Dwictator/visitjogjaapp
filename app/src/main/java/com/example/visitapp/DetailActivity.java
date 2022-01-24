package com.example.visitapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.visitapp.adapter.UlasanViewAdapter;
import com.example.visitapp.adapter.ulasanDataViewAdapter;
import com.example.visitapp.database.dao.WisataDao;
import com.example.visitapp.database.db.MyApp;
import com.example.visitapp.database.entity.Ulasan;
import com.example.visitapp.database.entity.Wisata;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    TextView judul, lokasi, deskripsi, foto, maps;
    ImageView fotoWisata, ivUlasan, foto1, foto2, ivFav;
    LinearLayout ivMaps;
    public final static String TAG_DATA_INTENT = "data_wisata";
    private WisataDao dao;
    Wisata wisata;


    RecyclerView myRecycleview;
    UlasanViewAdapter recyclerAdapter;
    List<Ulasan> ulasanList = new ArrayList<>();
    private UlasanViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dao = MyApp.getInstance().getDatabase().userDao();

        if (getIntent() != null) {
            int id = getIntent().getIntExtra(TAG_DATA_INTENT, 0);
            wisata = dao.findById(id);
        }

        fotoWisata = findViewById(R.id.fotoWisata);
        judul = findViewById(R.id.judul);
        lokasi = findViewById(R.id.lokasi);
        deskripsi = findViewById(R.id.deskripsi);
        foto1 = findViewById(R.id.foto1);
        foto2 = findViewById(R.id.foto2);
        ivUlasan = findViewById(R.id.ivUlasan);
        ivFav = findViewById(R.id.ivFav);
        ivMaps = findViewById(R.id.ivMaps);

        String namaImage =(wisata.getFoto()) ;
        String uri = "@drawable/"+namaImage;  // where myresource (without the extension) is the file
        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        fotoWisata.setImageDrawable(res);
        foto1.setImageDrawable(res);
        foto2.setImageDrawable(res);



        judul.setText(wisata.getJudul());
        lokasi.setText(wisata.getLokasi());
        deskripsi.setText(wisata.getDeskripsi());

        int i = wisata.getId();
        String idWisata = String.valueOf(i);
        myRecycleview =(RecyclerView)findViewById(R.id.myRecyclerview);

        adapter = new UlasanViewAdapter();
        myRecycleview.setAdapter(adapter);
        super.onResume();
        List<Ulasan> datas = MyApp.getInstance().getDatabase().ulasDao().getUlasan(idWisata);
        adapter.setData(datas);


        ivUlasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("idWisata", wisata.getId());
                Intent i = new Intent(DetailActivity.this, UlasanActivity.class);
                i.putExtras(bundle);
                startActivity(i);
            }
        });

        ivFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "Fitur Ini Masih Dalam Pengembangan", Toast.LENGTH_SHORT).show();
            }
        });

        ivMaps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "Fitur Ini Masih Dalam Pengembangan", Toast.LENGTH_SHORT).show();
            }
        });

    }
}