package com.example.visitapp.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.visitapp.DetailActivity;
import com.example.visitapp.R;
import com.example.visitapp.UpdateUlasanActivity;
import com.example.visitapp.common.DataListListener;
import com.example.visitapp.database.entity.Ulasan;

import java.util.ArrayList;
import java.util.List;

public class UlasanViewAdapter extends RecyclerView.Adapter <UlasanViewAdapter.ViewHolder> {
    private List<Ulasan> dataList = new ArrayList<>();
    private DataListListener listener;
//    private Context mContext;

    public void setData(List<Ulasan> dataList) {
        for (int i = 0; i < dataList.size(); i++) {
            Ulasan data = dataList.get(i);
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

    private int findPosition(Ulasan data) {
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

    public void removeData(Ulasan data) {
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
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ulasan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(dataList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nama, tanggalReview, review, rating;
        //        private ImageView foto;
        private Ulasan data;
        private DataListListener listener;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            foto = itemView.findViewById(R.id.foto);
            nama = itemView.findViewById(R.id.nama);
            tanggalReview = itemView.findViewById(R.id.tanggalReview);
            review = itemView.findViewById(R.id.review);
            rating = itemView.findViewById(R.id.rating);
        }

        public void bind(Ulasan data, DataListListener listener) {
            this.data = data;

            this.listener = listener;

            nama.setText(data.getIduser());
            rating.setText(data.getBintang());
            tanggalReview.setText(data.getTanggal());
            review.setText(data.getReview());
        }

//        @Override
//        public void onClick(View view) {
////            if (view.getId() == R.id.btn_hapus) {
//////
//////                MyApp.getInstance().getDatabase().userDao().delete(data);
//////                listener.onRemoveClick(data);
//////                Toast.makeText(itemView.getContext(), "Berhasil Dihapus", Toast.LENGTH_SHORT).show();
//////
//////            }
//////            else
//            if (view.getId() == R.id.item_list) {
//
//                Intent intent = new Intent(itemView.getContext(), UpdateUlasanActivity.class);
//                intent.putExtra(DetailActivity.TAG_DATA_INTENT, data.getId());
//                itemView.getContext().startActivity(intent);
//
//            }
//
//        }
    }
}
