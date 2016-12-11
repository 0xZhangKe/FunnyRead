package com.zhangke.funnyread.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.zhangke.funnyread.R;

import java.util.List;

/**
 * Created by ZhangKe at 2016/12/10
 */
public abstract class BaseRecyclerRefreshAdapter<T extends BaseRecyclerRefreshEntity>
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    protected static final int ITEM_TYPE_HEAD=0;
    protected static final int ITEM_TYPE_LOADING_FOOTER=1;
    protected static final int ITEM_TYPE_ERROR=2;
    protected static final int ITEM_TYPE_MAIN=3;

    protected Context context;
    protected List<T> list_data;

    public BaseRecyclerRefreshAdapter(Context context, List<T> list_data) {
        this.context = context;
        this.list_data = list_data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE_HEAD){
            return new HeadViewHolder(LayoutInflater.from(context).
                    inflate(R.layout.recycler_head_view,parent,false));
        }
        if(viewType == ITEM_TYPE_LOADING_FOOTER){
            return new FooterViewHolder(LayoutInflater.from(context).
                    inflate(R.layout.recycler_footer,parent,false));
        }
        if(viewType == ITEM_TYPE_ERROR){
            return new ErrorViewHolder(LayoutInflater.from(context).
                    inflate(R.layout.recycler_error_item,parent,false));
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
        if((list_data.get(position).isLoadingItem())) return ITEM_TYPE_LOADING_FOOTER;
        if((list_data.get(position).isErrorItem())) return ITEM_TYPE_ERROR;
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

    public static class ErrorViewHolder extends RecyclerView.ViewHolder{
        public ErrorViewHolder(View itemView) {
            super(itemView);
        }
    }
}
