package com.doanltandroid.quizme.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doanltandroid.quizme.Class.LuotChoi;
import com.doanltandroid.quizme.R;

import java.util.ArrayList;

public class LuotChoiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int TYPE_VIEW_ITEM = 0;
    private final static int TYPE_VIEW_LOADNG = 1;
    private final LayoutInflater inflater;
    private final ArrayList<LuotChoi> mListLuotChoi;
    private final Context context;

    public LuotChoiAdapter(ArrayList<LuotChoi> mListLuotChoi, Context context) {
        this.mListLuotChoi = mListLuotChoi;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return mListLuotChoi.get(position) == null ? TYPE_VIEW_LOADNG : TYPE_VIEW_ITEM;    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_VIEW_ITEM)
        {
            View item = this.inflater.inflate(R.layout.lichsuchoi_item,parent,false);
            return new LuotChoiViewHoler(item,this);
        }
        else if(viewType == TYPE_VIEW_LOADNG)
        {
            View item = this.inflater.inflate(R.layout.loading_item,parent,false);
            return new LoadingViewHolder(item);
        }
        return  null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof LuotChoiViewHoler)
        {
            HienThiThongTin((LuotChoiViewHoler)holder,position);
        }
        else if (holder instanceof LoadingViewHolder)
        {
            HienThiProgressBar((LoadingViewHolder)holder);
        }
    }

    @Override
    public int getItemCount() {
        return this.mListLuotChoi == null ? 0 : this.mListLuotChoi.size();
    }

    class LuotChoiViewHoler extends RecyclerView.ViewHolder {
        private final LuotChoiAdapter luotChoiAdapter;
        private final TextView vitri;
        private final TextView so_cau;
        private final TextView diem;
        private final TextView dateCredte;
        public LuotChoiViewHoler(@NonNull View itemView, LuotChoiAdapter luotChoiAdapter) {
            super(itemView);
            this.luotChoiAdapter = luotChoiAdapter;
            this.vitri = itemView.findViewById(R.id.stt_lichsu);
            this.so_cau = itemView.findViewById(R.id.so_cau_linhsu_textview);
            this.diem = itemView.findViewById(R.id.diem_lichsu_textview);
            this.dateCredte = itemView.findViewById(R.id.date_textview);
        }
    }
    class LoadingViewHolder extends RecyclerView.ViewHolder {
        private final ProgressBar progressBar;
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            this.progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    void HienThiThongTin (LuotChoiViewHoler holer,int position)
    {
        LuotChoi luotChoi = mListLuotChoi.get(position);
        holer.vitri.setText(position+1+"");
        holer.so_cau.setText("Số câu: "+luotChoi.getSo_cau());
        holer.diem.setText(luotChoi.getDiem()+" điểm");
        holer.dateCredte.setText(luotChoi.getDateCreate());
    }
    void HienThiProgressBar(LoadingViewHolder holder)
    {

    }
}
