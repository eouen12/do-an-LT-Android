package com.doanltandroid.quizme.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doanltandroid.quizme.Class.Credit;
import com.doanltandroid.quizme.R;

import java.util.ArrayList;

public class CreditAdapter extends RecyclerView.Adapter<CreditAdapter.CreditViewHolder> {
    private ArrayList<Credit> mListCredit;
    private LayoutInflater inflater;
    private Context context;
    public CreditAdapter(Context context, ArrayList<Credit> mListCredit) {
        this.inflater = LayoutInflater.from(context);
        this.mListCredit = mListCredit;
        this.context = context;
    }
    @NonNull
    @Override
    public CreditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.goi_credit_item,parent,false);
        return new CreditViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull CreditViewHolder holder, int position) {
        Credit credit = this.mListCredit.get(position);
        holder.text_ten_goi_credit.setText(credit.getTenGoiCredit());
        holder.text_so_tien.setText(credit.getSoTien()+" VND");
    }

    @Override
    public int getItemCount() {
        return this.mListCredit.size();
    }

    public class CreditViewHolder extends RecyclerView.ViewHolder {
        private TextView text_ten_goi_credit, text_so_tien;
        private CreditAdapter adapter;
        public CreditViewHolder(@NonNull View itemView, CreditAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            this.text_ten_goi_credit = itemView.findViewById(R.id.text_ten_goi_credit);
            this.text_so_tien = itemView.findViewById(R.id.text_so_tien);
        }
    }
}
