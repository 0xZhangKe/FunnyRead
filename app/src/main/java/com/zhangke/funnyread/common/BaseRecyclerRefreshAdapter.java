package com.zhangke.funnyread.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhangke.funnyread.R;

import java.util.List;
import java.util.Objects;

/**
 * Created by ZhangKe at 2016/12/10
 */
public class BaseRecyclerRefreshAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    protected static final int ITEM_TYPE_HEAD=0;
    protected static final int ITEM_TYPE_FOOTER=1;
    protected static final int ITEM_TYPE_MAIN=2;

    protected Context context;
    protected List<T> list_data;

    public BaseRecyclerRefreshAdapter(Context context, List<T> list_data) {
        this.context = context;
        this.list_data = list_data;
        list_data.add(0,null);
        list_data.add(null);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE_HEAD){
            return new HeadViewHolder(LayoutInflater.from(context).
                    inflate(R.layout.recycler_head_view,parent,false));
        }
        if(viewType == ITEM_TYPE_FOOTER){
            return new FooterViewHolder(LayoutInflater.from(context).
                    inflate(R.layout.recycler_footer,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return null == list_data ? 0:list_data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(0 == position) return ITEM_TYPE_HEAD;
        if((list_data.size()-1) == position) return ITEM_TYPE_FOOTER;
        return ITEM_TYPE_MAIN;
    }

    public static class HeadViewHolder extends RecyclerView.ViewHolder{
        public HeadViewHolder(View itemView) {
            super(itemView);
        }
    }

    public static class FooterViewHolder extends RecyclerView.ViewHolder{
        private ProgressBar progress_bar;
        public FooterViewHolder(View itemView) {
            super(itemView);
            progress_bar = (ProgressBar)itemView.findViewById(R.id.progress_bar);
        }
    }
}
