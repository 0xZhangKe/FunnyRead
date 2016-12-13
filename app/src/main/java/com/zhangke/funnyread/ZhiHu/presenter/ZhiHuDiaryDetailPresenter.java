package com.zhangke.funnyread.ZhiHu.presenter;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryDetailEntity;
import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryEntity;
import com.zhangke.funnyread.ZhiHu.model.IZhiHuDiary;
import com.zhangke.funnyread.ZhiHu.model.ZhiHuDiary;
import com.zhangke.funnyread.ZhiHu.view.IZhiHuDiaryDetailView;
import com.zhangke.funnyread.common.OnHttpDataCallbaclListener;

import java.util.List;

/**
 * Created by ZhangKe at 2016/12/13
 */
public class ZhiHuDiaryDetailPresenter implements IZhiHuDiaryDetailPresenter {

    private Activity activity;
    private IZhiHuDiaryDetailView iZhiHuDiaryDetailView;
    private IZhiHuDiary iZhiHuDiary;
    private String id;

    public ZhiHuDiaryDetailPresenter(Activity activity, String id, IZhiHuDiaryDetailView iZhiHuDiaryDetailView) {
        this.activity = activity;
        this.id = id;
        this.iZhiHuDiaryDetailView = iZhiHuDiaryDetailView;
        iZhiHuDiary = new ZhiHuDiary(activity);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        iZhiHuDiary.getZhiHuDiaryDetail(id, new OnHttpDataCallbaclListener<ZhiHuDiaryDetailEntity>() {
            @Override
            public void onSuccess(ZhiHuDiaryDetailEntity data) {
                iZhiHuDiaryDetailView.setRefresh(false);
                iZhiHuDiaryDetailView.bindData(data);
            }

            @Override
            public void onError(String error) {
                iZhiHuDiaryDetailView.setRefresh(false);
                iZhiHuDiaryDetailView.showSnakebar(error);
            }

            @Override
            public void onNoData() {
                iZhiHuDiaryDetailView.setRefresh(false);
                iZhiHuDiaryDetailView.showSnakebar("暂无数据...");
            }
        });
    }

    @Override
    public void onCopyText(String text) {

    }

    @Override
    public void onShare() {

    }
}
