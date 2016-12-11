package com.zhangke.funnyread.ZhiHu.presenter;

import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryEntity;

import java.util.List;

/**
 * Created by ZhangKe at 2016/12/10
 */
public interface IZhiHuDiaryPresenter {
    void onRefresh();
    void onScrollToBottom();
    List<ZhiHuDiaryEntity.Stories> getRecyclerData();
}
