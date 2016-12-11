package com.zhangke.funnyread.ZhiHu.model;

import com.zhangke.funnyread.ZhiHu.entity.ZhiHuColumnEntity;
import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryEntity;
import com.zhangke.funnyread.common.OnHttpCallbaclListener;

/**
 * Created by ZhangKe at 2016/12/10
 */
public interface IZhiHuDiary {
    /**
     * 获取知乎日报数据
     * @param callback  监听器
     */
    public void getZhiHuDiaryLatest( OnHttpCallbaclListener<ZhiHuDiaryEntity.Stories> callback);

    /**
     * 获取知乎专栏
     * @param date 日期
     * @param callback 监听器
     */
    public void getZhiHuDiaryBefore(String date, OnHttpCallbaclListener<ZhiHuDiaryEntity.Stories> callback);
}
