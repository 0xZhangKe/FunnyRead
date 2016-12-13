package com.zhangke.funnyread.ZhiHu.view;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhangke.funnyread.R;
import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryDetailEntity;
import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryEntity;
import com.zhangke.funnyread.ZhiHu.presenter.IZhiHuDiaryDetailPresenter;
import com.zhangke.funnyread.ZhiHu.presenter.ZhiHuDiaryDetailPresenter;
import com.zhangke.funnyread.common.BaseActivity;
import com.zhangke.funnyread.utils.UiTools;

/**
 * Created by ZhangKe at 2016/12/12
 */
public class ZhiHuDiaryDetailActivity extends BaseActivity implements IZhiHuDiaryDetailView {

    private CoordinatorLayout cl_root_layout;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsing_toolbar_layout;
    private SwipeRefreshLayout swipe_refresh;
    private WebView web_view;
    private ImageView image_title;

    private ZhiHuDiaryEntity.Stories entity;
    private IZhiHuDiaryDetailPresenter iZhiHuDiaryDetailPresenter;

    @Override
    protected void setMyContentView() {
        setContentView(R.layout.zhihu_diary_detail_activity);
    }

    @Override
    protected void initView() {
        UiTools.setWindow(this);
        cl_root_layout = (CoordinatorLayout) findViewById(R.id.cl_root_layout);
        collapsing_toolbar_layout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar_layout);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        swipe_refresh = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        web_view = (WebView)findViewById(R.id.web_view);
        image_title = (ImageView) findViewById(R.id.image_title);

        web_view.setScrollbarFadingEnabled(true);
        //能够和js交互
        web_view.getSettings().setJavaScriptEnabled(true);
        //缩放,设置为不能缩放可以防止页面上出现放大和缩小的图标
        web_view.getSettings().setBuiltInZoomControls(false);
        //缓存
        web_view.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        //开启DOM storage API功能
        web_view.getSettings().setDomStorageEnabled(true);
        //开启application Cache功能
        web_view.getSettings().setAppCacheEnabled(false);
        web_view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                presenter.openUrl(view, url);
                return true;
            }

        });

        entity = (ZhiHuDiaryEntity.Stories)getIntent().getExtras().getSerializable("data");
        iZhiHuDiaryDetailPresenter = new ZhiHuDiaryDetailPresenter(this, entity.getId()+"", this);

        collapsing_toolbar_layout.setExpandedTitleTextAppearance(R.style.CollapsingToolbarExpandedTextAppearance);
        collapsing_toolbar_layout.setCollapsedTitleTextAppearance(R.style.CollapsingToolbarCollapsedTextAppearance);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        collapsing_toolbar_layout.setTitle(entity.getTitle());

        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                iZhiHuDiaryDetailPresenter.onRefresh();
            }
        });
    }

    @Override
    public void bindData(final ZhiHuDiaryDetailEntity entity) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                web_view.loadDataWithBaseURL("x-data://base",convertResult(entity.getCss().get(0),
                        entity.getBody()),"text/html","UTF-8", null);
                Glide.with(ZhiHuDiaryDetailActivity.this).
                        load(entity.getImages().get(0)).
                        fitCenter().
                        into(image_title);
            }
        });
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

    @Override
    public void share() {

    }

    @Override
    public void copyText() {

    }

    @Override
    public void openInBrowser() {

    }

    @Override
    public void showSnakebar(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showNoActionSnackbar(cl_root_layout,msg);
            }
        });
    }

    @Override
    public void setRefresh(boolean refresh) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                swipe_refresh.setRefreshing(false);
            }
        });
    }

    private String convertResult(String cssPath, String preReuslt) {

        preReuslt = preReuslt.replace("<div class=\"img-place-holder\">", "");
        preReuslt = preReuslt.replace("<div class=\"headline\">", "");

        // 在api中，css的地址是以一个数组的形式给出，这里需要设置
        // in fact,in api,css addresses are given as an array
        // api中还有js的部分，这里不再解析js
        // javascript is included,but here I don't use it
        // 不再选择加载网络css，而是加载本地assets文件夹中的css
        // use the css file from local assets folder,not from network
        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/zhihu_daily.css\" type=\"text/css\">";
//        String css = "<link rel=\"stylesheet\" href=\""+cssPath+"\" type=\"text/css\">";


        // 根据主题的不同确定不同的加载内容
        // load content judging by different theme
//        String theme = "<body className=\"\" onload=\"onLoaded()\">";

        return new StringBuilder()
                .append("<!DOCTYPE html>\n")
                .append("<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n")
                .append("<head>\n")
                .append("\t<meta charset=\"utf-8\" />")
                .append(css)
                .append("\n</head>\n")
                .append(preReuslt)
                .append("</body></html>").toString();

    }
}
