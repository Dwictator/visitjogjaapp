package com.example.visitapp.common;

import com.example.visitapp.database.entity.Ulasan;
import com.example.visitapp.database.entity.Wisata;

public interface DataListListener {
    void onRemoveClick(Wisata wisata);

    void onRemoveClick2(Ulasan ulasan);
}
