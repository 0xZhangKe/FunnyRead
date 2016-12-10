package com.zhangke.funnyread.ZhiHu.presenter;

import com.zhangke.funnyread.ZhiHu.entity.ZhiHuDiaryEntity;
import com.zhangke.funnyread.ZhiHu.model.IZhiHu;
import com.zhangke.funnyread.ZhiHu.model.ZhiHu;
import com.zhangke.funnyread.ZhiHu.view.IZhiHuDiaryView;
import com.zhangke.funnyread.common.OnHttpCallbaclListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZhangKe at 2016/12/10
 */
public class ZhiHuDiaryPresenterImpl implements IZhiHuDiaryPresenter {

    private List<ZhiHuDiaryEntity> list_data = new ArrayList<>();
    private IZhiHuDiaryView iZhiHuDiaryView;
    private IZhiHu iZhiHu;

    private int pageNumber = 1;

    public ZhiHuDiaryPresenterImpl(IZhiHuDiaryView iZhiHuDiaryView) {
        this.iZhiHuDiaryView = iZhiHuDiaryView;
        iZhiHu = new ZhiHu();
        list_data.add(null);
        addListHead();
        onRefresh();
    }

    @Override
    public void onRefresh() {
        iZhiHuDiaryView.setRefresh(true);
        pageNumber = 1;
        if(list_data.size() > 0){
            list_data.clear();
        }
        addListHead();
        iZhiHu.getZhiHuDiary(pageNumber, 0, new OnHttpCallbaclListener<ZhiHuDiaryEntity>() {
            @Override
            public void onSuccess(List<ZhiHuDiaryEntity> data) {
                list_data.addAll(data);
                iZhiHuDiaryView.setRefresh(false);
                iZhiHuDiaryView.notifyAdapterDataChanged();
            }

            @Override
            public void onError(String error) {
                iZhiHuDiaryView.setRefresh(false);
                iZhiHuDiaryView.onShowToast(error);
            }

            @Override
            public void onNoData() {
                iZhiHuDiaryView.setRefresh(false);
                iZhiHuDiaryView.onShowToast("暂无数据");
            }
        });
    }

    @Override
    public void onScrollToBottom() {
        ZhiHuDiaryEntity entity = new ZhiHuDiaryEntity();
        entity.setLoadingItem(true);
        entity.setErrorItem(false);
        list_data.add(entity);
        iZhiHuDiaryView.notifyAdapterDataChanged();
        iZhiHu.getZhiHuDiary(pageNumber + 1, 0, new OnHttpCallbaclListener<ZhiHuDiaryEntity>() {
            @Override
            public void onSuccess(List<ZhiHuDiaryEntity> data) {
                pageNumber++;
                clearErrorAndFooterItem();
                list_data.addAll(data);
                iZhiHuDiaryView.closeLoadingState();
                iZhiHuDiaryView.notifyAdapterDataChanged();
            }

            @Override
            public void onError(String error) {
                clearErrorAndFooterItem();
                ZhiHuDiaryEntity entity = new ZhiHuDiaryEntity();
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

    /**
     * 清楚RecyclerView中的错误或者Foot而视图
     */
    private void clearErrorAndFooterItem(){
        if(list_data.size() > 0){
            for(ZhiHuDiaryEntity entity : list_data){
                if(entity.isLoadingItem() || entity.isErrorItem()){
                    list_data.remove(entity);
                }
            }
        }
    }

    /**
     * 给list_data添加一个head，下标为0的数据会被设置为head。
     */
    private void addListHead(){
        list_data.add(0,null);
    }

    @Override
    public List<ZhiHuDiaryEntity> getRecyclerData() {
        return list_data;
    }
}
