package com.docking.recycle;

import android.view.View;

/**
 * Created by docking on 16/4/26 10:11.
 */
public interface OnItemClickListener {

    public void onItemClick(View view, int position);

    public void onItemClick(View view, RecycleBean bean);

    public void onItemLongClick(View view, int position);
}
