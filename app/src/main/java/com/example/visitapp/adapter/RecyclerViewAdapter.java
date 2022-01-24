package com.example.visitapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visitapp.DetailActivity;
import com.example.visitapp.R;
import com.example.visitapp.common.DataListListener;
import com.example.visitapp.database.db.MyApp;
import com.example.visitapp.database.entity.Wisata;
import com.example.visitapp.ui.AddDataActivity;
import com.example.visitapp.ui.ViewDataActivity;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Wisata> dataList = new ArrayList<>();
    private DataListListener listener;

    public void setData(List<Wisata> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            Wisata data = dataList.get(i);
            int position = findPosition(data);
            if (position == -1) {
                this.dataList.add(data);
                notifyItemInserted(this.dataList.size() - 1);
            } else {
                this.dataList.remove(position);
                this.dataList.add(position, data);
                notifyItemChanged(position);
            }
        }
    }

    private int findPosition(Wisata data) {
        int position = -1;

        if (!this.dataList.isEmpty()) {
            for (int i = 0; i < dataList.size(); i++) {
                if (this.dataList.get(i).getId() == data.getId()) {
                    position = i;
                }
            }
        }

        return position;
    }


    public void removeData(Wisata data) {
        if (this.dataList.isEmpty()) {
            return;
        }

        for (int i = 0; i < dataList.size(); i++) {
            if (this.dataList.get(i).getId() == data.getId()) {
                this.dataList.remove(i);
                notifyItemRemoved(i);
            }
        }
    }
    public void setRemoveListener(DataListListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wisata, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dataList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        private TextView judul, lokasi;
        private ImageView btnHapus, fotoWisata;
        private Wisata data;
        private DataListListener listener;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            fotoWisata = itemView.findViewById(R.id.fotoWisata);
            judul = itemView.findViewById(R.id.judul);
            lokasi = itemView.findViewById(R.id.lokasi);
//            btnHapus = itemView.findViewById(R.id.btn_hapus);
//
//            btnHapus.setOnClickListener(this);
            itemView.setOnClickListener(this);
        }

        void bind(Wisata data, DataListListener listener) {
            this.data = data;

            this.listener = listener;
            String namaImage =(data.getFoto()) ;
            Context context = fotoWisata.getContext();
            int id = context.getResources().getIdentifier(""+namaImage, "drawable", context.getPackageName());
            fotoWisata.setImageResource(id);


            judul.setText(data.getJudul());
            lokasi.setText(data.getLokasi());

        }

        @Override
        public void onClick(View view) {
//            if (view.getId() == R.id.btn_hapus) {
//
//                MyApp.getInstance().getDatabase().userDao().delete(data);
//                listener.onRemoveClick(data);
//                Toast.makeText(itemView.getContext(), "Berhasil Dihapus", Toast.LENGTH_SHORT).show();
//
//            }
//            else
            if (view.getId() == R.id.item_list) {

                Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.TAG_DATA_INTENT, data.getId());
                itemView.getContext().startActivity(intent);

            }
        }
    }
}