package com.zhangke.funnyread.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by ZhangKe at 2016/12/8
 */
public class UiTools {

    private static Toast mToast;

    public static void showToast(Context context, String msg){
        if(null == mToast){
            mToast = Toast.makeText(context,msg,Toast.LENGTH_SHORT);
        }else{
            mToast.setText(msg);
        }
        mToast.show();
    }
}
