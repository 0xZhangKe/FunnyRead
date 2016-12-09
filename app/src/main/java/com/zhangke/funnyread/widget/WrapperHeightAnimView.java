package com.zhangke.funnyread.widget;

import android.view.View;

/**
 * Created by ZhangKe at 2016/12/10
 */
public class WrapperHeightAnimView {
    private View mTarget;

    public WrapperHeightAnimView(View mTarget) {
        this.mTarget = mTarget;
    }

    public int getHeight(){
        return mTarget.getLayoutParams().height;
    }

    public void setHeight(int height){
        mTarget.getLayoutParams().height=height;
        mTarget.requestLayout();
    }
}
