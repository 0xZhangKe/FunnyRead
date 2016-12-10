package com.zhangke.funnyread.main;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zhangke.funnyread.common.BaseActivity;
import com.zhangke.funnyread.DouBan.DouBanFragment;
import com.zhangke.funnyread.GuoKe.GuoKeFragment;
import com.zhangke.funnyread.JianDan.JianDanFragment;
import com.zhangke.funnyread.R;
import com.zhangke.funnyread.TopNews.TopNewsFragment;
import com.zhangke.funnyread.ZhiHu.ZhiHuFragment;

import java.lang.reflect.Method;

/**
 * Created by ZhangKe on 2016/12/6.
 */
public class MainActivity extends BaseActivity implements IMainView,View.OnClickListener{

    private static final String ZHIHU_TAG="zhihu";
    private static final String DOUBAN_TAG="douban";
    private static final String GUOKE_TAG="guoke";
    private static final String JIANDAN_TAG="jiandan";
    private static final String TOPNEWS_TAG="topnews";

    private Toolbar toolbar;
    private FrameLayout fl_content;
    private DrawerLayout drawer_layout;
    private TextView drawer_tv_zhihu,drawer_tv_douban,
            drawer_tv_guoke,drawer_tv_jiandan,drawer_tv_top_news,
            drawer_tv_setting;

    private ZhiHuFragment zhiHuFragment;
    private DouBanFragment douBanFragment;
    private GuoKeFragment guoKeFragment;
    private JianDanFragment jianDanFragment;
    private TopNewsFragment topNewsFragment;

    private ActionBarDrawerToggle mDrawerToggle;
    private FragmentManager fragmentManager;
    private IMainPresenter iMainPresenter;

    @Override
    protected void setMyContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initView() {
        fl_content = (FrameLayout)findViewById(R.id.fl_content);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        drawer_layout = (DrawerLayout)findViewById(R.id.drawer_layout);
        initDrawerView();

        toolbar.setTitle("知乎");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        iMainPresenter = new MainPresenterImpl(this);
        zhiHuFragment = new ZhiHuFragment();
        douBanFragment = new DouBanFragment();
        guoKeFragment = new GuoKeFragment();
        jianDanFragment = new JianDanFragment();
        topNewsFragment = new TopNewsFragment();

        mDrawerToggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.syncState();
        drawer_layout.setDrawerListener(mDrawerToggle);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(R.id.fl_content,zhiHuFragment,ZHIHU_TAG);
        ft.show(zhiHuFragment);
        ft.commit();
    }

    private void initDrawerView(){
        drawer_tv_zhihu = (TextView) findViewById(R.id.drawer_tv_zhihu);
        drawer_tv_douban = (TextView) findViewById(R.id.drawer_tv_douban);
        drawer_tv_guoke = (TextView) findViewById(R.id.drawer_tv_guoke);
        drawer_tv_jiandan = (TextView) findViewById(R.id.drawer_tv_jiandan);
        drawer_tv_top_news = (TextView) findViewById(R.id.drawer_tv_top_news);
        drawer_tv_setting = (TextView) findViewById(R.id.drawer_tv_setting);

        FragmentItemOnClick click = new FragmentItemOnClick();
        drawer_tv_zhihu.setOnClickListener(click);
        drawer_tv_douban.setOnClickListener(click);
        drawer_tv_guoke.setOnClickListener(click);
        drawer_tv_jiandan.setOnClickListener(click);
        drawer_tv_top_news.setOnClickListener(click);
        drawer_tv_setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.drawer_tv_setting:
                iMainPresenter.settingClick();
                break;
        }
    }

    private class FragmentItemOnClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            Fragment zhihu = fragmentManager.findFragmentByTag(ZHIHU_TAG);
            Fragment douban = fragmentManager.findFragmentByTag(DOUBAN_TAG);
            Fragment guoke = fragmentManager.findFragmentByTag(GUOKE_TAG);
            Fragment jiandan = fragmentManager.findFragmentByTag(JIANDAN_TAG);
            Fragment topnews = fragmentManager.findFragmentByTag(TOPNEWS_TAG);
            if(null != zhihu) ft.hide(zhihu);
            if(null != douban) ft.hide(douban);
            if(null != guoke) ft.hide(guoke);
            if(null != jiandan) ft.hide(jiandan);
            if(null != topnews) ft.hide(topnews);
            switch(v.getId()){
                case R.id.drawer_tv_zhihu:
                    iMainPresenter.zhiHuClick();
                    if (zhihu == null) {
                        zhihu = new ZhiHuFragment();
                        ft.add(R.id.fl_content, zhihu, ZHIHU_TAG);
                    } else {
                        ft.show(zhihu);
                    }
                    break;
                case R.id.drawer_tv_douban:
                    iMainPresenter.douBanClick();
                    if (douban == null) {
                        douban = new DouBanFragment();
                        ft.add(R.id.fl_content, douban, DOUBAN_TAG);
                    } else {
                        ft.show(douban);
                    }
                    break;
                case R.id.drawer_tv_guoke:
                    iMainPresenter.guoKeClick();
                    if (guoke == null) {
                        guoke = new GuoKeFragment();
                        ft.add(R.id.fl_content, guoke, GUOKE_TAG);
                    } else {
                        ft.show(guoke);
                    }
                    break;
                case R.id.drawer_tv_jiandan:
                    iMainPresenter.jianDanClick();
                    if (jiandan == null) {
                        jiandan = new JianDanFragment();
                        ft.add(R.id.fl_content, jiandan, JIANDAN_TAG);
                    } else {
                        ft.show(jiandan);
                    }
                    break;
                case R.id.drawer_tv_top_news:
                    iMainPresenter.topNewsClick();
                    if (topnews == null) {
                        topnews = new TopNewsFragment();
                        ft.add(R.id.fl_content, topnews, TOPNEWS_TAG);
                    } else {
                        ft.show(topnews);
                    }
                    break;
                default:
                    break;
            }
            ft.commit();
        }
    }

    @Override
    public void onZhiHuClick() {
        toolbar.setTitle("知乎");
        drawer_layout.closeDrawers();
    }

    @Override
    public void onDouBanClick() {
        toolbar.setTitle("豆瓣");
        drawer_layout.closeDrawers();
    }

    @Override
    public void onGuoKeClick() {
        toolbar.setTitle("果壳");
        drawer_layout.closeDrawers();
    }

    @Override
    public void onJianDanClick() {
        toolbar.setTitle("煎蛋");
        drawer_layout.closeDrawers();
    }

    @Override
    public void onTopNewsClick() {
        toolbar.setTitle("头条");
        drawer_layout.closeDrawers();
    }

    @Override
    public void onSettingClick() {
        drawer_layout.closeDrawers();
    }

    /**
     **显示溢出菜单图标
     **/
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (featureId == Window.FEATURE_ACTION_BAR && menu != null) {
            if (menu.getClass().getSimpleName().equals("MenuBuilder")) {
                try {
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_tool_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
