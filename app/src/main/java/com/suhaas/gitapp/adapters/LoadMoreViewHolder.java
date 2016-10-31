package com.suhaas.gitapp.adapters;


import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.suhaas.gitapp.R;

public class LoadMoreViewHolder extends RecyclerView.ViewHolder {
    public final AppCompatTextView mLoadTV;

    public final ProgressBar mLoadPB;

    public LoadMoreViewHolder(View itemView) {
        super(itemView);

        mLoadTV = (AppCompatTextView) itemView.findViewById(R.id.load_more_tip);
        mLoadPB = (ProgressBar) itemView.findViewById(R.id.load_more_progressbar);
    }
}
