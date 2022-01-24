package com.example.visitapp;

import static com.example.visitapp.database.db.MyApp.db;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.visitapp.adapter.RecyclerViewAdapter;
import com.example.visitapp.common.DataListListener;
import com.example.visitapp.database.db.AppDatabase;
import com.example.visitapp.database.db.MyApp;
import com.example.visitapp.database.entity.Wisata;
import com.example.visitapp.ui.AddDataActivity;
import com.example.visitapp.ui.ViewDataActivity;

import java.util.ArrayList;
import java.util.List;


public class WisataFragment extends Fragment {
    RecyclerView myRecycleview;
    RecyclerViewAdapter recyclerAdapter;
    List<Wisata> listWisata = new ArrayList<>();
    private RecyclerViewAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_wisata,container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        myRecycleview =(RecyclerView)getView().findViewById(R.id.myRecyclerview);

        adapter = new RecyclerViewAdapter();
        myRecycleview.setAdapter(adapter);
        super.onResume();
        List<Wisata> datas = MyApp.getInstance().getDatabase().userDao().getAll();
        adapter.setData(datas);
    }

}