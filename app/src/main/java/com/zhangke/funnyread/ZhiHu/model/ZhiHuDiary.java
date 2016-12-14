package com.zhangke.funnyread.ZhiHu.model;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.zhangke.funnyread.R;
import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryDetailEntity;
import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryEntity;
import com.zhangke.funnyread.common.MyApplication;
import com.zhangke.funnyread.common.OnHttpDataCallbaclListener;
import com.zhangke.funnyread.common.OnHttpListCallbaclListener;
import com.zhangke.funnyread.db.ZhiHuDBHelper;
import com.zhangke.funnyread.utils.Api;
import com.zhangke.funnyread.utils.DateUtils;
import com.zhangke.funnyread.utils.HttpUtils;

import java.util.List;

/**
 * Created by ZhangKe at 2016/12/10
 */
public class ZhiHuDiary implements IZhiHuDiary {

    private RequestQueue mQueue;
    private String json_error;
    private String internet_error;
    private Context context;

    public ZhiHuDiary(Context context) {
        this.context = context;
        json_error = context.getString(R.string.json_error);
        internet_error = context.getString(R.string.internet_error);
        mQueue = Volley.newRequestQueue(context);
    }

    public ZhiHuDiary(Activity activity) {
        json_error = activity.getString(R.string.json_error);
        internet_error = activity.getString(R.string.internet_error);
        mQueue = Volley.newRequestQueue(activity);
    }

    @Override
    public void getZhiHuDiaryLatest(final OnHttpListCallbaclListener<ZhiHuDiaryEntity.Stories> callback) {
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
                                ZhiHuDBHelper dbHelper = ZhiHuDBHelper.getInstance();
                                dbHelper.saveZhiHuDiaryBefore(context, zhiHuDiaryEntity.getStories(), zhiHuDiaryEntity.getDate());
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
    public void getZhiHuDiaryBefore(String date, final OnHttpListCallbaclListener<ZhiHuDiaryEntity.Stories> callback) {
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

    @Override
    public void getZhiHuDiaryDetail(String id, final OnHttpDataCallbaclListener<ZhiHuDiaryDetailEntity> callback) {

        StringRequest stringRequest = new StringRequest(Api.ZHIHU_NEWS+id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        if(null == response){
                            callback.onNoData();
                        }else{
                            ZhiHuDiaryDetailEntity zhiHuDiaryDetailEntity = HttpUtils.parseJsonWithGson(response, ZhiHuDiaryDetailEntity.class);
                            if(null != zhiHuDiaryDetailEntity){
                                callback.onSuccess(zhiHuDiaryDetailEntity);
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
