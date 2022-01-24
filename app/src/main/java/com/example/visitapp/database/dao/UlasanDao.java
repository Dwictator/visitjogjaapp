package com.example.visitapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.visitapp.database.entity.Ulasan;

import java.util.List;

@Dao
public interface UlasanDao {

    @Query("SELECT * FROM ulasan")
    List<Ulasan> getAll();

    @Insert
    void insertAll(Ulasan ulasan);

    @Query("SELECT * FROM ulasan WHERE id LIKE :ulasanId LIMIT 1")
    Ulasan findById(int ulasanId);

    @Query("SELECT * FROM ulasan WHERE id_wisata LIKE :id_wisata LIMIT 3")
    List<Ulasan> getUlasan(String id_wisata);

    @Query("SELECT * FROM ulasan WHERE id_user LIKE :id_user")
    List<Ulasan> getUserulasan(String id_user);

    @Update
    void update(Ulasan ulasan);

    @Delete
    void delete(Ulasan ulasan);
}