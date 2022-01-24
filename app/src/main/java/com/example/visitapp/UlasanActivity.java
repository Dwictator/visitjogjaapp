package com.example.visitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.visitapp.database.dao.UlasanDao;
import com.example.visitapp.database.dao.WisataDao;
import com.example.visitapp.database.db.MyApp;
import com.example.visitapp.database.entity.Ulasan;
import com.example.visitapp.database.entity.Wisata;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UlasanActivity extends AppCompatActivity {
    TextView judul, lokasi, idWisata;
    EditText  idUser, review;
    RatingBar rateWisata;
    ImageView fotoWisata, ivMaps, fotoProfil;
    public final static String TAG_DATA_INTENT = "data_wisata";
    private WisataDao dao;
    private UlasanDao daou;
    Ulasan ulasan;
    Wisata wisata;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ulasan);

        SharedPrefManager sharedPrefManager = new SharedPrefManager(this);
        dao = MyApp.getInstance().getDatabase().userDao();
        daou = MyApp.getInstance().getDatabase().ulasDao();
        fotoWisata = findViewById(R.id.fotoWisata);
        judul = findViewById(R.id.judul);
        lokasi = findViewById(R.id.lokasi);
        review = findViewById(R.id.review);
        rateWisata = findViewById(R.id.rate);
        btnSubmit = findViewById(R.id.btnSubmit);
        idWisata = findViewById(R.id.idWisata);
        idUser = findViewById(R.id.idUser);
        fotoProfil = findViewById(R.id.fotoProfil);

        Bundle bundle = getIntent().getExtras();
        Integer getIdWisata = bundle.getInt("idWisata");

        wisata = dao.findById(getIdWisata);


        String namaImage =(wisata.getFoto()) ;
        String uri = "@drawable/"+namaImage;  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getPackageName());
        Drawable res = getResources().getDrawable(imageResource);
        fotoWisata.setImageDrawable(res);

        Integer idw =(wisata.getId()) ;
        judul.setText(wisata.getJudul());
        lokasi.setText(wisata.getLokasi());
        idWisata.setText(""+idw);
        idUser.setText(sharedPrefManager.getUsername());
        idWisata.setVisibility(View.INVISIBLE);
        idUser.setVisibility(View.INVISIBLE);


        String namaGambar =(sharedPrefManager.getFoto()) ;
        String uril = "@drawable/"+namaGambar;  // where myresource (without the extension) is the file

        int imageRes = getResources().getIdentifier(uril, null, getPackageName());
        Drawable res1 = getResources().getDrawable(imageRes);
        fotoProfil.setImageDrawable(res1);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String numberOfStars = String.valueOf(rateWisata.getRating());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
                String currentDateandTime = sdf.format(new Date());
                ulasan = new Ulasan();
                ulasan.setIdwisata(idWisata.getText().toString());
                ulasan.setReview(review.getText().toString());
                ulasan.setIduser(idUser.getText().toString());
                ulasan.setTanggal(currentDateandTime);
                ulasan.setBintang(numberOfStars);
                daou.insertAll(ulasan);
                Toast.makeText(UlasanActivity.this, "Berhasil Menulis Ulasan", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UlasanActivity.this, MainActivity.class);
                finishAffinity();
                startActivity(intent);
            }
        });

    }

}