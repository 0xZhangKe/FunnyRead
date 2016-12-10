package com.zhangke.funnyread.ZhiHu.model;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zhangke.funnyread.ZhiHu.entity.ZhiHuColumnEntity;
import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryEntity;
import com.zhangke.funnyread.common.MyApplication;
import com.zhangke.funnyread.common.OnHttpCallbaclListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ZhangKe at 2016/12/10
 */
public class ZhiHu implements IZhiHu {

//    RequestQueue mQueue = Volley.newRequestQueue(MyApplication.getContextObject());

    int tmpCount = 0;
    @Override
    public void getZhiHuDiary(int pageNumbre, int date, final OnHttpCallbaclListener<ZhiHuDiaryEntity> callback) {
        final List<ZhiHuDiaryEntity> list = new ArrayList<>();
        for(int i=0;i<10; i++){
            ZhiHuDiaryEntity entity = new ZhiHuDiaryEntity();
            entity.setLoadingItem(false);
            entity.setErrorItem(false);
            list.add(entity);
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(3 == tmpCount){
                    callback.onNoData();
                }else if(5==tmpCount){
                    callback.onError("123");
                }else{
                    callback.onSuccess(list);
                }
                tmpCount++;
            }
        },1000);
//        StringRequest stringRequest = new StringRequest("http://www.baidu.com",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("TAG", response);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("TAG", error.getMessage(), error);
//            }
//        });
//        mQueue.add(stringRequest);
    }

    @Override
    public void getZhiHuDiary(int pageNumbre, OnHttpCallbaclListener<ZhiHuColumnEntity> callback) {

    }
}
