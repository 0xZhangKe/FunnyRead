package com.zhangke.funnyread.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.WindowManager;

import com.zhangke.funnyread.common.IRecyclerViewScrollListener;
import com.zhangke.funnyread.utils.DisplayUtil;

/**
 * Created by ZhangKe at 2016/12/10
 */
public class PowerRecyclerView extends SwipeRefreshLayout {

    private RecyclerView recycler_view;
    private OnPowerSwipeListener onPowerSwipeListener;
    private boolean isLoading = false;

    private int touchSlop;
    private float ScreenHeight=0;

    public PowerRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        recycler_view = new RecyclerView(context);
        recycler_view.setLayoutManager(new LinearLayoutManager(context));
        recycler_view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        touchSlop= ViewConfiguration.get(context).getScaledTouchSlop();
        WindowManager wm = (WindowManager)context.getSystemService(Context.WINDOW_SERVICE);
        ScreenHeight=wm.getDefaultDisplay().getHeight();
        setProgressViewEndTarget(false, DisplayUtil.dip2px(context, 100));
        addView(recycler_view);
        initControl();
    }

    private void initControl(){
        setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(null != PowerRecyclerView.this.onPowerSwipeListener) {
                    PowerRecyclerView.this.onPowerSwipeListener.onRefresh();
                }
            }
        });
        recycler_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(null != PowerRecyclerView.this.onPowerSwipeListener) {
                    if(!isLoading){
                        View lastView = recyclerView.getChildAt(recyclerView.getChildCount() - 1);
                        if (lastView != null && dy > 0 && dy > touchSlop) {
                            int[] position = new int[2];
                            lastView.getLocationOnScreen(position);
                            float lastViewBottomPosition = lastView.getHeight() + position[1];
                            if (lastViewBottomPosition <= ScreenHeight) {
                                isLoading = true;
                                PowerRecyclerView.this.onPowerSwipeListener.onScrollToBottom();
                            }
                        }
                    }
                    if(dy>0){
                        if(dy>IRecyclerViewScrollListener.TouchSlop) {
                            PowerRecyclerView.this.onPowerSwipeListener.onScrollDown();
                        }
                    }else{
                        if((-dy)>IRecyclerViewScrollListener.TouchSlop) {
                            PowerRecyclerView.this.onPowerSwipeListener.onScrollUp();
                        }
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    public void setLoading(boolean loading) {
        isLoading = loading;
    }

    public void setAdapter(RecyclerView.Adapter adapter){
        recycler_view.setAdapter(adapter);
    }

    public RecyclerView getRecycler_view() {
        return recycler_view;
    }

    public void setOnPowerSwipeListener(OnPowerSwipeListener onPowerSwipeListener) {
        this.onPowerSwipeListener = onPowerSwipeListener;
    }

    public static interface OnPowerSwipeListener extends IRecyclerViewScrollListener{
        void onRefresh();
        void onScrollToBottom();
    }
}
