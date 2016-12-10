package com.zhangke.funnyread.common;

import android.support.v4.app.Fragment;

import com.zhangke.funnyread.utils.UiTools;

/**
 * Created by ZhangKe on 2016/12/6.
 */
public class BaseFragment extends Fragment {


    protected void showToast(String msg){
        UiTools.showToast(getActivity(), msg);
    }

}
