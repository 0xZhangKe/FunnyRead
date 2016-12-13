package com.zhangke.funnyread.common;

import java.util.List;

/**
 * Created by ZhangKe at 2016/12/13
 */
public interface OnHttpDataCallbaclListener<T> {
    void onSuccess(T data);
    void onError(String error);
    void onNoData();
}
