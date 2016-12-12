package com.zhangke.funnyread.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
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

    /**
     * 设置Window Title颜色<br/>
     * @param activity activity
     */
    public static void setWindow(Activity activity){
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }else{
            //TODO set level api window
        }
    }
}
