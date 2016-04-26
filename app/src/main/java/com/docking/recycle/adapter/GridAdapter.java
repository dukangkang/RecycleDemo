package com.docking.recycle.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.docking.recycle.OnItemClickListener;
import com.docking.recycle.R;
import com.docking.recycle.RecycleBean;

import java.util.List;

/**
 * Created by docking on 16/4/26 09:48.
 */
public class GridAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private OnItemClickListener mOnItemClickListener = null;
    private Context mContext = null;
    private List<RecycleBean> mList = null;

    public GridAdapter(Context context) {
        this.mContext = context;
    }

    public GridAdapter(Context context, List<RecycleBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void replace(List<RecycleBean> list) {
        this.mList = list;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.text_item_layout, null);
        view.setOnClickListener(this);
        RecyclerView.ViewHolder holder = new TextHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(null != mList) {
            RecycleBean bean = mList.get(position);
            String title = "---";
            if(null != bean) {
                title = bean.getTitle();
                holder.itemView.setTag(bean);
            }
            TextHolder textHolder = (TextHolder) holder;
            textHolder.mLogoIv.setImageResource(R.drawable.ic_launcher);
            textHolder.mTitleTv.setText(title);
        }
    }

    @Override
    public int getItemCount() {
        if(null == mList) {
            return 0;
        }
        return mList.size();
    }

    @Override
    public void onClick(View v) {
        if(null != mOnItemClickListener) {
            mOnItemClickListener.onItemClick(v, (RecycleBean) v.getTag());
        }
    }

    public class TextHolder extends RecyclerView.ViewHolder {
        private ImageView mLogoIv = null;
        private TextView mTitleTv = null;
        public TextHolder(View itemView) {
            super(itemView);
            mLogoIv = (ImageView) itemView.findViewById(R.id.item_img);
            mTitleTv = (TextView) itemView.findViewById(R.id.item_title);
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
