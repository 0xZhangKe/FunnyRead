package com.zhangke.funnyread.main;

/**
 * Created by ZhangKe at 2016/12/6
 */
public class MainPresenterImpl implements IMainPresenter{

    private IMainView iMainView;
    private IMain iMain;

    public MainPresenterImpl(IMainView iMainView) {
        this.iMainView = iMainView;
    }

    @Override
    public void zhiHuClick() {
        iMainView.onZhiHuClick();
    }

    @Override
    public void douBanClick() {
        iMainView.onDouBanClick();
    }

    @Override
    public void guoKeClick() {
        iMainView.onGuoKeClick();
    }

    @Override
    public void jianDanClick() {
        iMainView.onJianDanClick();
    }

    @Override
    public void topNewsClick() {
        iMainView.onTopNewsClick();
    }

    @Override
    public void settingClick() {
        iMainView.onSettingClick();
    }
}
