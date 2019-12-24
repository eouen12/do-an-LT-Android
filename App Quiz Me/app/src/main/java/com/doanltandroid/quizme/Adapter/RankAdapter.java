package com.doanltandroid.quizme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.RankViewHolder> {
    private final ArrayList<NguoiChoi> mlistNguoiChoi;
    private final Context mcontext;
    private LayoutInflater minflater;
    public RankAdapter(ArrayList<NguoiChoi> mlistNguoiChoi, Context context) {
        this.mlistNguoiChoi = mlistNguoiChoi;
        this.mcontext = context;
        this.minflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RankAdapter.RankViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.minflater.inflate(R.layout.ranking_item,parent,false);
        return new RankViewHolder(itemView,this);
    }

    @Override
    public void onBindViewHolder(@NonNull RankAdapter.RankViewHolder holder, int position) {
        NguoiChoi nguoiChoi = this.mlistNguoiChoi.get(position);
        holder.mAnhDaiDien.setText(nguoiChoi.getHinhDaiDien());
        holder.mTenNguoiChoi.setText(nguoiChoi.getTenDangNhap());
        holder.mDiem.setText(nguoiChoi.getDiemCaoNhat());
    }

    @Override
    public int getItemCount() {

        return this.mlistNguoiChoi.size();
    }

    public class RankViewHolder extends RecyclerView.ViewHolder {

        private final TextView mAnhDaiDien;
        private final TextView mTenNguoiChoi;
        private final TextView mDiem;
        private final RankAdapter mrankAdapter;
        public RankViewHolder(@NonNull View itemView, RankAdapter rankAdapter) {
            super(itemView);
            this.mrankAdapter = rankAdapter;
            this.mAnhDaiDien = itemView.findViewById(R.id.anhdaidien_rankitem_imageview);
            this.mTenNguoiChoi = itemView.findViewById(R.id.tennguoichoi_rankitem_textview);
            this.mDiem = itemView.findViewById(R.id.diem_rankitem_textview);

        }
    }
}
