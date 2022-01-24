package com.example.visitapp.database.db;

import android.os.Build;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.visitapp.database.dao.UlasanDao;
import com.example.visitapp.database.dao.WisataDao;
import com.example.visitapp.database.entity.Ulasan;
import com.example.visitapp.database.entity.Wisata;

@Database(version = 2, entities = {Wisata.class, Ulasan.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract WisataDao userDao();

    public abstract UlasanDao ulasDao();
}
