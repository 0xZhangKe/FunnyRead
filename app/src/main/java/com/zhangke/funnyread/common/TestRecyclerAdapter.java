package com.zhangke.funnyread.common;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangke.funnyread.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangKe at 2016/12/9
 */
public class TestRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public static enum ITEM_TYPE {
        ITEM_TYPE_HEAD, ITEM_TYPE_COMMON
    }

    private Context context;
    private List<String> list_data;

    public TestRecyclerAdapter(Context context) {
        this.context = context;
        list_data = new ArrayList<>();
        list_data.add("0");
        for(int i=0;i<20;i++){
            list_data.add(""+i);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == ITEM_TYPE.ITEM_TYPE_HEAD.ordinal()){
            return new HeadViewHolder(LayoutInflater.from(context).
                    inflate(R.layout.recycler_head_view,parent,false));
        }else{
            return new CommonViewHolder(LayoutInflater.from(context).
                    inflate(R.layout.test_recycler_adapter,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof CommonViewHolder){
            ((CommonViewHolder)holder).tv.setText(list_data.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return null == list_data ? 0:list_data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? ITEM_TYPE.ITEM_TYPE_HEAD.ordinal() : ITEM_TYPE.ITEM_TYPE_COMMON.ordinal();
    }

    public static class CommonViewHolder extends RecyclerView.ViewHolder{
        private TextView tv;
        public CommonViewHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.tv);
        }
    }

    public static class HeadViewHolder extends RecyclerView.ViewHolder{
        public HeadViewHolder(View itemView) {
            super(itemView);
        }
    }
}
