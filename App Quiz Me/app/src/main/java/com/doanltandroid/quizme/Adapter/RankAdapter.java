package com.doanltandroid.quizme.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.doanltandroid.quizme.Class.NguoiChoi;
import com.doanltandroid.quizme.R;

import java.util.ArrayList;

public class RankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int TYPE_VIEW_ITEM = 0;
    private final static int TYPE_VIEW_LOADNG = 1;
    private final LayoutInflater inflater;
    private final ArrayList<NguoiChoi> mListNguoiChoi;
    private final Context context;

    public RankAdapter(ArrayList<NguoiChoi> mListNguoiChoi, Context context) {
        this.mListNguoiChoi = mListNguoiChoi;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return mListNguoiChoi.get(position) == null ? TYPE_VIEW_LOADNG : TYPE_VIEW_ITEM;    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_VIEW_ITEM)
        {
            View item = this.inflater.inflate(R.layout.ranking_item,parent,false);
            return new NguoiChoiViewHoler(item,this);
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
        if(holder instanceof NguoiChoiViewHoler)
        {
            HienThiThongTin((NguoiChoiViewHoler)holder,position);
        }
        else if (holder instanceof LoadingViewHolder)
        {
            HienThiProgressBar((LoadingViewHolder)holder);
        }
    }

    @Override
    public int getItemCount() {
        return this.mListNguoiChoi == null ? 0 : this.mListNguoiChoi.size();
    }

    class NguoiChoiViewHoler extends RecyclerView.ViewHolder {
        private final RankAdapter rankAdapter;
        private final TextView vitri;
        private final TextView tenDangNhap_textView;
        private final ImageView imgNguoiChoi;
        private final TextView diemCaoNhat_textView;
        public NguoiChoiViewHoler(@NonNull View itemView, RankAdapter rankAdapter) {
            super(itemView);
            this.rankAdapter = rankAdapter;
            this.vitri = itemView.findViewById(R.id.vitri_rankitem_textview);
            this.tenDangNhap_textView = itemView.findViewById(R.id.tennguoichoi_rankitem_textview);
            this.diemCaoNhat_textView = itemView.findViewById(R.id.diem_rankitem_textview);
            this.imgNguoiChoi = itemView.findViewById(R.id.anhdaidien_rankitem_imageview);
        }
    }
    class LoadingViewHolder extends RecyclerView.ViewHolder {
        private final ProgressBar progressBar;
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            this.progressBar = itemView.findViewById(R.id.progressBar);
        }
    }

    void HienThiThongTin (NguoiChoiViewHoler holer,int position)
    {
        NguoiChoi nguoiChoi = mListNguoiChoi.get(position);
        holer.vitri.setText(position+1+"");
        holer.tenDangNhap_textView.setText(nguoiChoi.getTenDangNhap());
        holer.diemCaoNhat_textView.setText(nguoiChoi.getDiemCaoNhat()+"");
    }
    void HienThiProgressBar(LoadingViewHolder holder)
    {

    }
}
