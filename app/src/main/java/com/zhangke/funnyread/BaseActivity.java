package com.zhangke.funnyread;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.zhangke.funnyread.utils.UiTools;

/**
 * Created by ZhangKe on 2016/12/6.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setMyContentView();
        initView();
    }

    protected abstract void setMyContentView();
    protected  abstract void initView();

    protected void showToast(String msg){
        UiTools.showToast(BaseActivity.this, msg);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
