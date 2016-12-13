package com.zhangke.funnyread.ZhiHu.view;

/**
 * Created by ZhangKe at 2016/12/10
 */
public interface IZhiHuDiaryView {
    void setRefresh(boolean refresh);
    void closeLoadingState();
    void notifyAdapterDataChanged();
    void onShowToast(String msg);
    void showSnakebar(String msg);
    void recyclerViewScrollToPosition(int position);
}
