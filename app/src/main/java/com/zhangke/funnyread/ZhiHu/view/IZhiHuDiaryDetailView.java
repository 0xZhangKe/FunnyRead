package com.zhangke.funnyread.ZhiHu.view;

import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryDetailEntity;

/**
 * Created by ZhangKe at 2016/12/12
 */
public interface IZhiHuDiaryDetailView {
    void share();
    void bindData(ZhiHuDiaryDetailEntity entity);
    void copyText();
    void openInBrowser();
    void showSnakebar(String msg);
    void setRefresh(boolean refresh);
}
