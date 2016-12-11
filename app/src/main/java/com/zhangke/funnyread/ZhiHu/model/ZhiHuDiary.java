package com.zhangke.funnyread.ZhiHu.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zhangke.funnyread.R;
import com.zhangke.funnyread.ZhiHu.entity.ZhiHuColumnEntity;
import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryEntity;
import com.zhangke.funnyread.common.MyApplication;
import com.zhangke.funnyread.common.OnHttpCallbaclListener;
import com.zhangke.funnyread.utils.Api;
import com.zhangke.funnyread.utils.DateUtils;
import com.zhangke.funnyread.utils.HttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangKe at 2016/12/10
 */
public class ZhiHuDiary implements IZhiHuDiary {

    private Context context;
    private RequestQueue mQueue;
    private String json_error;
    private String internet_error;

    public ZhiHuDiary(Context context) {
        this.context = context;
        json_error = context.getString(R.string.json_error);
        internet_error = context.getString(R.string.internet_error);
        mQueue = Volley.newRequestQueue(MyApplication.getContextObject());
    }

    @Override
    public void getZhiHuDiaryLatest(final OnHttpCallbaclListener<ZhiHuDiaryEntity.Stories> callback) {
        StringRequest stringRequest = new StringRequest(Api.ZHIHU_LATEST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        if(null == response){
                            callback.onNoData();
                        }else{
                            ZhiHuDiaryEntity zhiHuDiaryEntity = HttpUtils.parseJsonWithGson(response, ZhiHuDiaryEntity.class);
                            if(null != zhiHuDiaryEntity.getStories() && zhiHuDiaryEntity.getStories().size()>0){
                                callback.onSuccess(zhiHuDiaryEntity.getStories());
                            }else {
                                callback.onNoData();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(), error);
                        callback.onError(internet_error);
                    }
                });
        mQueue.add(stringRequest);
    }

    @Override
    public void getZhiHuDiaryBefore(String date, final OnHttpCallbaclListener<ZhiHuDiaryEntity.Stories> callback) {
        StringRequest stringRequest = new StringRequest(Api.ZHIHU_HISTORY+date,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        if(null == response){
                            callback.onNoData();
                        }else{
                            ZhiHuDiaryEntity zhiHuDiaryEntity = HttpUtils.parseJsonWithGson(response, ZhiHuDiaryEntity.class);
                            if(null != zhiHuDiaryEntity.getStories() && zhiHuDiaryEntity.getStories().size()>0){
                                ZhiHuDiaryEntity.Stories entity = new ZhiHuDiaryEntity.Stories();
                                entity.setDateView(true);
                                entity.setCurrentDate(DateUtils.formatDate(zhiHuDiaryEntity.getDate()));
                                List<ZhiHuDiaryEntity.Stories> list_data = zhiHuDiaryEntity.getStories();
                                list_data.add(0,entity);
                                callback.onSuccess(list_data);
                            }else {
                                callback.onNoData();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(), error);
                        callback.onError(internet_error);
                    }
                });
        mQueue.add(stringRequest);
    }
}
