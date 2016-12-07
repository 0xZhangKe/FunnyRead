package com.zhangke.funnyread.main;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhangke.funnyread.R;

import java.util.List;

/**
 * Created by ZhangKe at 2016/12/7
 */
public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder>{

    private Activity activity;
    private List<String> data;

    public TestAdapter(Activity activity, List<String> data) {
        this.activity = activity;
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.test_adapter,parent,false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder  extends RecyclerView.ViewHolder{
        TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView)itemView.findViewById(R.id.tv);
        }

    }
}
