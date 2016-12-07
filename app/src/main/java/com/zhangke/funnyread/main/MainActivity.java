package com.zhangke.funnyread.main;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.zhangke.funnyread.BaseActivity;
import com.zhangke.funnyread.R;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangKe on 2016/12/6.
 */
public class MainActivity extends BaseActivity implements IMainView{

    private Toolbar toolbar;
    private RecyclerView recycler_view;
    private DrawerLayout drawer_layout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_test);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);
        drawer_layout = (DrawerLayout)findViewById(R.id.drawer_layout);


        List<String> data = new ArrayList<>();
        for(int i=0;i<50; i++){
            data.add(""+i);
        }
        TestAdapter adapter = new TestAdapter(this,data);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        recycler_view.setAdapter(adapter);

        toolbar.setTitle("知乎日报");
        setSupportActionBar(toolbar);


        String[] lvs = {"List Item 01", "List Item 02", "List Item 03", "List Item 04"};
        ListView lv_left_menu = (ListView)findViewById(R.id.lv_left_menu);
        ArrayAdapter<String> lv_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,lvs);
        lv_left_menu.setAdapter(lv_adapter);

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
