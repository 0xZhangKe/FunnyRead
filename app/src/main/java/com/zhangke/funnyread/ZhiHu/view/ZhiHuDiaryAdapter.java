package com.zhangke.funnyread.ZhiHu.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangke.funnyread.R;
import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryEntity;
import com.zhangke.funnyread.common.BaseRecyclerRefreshAdapter;

import java.util.List;

/**
 * Created by ZhangKe at 2016/12/11
 */
public class ZhiHuDiaryAdapter extends BaseRecyclerRefreshAdapter<ZhiHuDiaryEntity.Stories> {

    private static final int ITEM_TYPE_DATE=4;

    public ZhiHuDiaryAdapter(Context context, List<ZhiHuDiaryEntity.Stories> list_data) {
        super(context, list_data);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE_MAIN){
            return new MainViewHolder(LayoutInflater.from(context).inflate(R.layout.zhihu_diary_adapter,parent,false));
        }
        if(viewType == ITEM_TYPE_DATE){
            return new DateViewHolder(LayoutInflater.from(context).inflate(R.layout.zhihu_diary_adapter_date,parent,false));
        }
        return super.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MainViewHolder){
            MainViewHolder viewHolder = (MainViewHolder)holder;
           viewHolder.tv_title.setText(list_data.get(position).getTitle());
            if(null != list_data.get(position).getImages() && list_data.get(position).getImages().size()>0) {
                Glide.with(context).load(list_data.get(position).getImages().get(0)).into(viewHolder.img);
            }
        }else if(holder instanceof DateViewHolder){
            DateViewHolder viewHolder = (DateViewHolder)holder;
            if(!TextUtils.isEmpty(list_data.get(position).getCurrentDate())) {
                viewHolder.tv_date.setText(list_data.get(position).getCurrentDate());
            }
        }else {
            super.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(list_data.get(position).isDateView()) return ITEM_TYPE_DATE;
        return super.getItemViewType(position);
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_title;
        private ImageView img;
        public MainViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView)itemView.findViewById(R.id.tv_title);
            img = (ImageView)itemView.findViewById(R.id.img);
        }
    }

    public static class DateViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_date;
        public DateViewHolder(View itemView) {
            super(itemView);
            tv_date = (TextView)itemView.findViewById(R.id.tv_date);
        }
    }
}
