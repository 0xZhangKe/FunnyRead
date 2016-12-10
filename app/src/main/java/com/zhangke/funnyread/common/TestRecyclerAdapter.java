package com.zhangke.funnyread.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.zhangke.funnyread.R;
import com.zhangke.funnyread.ZhiHu.ZhiHuEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangKe at 2016/12/9
 */
public class TestRecyclerAdapter extends BaseRecyclerRefreshAdapter<ZhiHuEntity>{

    public TestRecyclerAdapter(Context context, List<ZhiHuEntity> list_data) {
        super(context, list_data);
        ZhiHuEntity entity = new ZhiHuEntity();
//        list_data.add(0,entity);
//        list_data.add(entity);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(ITEM_TYPE_MAIN == viewType){
            return new CommonViewHolder(LayoutInflater.from(context).inflate(R.layout.test_recycler_adapter,parent,false));
        }else{
            return super.onCreateViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof CommonViewHolder){
            ((CommonViewHolder)holder).tv.setText(list_data.get(position).getContent());
        }else{
            super.onBindViewHolder(holder, position);
        }
    }

    @Override
    public int getItemCount() {
        return null == list_data ? 0:list_data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(0 != position && (list_data.size()-1) != position) {
            return ITEM_TYPE_MAIN;
        }else{
            return super.getItemViewType(position);
        }
    }

    public static class CommonViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public CommonViewHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.tv);
        }
    }
}
