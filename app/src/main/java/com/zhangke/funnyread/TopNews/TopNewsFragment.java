package com.zhangke.funnyread.TopNews;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangke.funnyread.BaseFragment;
import com.zhangke.funnyread.R;

/**
 * Created by ZhangKe at 2016/12/8
 */
public class TopNewsFragment extends BaseFragment {
    private Activity activity;

    private View rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.topnews_fragment,container,false);
        return rootView;
    }
}
