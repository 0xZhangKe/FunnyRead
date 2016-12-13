package com.zhangke.funnyread.ZhiHu.presenter;

import android.content.Context;

import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryEntity;
import com.zhangke.funnyread.ZhiHu.model.IZhiHuDiary;
import com.zhangke.funnyread.ZhiHu.model.ZhiHuDiary;
import com.zhangke.funnyread.ZhiHu.view.IZhiHuDiaryView;
import com.zhangke.funnyread.common.OnHttpListCallbaclListener;
import com.zhangke.funnyread.utils.DateUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by ZhangKe at 2016/12/10
 */
public class ZhiHuDiaryPresenterImpl implements IZhiHuDiaryPresenter {

    private List<ZhiHuDiaryEntity.Stories> list_data = new ArrayList<>();
    private IZhiHuDiaryView iZhiHuDiaryView;
    private IZhiHuDiary iZhiHuDiary;
    private Context context;

    private String currentDate;//程序已经加载到的日期

    public ZhiHuDiaryPresenterImpl(Context context , IZhiHuDiaryView iZhiHuDiaryView) {
        this.context = context;
        this.iZhiHuDiaryView = iZhiHuDiaryView;
        iZhiHuDiary = new ZhiHuDiary(context);
        currentDate = DateUtils.getNextDate(DateUtils.getNowDate());
        onRefresh();
    }

    @Override
    public void onRefresh() {
        synchronized (list_data) {
            iZhiHuDiaryView.setRefresh(true);
            currentDate = DateUtils.getNextDate(DateUtils.getNowDate());
            if (list_data.size() > 0) {
                list_data.clear();
            }
            addListHead();//TODO notifyAdapter
            iZhiHuDiary.getZhiHuDiaryLatest(new OnHttpListCallbaclListener<ZhiHuDiaryEntity.Stories>() {
                @Override
                public void onSuccess(List<ZhiHuDiaryEntity.Stories> data) {
                    list_data.addAll(data);
                    iZhiHuDiaryView.setRefresh(false);
                    iZhiHuDiaryView.notifyAdapterDataChanged();
                }

                @Override
                public void onError(String error) {
                    iZhiHuDiaryView.setRefresh(false);
                    iZhiHuDiaryView.showSnakebar(error);
                    iZhiHuDiaryView.notifyAdapterDataChanged();
                }

                @Override
                public void onNoData() {
                    iZhiHuDiaryView.setRefresh(false);
                    iZhiHuDiaryView.showSnakebar("暂无数据");
                    iZhiHuDiaryView.notifyAdapterDataChanged();
                }
            });
        }
    }

    @Override
    public void onScrollToBottom() {
        synchronized (list_data) {
            clearErrorAndFooterItem();
            ZhiHuDiaryEntity.Stories entity = new ZhiHuDiaryEntity.Stories();
            entity.setLoadingItem(true);
            entity.setErrorItem(false);
            list_data.add(entity);
            iZhiHuDiaryView.notifyAdapterDataChanged();
            iZhiHuDiaryView.recyclerViewScrollToPosition(list_data.size());
            iZhiHuDiary.getZhiHuDiaryBefore(DateUtils.getBeforeDate(currentDate), new OnHttpListCallbaclListener<ZhiHuDiaryEntity.Stories>() {
                @Override
                public void onSuccess(List<ZhiHuDiaryEntity.Stories> data) {
                    currentDate = DateUtils.getBeforeDate(currentDate);
                    clearErrorAndFooterItem();
                    list_data.addAll(data);
                    iZhiHuDiaryView.closeLoadingState();
                    iZhiHuDiaryView.notifyAdapterDataChanged();
                }

                @Override
                public void onError(String error) {
                    clearErrorAndFooterItem();
                    ZhiHuDiaryEntity.Stories entity = new ZhiHuDiaryEntity.Stories();
                    entity.setErrorItem(true);
                    list_data.add(entity);
                    iZhiHuDiaryView.closeLoadingState();
                    iZhiHuDiaryView.notifyAdapterDataChanged();
                }

                @Override
                public void onNoData() {
                    clearErrorAndFooterItem();
                    iZhiHuDiaryView.closeLoadingState();
                    iZhiHuDiaryView.notifyAdapterDataChanged();
                }
            });
        }
    }

    /**
     * 清楚RecyclerView中的错误或者Foot而视图
     */
    private void clearErrorAndFooterItem(){
        synchronized (list_data) {
            if (list_data.size() > 0) {
                for(Iterator<ZhiHuDiaryEntity.Stories> it = list_data.iterator(); it.hasNext(); ){
                    ZhiHuDiaryEntity.Stories entity = it.next();
                    if (entity.isLoadingItem() || entity.isErrorItem()) {
                        it.remove();
                    }
                }
            }
        }
    }

    /**
     * 给list_data添加一个head，下标为0的数据会被设置为head。
     */
    private void addListHead(){
        ZhiHuDiaryEntity.Stories entity = new ZhiHuDiaryEntity.Stories();
        entity.setErrorItem(false);
        entity.setLoadingItem(false);
        list_data.add(entity);
    }

    @Override
    public List<ZhiHuDiaryEntity.Stories> getRecyclerData() {
        return list_data;
    }
}
