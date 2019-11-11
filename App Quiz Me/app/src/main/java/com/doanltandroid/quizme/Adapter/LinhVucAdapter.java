package com.doanltandroid.quizme.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doanltandroid.quizme.Class.LinhVuc;
import com.doanltandroid.quizme.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LinhVucAdapter extends RecyclerView.Adapter<LinhVucAdapter.LinhVucViewHolder> {
    private ArrayList<LinhVuc> mListLinhVuc;
    private LayoutInflater inflater;
    private Context context;

    public LinhVucAdapter(Context context, ArrayList<LinhVuc> listLinhVuc) {
        this.inflater = LayoutInflater.from(context);
        this.mListLinhVuc = listLinhVuc;
        this.context = context;
    }
    @NonNull
    @Override
    public LinhVucAdapter.LinhVucViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.linh_vuc_item, parent, false);
        return new LinhVucViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull LinhVucAdapter.LinhVucViewHolder holder, int position) {
        LinhVuc linhVuc = this.mListLinhVuc.get(position);
        holder.text_linh_vuc.setText(linhVuc.getTenLinhVuc());
        Picasso.with(this.context).load("http://10.0.3.2:8000/images/linh-vuc/"  + linhVuc.getHinhAnh()).into(holder.img_linh_vuc);
    }

    @Override
    public int getItemCount() {
        return this.mListLinhVuc.size();
    }

    public class LinhVucViewHolder extends RecyclerView.ViewHolder {
        private TextView text_linh_vuc;
        private ImageView img_linh_vuc;
        private LinhVucAdapter adapter;
        public LinhVucViewHolder(@NonNull View itemView, LinhVucAdapter adapter) {
            super(itemView);
            this.adapter = adapter;
            this.text_linh_vuc = itemView.findViewById(R.id.text_linh_vuc);
            this.img_linh_vuc = itemView.findViewById(R.id.img_linh_vuc);
        }
    }
}
