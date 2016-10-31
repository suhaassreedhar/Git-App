package com.suhaas.gitapp.utils;


import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.suhaas.gitapp.adapters.LoadMoreRecyclerViewAdapter;

public abstract class LoadMoreInSwipeRefreshLayoutMoreManager extends LoadMoreManager {

    public LoadMoreInSwipeRefreshLayoutMoreManager(RecyclerView recyclerView, SwipeRefreshLayout swipeRefreshLayout) {
        super(recyclerView, swipeRefreshLayout);
    }

    public void setSwipeRefreshLayoutRefreshing(LoadMoreRecyclerViewAdapter adapter) {
        adapter.setHasLoading(true);
        adapter.hideLoadingView();
        load();
    }
}
