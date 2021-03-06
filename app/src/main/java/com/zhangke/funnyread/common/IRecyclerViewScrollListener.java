package com.zhangke.funnyread.common;

import com.zhangke.funnyread.utils.DisplayUtil;

/**
 * Created by ZhangKe at 2016/12/9<br/>
 * RecyclerView的滑动监听器
 */
public interface IRecyclerViewScrollListener {
    int TouchSlop = DisplayUtil.dip2px(MyApplication.getContextObject(),8);
    void onScrollUp();
    void onScrollDown();
}
