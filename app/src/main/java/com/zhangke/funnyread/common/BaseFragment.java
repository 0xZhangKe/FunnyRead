package com.zhangke.funnyread.common;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import com.zhangke.funnyread.utils.UiTools;

/**
 * Created by ZhangKe on 2016/12/6.
 */
public class BaseFragment extends Fragment {

    private Snackbar snackbar;

    protected void showToast(final String msg){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                UiTools.showToast(getActivity(), msg);
            }
        });
    }

    protected void showNoActionSnackbar(View view , String msg){
        snackbar = Snackbar.make(view,msg, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}
