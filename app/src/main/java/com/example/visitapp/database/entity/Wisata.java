package com.example.visitapp.database.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Wisata {
    @PrimaryKey(autoGenerate = true)
    private int id = 0 ;

    @ColumnInfo(name = "judul")
    private String judul ="";

    @ColumnInfo(name = "lokasi")
    private String lokasi ="";

    @ColumnInfo(name = "deskripsi")
    private String deskripsi ="";

    @ColumnInfo(name = "foto")
    private String foto ="";

    @ColumnInfo(name = "maps")
    private String maps ="";

    @ColumnInfo(name = "kategori")
    private String kategori ="";

    ///////////

    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id =id;
    }

    public String getJudul() {
        return judul;
    }
    public void setJudul(String judul) {
        this.judul =judul;
    }

    public String getLokasi() {
        return lokasi;
    }
    public void setLokasi(String lokasi) {
        this.lokasi =lokasi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }
    public void setDeskripsi(String deskripsi) {
        this.deskripsi =deskripsi;
    }

    public String getFoto() {
        return foto;
    }
    public void setFoto(String foto) {
        this.foto =foto;
    }

    public String getMaps() {
        return maps;
    }
    public void setMaps(String maps) {
        this.maps =maps;
    }

    public String getKategori() { return kategori;  }
    public void setKategori(String kategori) {        this.kategori =kategori;    }

}


