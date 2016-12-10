package com.zhangke.funnyread.ZhiHu.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangke.funnyread.R;
import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryEntity;
import com.zhangke.funnyread.common.BaseRecyclerRefreshAdapter;

import java.util.List;

/**
 * Created by ZhangKe at 2016/12/11
 */
public class ZhiHuDiaryAdapter extends BaseRecyclerRefreshAdapter<ZhiHuDiaryEntity> {
    public ZhiHuDiaryAdapter(Context context, List<ZhiHuDiaryEntity> list_data) {
        super(context, list_data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE_MAIN){
            return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.zhihu_diary_adapter,parent,false));
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MainViewHolder){
            MainViewHolder viewHolder = (MainViewHolder)holder;
           //TODO bind data
        }
        super.onBindViewHolder(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title;
        private ImageView img;
        public MainViewHolder(View itemView) {
            super(itemView);
        }
    }
}
