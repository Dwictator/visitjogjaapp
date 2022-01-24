package com.example.visitapp.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.visitapp.database.entity.Wisata;

import java.util.List;

@Dao
public interface WisataDao {

    @Query("SELECT * FROM wisata")
    List<Wisata> getAll();

    @Insert
    void insertAll(Wisata wisata);

//  SELECT * FROM expense INNER JOIN refuel
//ON exp_id = expense_id
//WHERE refuel_id = 1

//    @Query("SELECT * FROM wisata INNER JOIN ulasan ON id = id_wisata WHERE SUM(bintang) LIMIT 2")
    @Query("SELECT * FROM wisata LIMIT 2")
    List<Wisata> getWisataPopuler();

    @Query("SELECT * FROM wisata WHERE id LIKE :wisataId LIMIT 1")
    Wisata findById(int wisataId);

    @Query("SELECT * FROM wisata WHERE judul LIKE :judul LIMIT 3")
    List<Wisata> getWisata(String judul);

    @Query("SELECT * FROM wisata WHERE kategori LIKE :kategori")
    List<Wisata> getPantai(String kategori);

    @Update
    void update(Wisata wisata);

    @Delete
    void delete(Wisata wisata);
}
