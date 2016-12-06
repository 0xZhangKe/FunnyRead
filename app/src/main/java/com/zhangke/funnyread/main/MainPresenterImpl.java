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
}
