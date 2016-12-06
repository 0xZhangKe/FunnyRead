package com.zhangke.funnyread.main;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.zhangke.funnyread.BaseActivity;
import com.zhangke.funnyread.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangKe on 2016/12/6.
 */
public class MainActivity extends BaseActivity implements IMainView{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = (ListView)findViewById(R.id.list_view);
        List<String> data = new ArrayList<>();
        for(int i=0;i<50; i++){
            data.add(""+i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
        lv.setAdapter(adapter);
    }
}
