package com.zhangke.funnyread.ZhiHu.model;

import com.zhangke.funnyread.ZhiHu.entity.ZhiHuColumnEntity;
import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryEntity;
import com.zhangke.funnyread.common.OnHttpCallbaclListener;

/**
 * Created by ZhangKe at 2016/12/10
 */
public interface IZhiHu {
    /**
     * 获取知乎日报数据
     * @param pageNumbre 页码
     * @param date 日期
     * @param callback  监听器
     */
    public void getZhiHuDiary(int pageNumbre, int date, OnHttpCallbaclListener<ZhiHuDiaryEntity> callback);

    /**
     * 获取知乎专栏
     * @param pageNumbre 页码
     * @param callback 监听器
     */
    public void getZhiHuDiary(int pageNumbre, OnHttpCallbaclListener<ZhiHuColumnEntity> callback);
}
