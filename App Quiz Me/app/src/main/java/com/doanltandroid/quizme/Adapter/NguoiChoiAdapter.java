package com.doanltandroid.quizme.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doanltandroid.quizme.Class.NguoiChoi;
import com.doanltandroid.quizme.R;

import java.util.ArrayList;

public class NguoiChoiAdapter extends RecyclerView.Adapter<NguoiChoiAdapter.NguoiChoiViewHolder> {
    private ArrayList<NguoiChoi> mListNguoiChoi;
    private LayoutInflater mInflater;
    private Context context;

    public NguoiChoiAdapter(Context context, ArrayList<NguoiChoi> mListNguoiChoi){
        this.mInflater = LayoutInflater.from(context);
        this.mListNguoiChoi = mListNguoiChoi;
        this.context = context;
    }

    @NonNull
    @Override
    public NguoiChoiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = this.mInflater.inflate(R.layout.ranking_item, parent, false);
            return new NguoiChoiViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull NguoiChoiViewHolder holder, int position) {
        NguoiChoi nguoiChoi = this.mListNguoiChoi.get(position);
        holder.tenDangNhap.setText(nguoiChoi.getTenDangNhap());
        holder.diemCaoNhat.setText(nguoiChoi.getSoDiemCaoNhat());
    }

    @Override
    public int getItemCount() {
        return  this.mListNguoiChoi.size();
    }

        public class NguoiChoiViewHolder extends RecyclerView.ViewHolder {
        private TextView tenDangNhap,diemCaoNhat;
        private NguoiChoiAdapter mAdapter;

        public NguoiChoiViewHolder(@NonNull View itemView, NguoiChoiAdapter mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;
            this.tenDangNhap =  itemView.findViewById(R.id.text_view_tenNC_rank);
            this.diemCaoNhat =  itemView.findViewById(R.id.text_view_diem_rank);
        }
    }
}
