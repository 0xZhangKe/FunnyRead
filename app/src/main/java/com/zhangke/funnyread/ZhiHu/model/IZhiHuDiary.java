package com.zhangke.funnyread.ZhiHu.model;

import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryDetailEntity;
import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryEntity;
import com.zhangke.funnyread.common.OnHttpDataCallbaclListener;
import com.zhangke.funnyread.common.OnHttpListCallbaclListener;

/**
 * Created by ZhangKe at 2016/12/10
 */
public interface IZhiHuDiary {
    /**
     * 获取知乎日报数据
     * @param callback  监听器
     */
    public void getZhiHuDiaryLatest( OnHttpListCallbaclListener<ZhiHuDiaryEntity.Stories> callback);

    /**
     * 获取知乎专栏
     * @param date 日期
     * @param callback 监听器
     */
    public void getZhiHuDiaryBefore(String date, OnHttpListCallbaclListener<ZhiHuDiaryEntity.Stories> callback);

    /**
     * 获取知乎日报内容
     * @param id ID
     * @param callbaclListener 监听器
     */
    public void getZhiHuDiaryDetail(String id, OnHttpDataCallbaclListener<ZhiHuDiaryDetailEntity> callbaclListener);
}
