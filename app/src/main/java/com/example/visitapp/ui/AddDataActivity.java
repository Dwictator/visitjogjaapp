package com.example.visitapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import static com.example.visitapp.database.db.MyApp.db;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.visitapp.R;
import com.example.visitapp.database.dao.WisataDao;
import com.example.visitapp.database.db.MyApp;
import com.example.visitapp.database.entity.Wisata;

public class AddDataActivity extends AppCompatActivity implements View.OnClickListener {
    public final static String TAG_DATA_INTENT = "data_wisata";
    EditText etJudul, etLokasi, etDeskripsi, etFoto, etMaps;
    Wisata wisata;
    Spinner Kategori;
    Button btnTambah;
    private WisataDao dao;

    @SuppressLint("setTextll8n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
        dao = MyApp.getInstance().getDatabase().userDao();

        if (getIntent() != null) {
            int id = getIntent().getIntExtra(TAG_DATA_INTENT, 0);
            wisata = dao.findById(id);
        }

        btnTambah = findViewById(R.id.btnSave);
        etJudul = findViewById(R.id.etJudul);
        etLokasi = findViewById(R.id.etLokasi);
        etDeskripsi = findViewById(R.id.etDeskripsi);
        etFoto = findViewById(R.id.etFoto);
        Kategori = findViewById(R.id.Kategori);

        if (wisata != null){
            etJudul.setText(wisata.getJudul());
            etLokasi.setText(wisata.getLokasi());
            etDeskripsi.setText(wisata.getDeskripsi());
            etFoto.setText(wisata.getFoto());
            btnTambah.setText("Update Data");
        }
        btnTambah.setOnClickListener(this);
    }

//    private void TambahData() {
//        if (!etJudul.getText().toString().isEmpty() && !etLokasi.getText().toString().isEmpty()){
//
//            wisata = new Wisata();
//            wisata.setJudul(etJudul.getText().toString());
//            wisata.setLokasi(etLokasi.getText().toString());
//            wisata.setDeskripsi(etDeskripsi.getText().toString());
//            wisata.setFoto(etFoto.getText().toString());
//            wisata.setMaps(etMaps.getText().toString());
//            //insert data di database
//            db.wstDao().insertAll(wisata);
//
//            startActivity(new Intent(AddDataActivity.this, ViewDataActivity.class));
//            Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(this, "Mohon masukkan data dengan lengkap", Toast.LENGTH_SHORT).show();
//        }
//    }

    @Override
    public void onClick(View view){
        addOrUpdate();
        if (wisata.getId() == 0){
            dao.insertAll(wisata);
        }else{
            dao.update(wisata);
        }
        Toast.makeText(this, btnTambah.getText().toString(), Toast.LENGTH_SHORT).show();
        finish();
    }

    private void addOrUpdate(){
        if (wisata == null ){
            wisata = new Wisata();
        }
        wisata.setJudul(etJudul.getText().toString());
        wisata.setLokasi(etLokasi.getText().toString());
        wisata.setDeskripsi(etDeskripsi.getText().toString());
        wisata.setFoto(etFoto.getText().toString());
        wisata.setKategori(Kategori.getItemAtPosition(Kategori.getSelectedItemPosition()).toString());
    }
}