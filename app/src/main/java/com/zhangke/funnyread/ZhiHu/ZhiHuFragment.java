package com.zhangke.funnyread.ZhiHu;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.zhangke.funnyread.BaseFragment;
import com.zhangke.funnyread.common.Find_tab_Adapter;
import com.zhangke.funnyread.R;
import com.zhangke.funnyread.common.IRecyclerViewScrollListener;
import com.zhangke.funnyread.utils.DisplayUtil;
import com.zhangke.funnyread.widget.WrapperHeightAnimView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangKe at 2016/12/8
 */
public class ZhiHuFragment extends BaseFragment implements IRecyclerViewScrollListener{

    private Activity activity;
    private View rootView;
    private View ll_layout;
    private TabLayout tab_layout;
    private ViewPager view_pager;

    private Find_tab_Adapter adapter;

    private List<Fragment> list_fragment = new ArrayList<>();
    private List<String> list_title = new ArrayList<>();
    private ZhiHuDiaryFragment zhiHuDiaryFragment;
    private ZhiHuColumnFragment zhiHuColumnFragment;

    private int tabLayoutHeight=100;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.zhihu_fragment,container,false);
        ll_layout = rootView.findViewById(R.id.ll_layout);
        tab_layout = (TabLayout) rootView.findViewById(R.id.tab_layout);
        view_pager = (ViewPager) rootView.findViewById(R.id.view_pager);
        tabLayoutHeight = DisplayUtil.dip2px(activity , 48);
        initView();
        return rootView;
    }

    private void initView(){
        zhiHuDiaryFragment = new ZhiHuDiaryFragment();
        zhiHuColumnFragment = new ZhiHuColumnFragment();
        zhiHuDiaryFragment.setOnRecyclerViewScrollListener(this);
        zhiHuColumnFragment.setOnRecyclerViewScrollListener(this);

        list_fragment.add(zhiHuDiaryFragment);
        list_fragment.add(zhiHuColumnFragment);
        list_title.add("知乎日报");
        list_title.add("知乎专栏");

        tab_layout.setTabMode(TabLayout.MODE_FIXED);
        tab_layout.addTab(tab_layout.newTab().setText(list_title.get(0)));
        tab_layout.addTab(tab_layout.newTab().setText(list_title.get(1)));

        adapter = new Find_tab_Adapter(getChildFragmentManager(),list_fragment,list_title);
        view_pager.setAdapter(adapter);

        tab_layout.setupWithViewPager(view_pager);
    }

    @Override
    public void onScrollUp() {
        showTabLayout();
    }

    @Override
    public void onScrollDown() {
        hideTabLayout();
    }

    private void hideTabLayout(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(tab_layout,"translationY", -tabLayoutHeight);
        animator.setDuration(300);
        animator.start();
    }

    private void showTabLayout(){
        ObjectAnimator animator = ObjectAnimator.ofFloat(tab_layout,"translationY", 0);
        animator.setDuration(300);
        animator.start();
    }
}
