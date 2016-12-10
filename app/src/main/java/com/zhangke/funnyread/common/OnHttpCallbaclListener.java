package com.zhangke.funnyread.common;

import java.util.List;

/**
 * Created by ZhangKe at 2016/12/10
 */
public interface OnHttpCallbaclListener<T> {
    void onSuccess(List<T> data);
    void onError(String error);
    void onNoData();
}
