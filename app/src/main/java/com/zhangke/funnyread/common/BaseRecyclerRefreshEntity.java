package com.zhangke.funnyread.common;

/**
 * Created by ZhangKe at 2016/12/11
 */
public class BaseRecyclerRefreshEntity {
    private boolean isErrorItem = false;
    private boolean isLoadingItem = false;

    public boolean isErrorItem() {
        return isErrorItem;
    }

    public void setErrorItem(boolean errorItem) {
        isErrorItem = errorItem;
    }

    public boolean isLoadingItem() {
        return isLoadingItem;
    }

    public void setLoadingItem(boolean loadingItem) {
        isLoadingItem = loadingItem;
    }
}
