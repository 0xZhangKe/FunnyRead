package com.zhangke.funnyread.ZhiHu.view;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.zhangke.funnyread.R;
import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryDetailEntity;
import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryEntity;
import com.zhangke.funnyread.common.BaseActivity;
import com.zhangke.funnyread.common.MyApplication;
import com.zhangke.funnyread.utils.Api;
import com.zhangke.funnyread.utils.HttpUtils;
import com.zhangke.funnyread.utils.UiTools;

/**
 * Created by ZhangKe at 2016/12/12
 */
public class ZhiHuDiaryDetailActivity extends BaseActivity {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsing_toolbar_layout;
    private SwipeRefreshLayout swipe_refresh;
    private WebView web_view;
    private ImageView image_title;

    private ZhiHuDiaryEntity.Stories entity;

    private RequestQueue mQueue;

    @Override
    protected void setMyContentView() {
        setContentView(R.layout.zhihu_diary_detail_activity);
    }

    @Override
    protected void initView() {
        UiTools.setWindow(this);
        collapsing_toolbar_layout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar_layout);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        swipe_refresh = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        web_view = (WebView)findViewById(R.id.web_view);
        image_title = (ImageView) findViewById(R.id.image_title);

        entity = (ZhiHuDiaryEntity.Stories)getIntent().getExtras().getSerializable("data");
        mQueue = Volley.newRequestQueue(this);

        collapsing_toolbar_layout.setExpandedTitleTextAppearance(R.style.CollapsingToolbarExpandedTextAppearance);
        collapsing_toolbar_layout.setCollapsedTitleTextAppearance(R.style.CollapsingToolbarCollapsedTextAppearance);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        collapsing_toolbar_layout.setTitle(entity.getTitle());

        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                iniData();
            }
        });

        iniData();
    }

    private void iniData(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                swipe_refresh.setRefreshing(true);
            }
        });
        StringRequest stringRequest = new StringRequest(Api.ZHIHU_NEWS+entity.getId(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(final String response) {
                        if(null == response){
                            //
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    swipe_refresh.setRefreshing(false);
                                }
                            });
                        }else{
                            final ZhiHuDiaryDetailEntity zhiHuDiaryDetailEntity = HttpUtils.parseJsonWithGson(response, ZhiHuDiaryDetailEntity.class);
                            if(!TextUtils.isEmpty(zhiHuDiaryDetailEntity.getBody())){
//                                callback.onSuccess(zhiHuDiaryEntity.getStories());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        web_view.loadDataWithBaseURL("file://",zhiHuDiaryDetailEntity.getBody()
                                                ,"text/html","UTF-8","about:blank");
                                        Glide.with(ZhiHuDiaryDetailActivity.this).
                                                load(zhiHuDiaryDetailEntity.getImages().get(0)).
                                                into(image_title);
                                        swipe_refresh.setRefreshing(false);
                                    }
                                });
                            }else {
//                                callback.onNoData();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipe_refresh.setRefreshing(false);
                                    }
                                });
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(), error);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                swipe_refresh.setRefreshing(false);
                            }
                        });
                    }
                });
        mQueue.add(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.zhihu_diary_detail_toolbar_menu, menu);
        return true;
    }

}
