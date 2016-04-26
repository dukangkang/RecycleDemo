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
 * Created by docking on 16/4/25 22:27.
 */
public class LinearAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // 图片
    private static final int TYPE_IMG = 0;
    // 图片+文字
    private static final int TYPE_TEXT = 1;

    private Context mContext = null;
    private List<RecycleBean> mList;
    private OnItemClickListener mOnItemClickListener = null;

    public LinearAdapter(Context context) {
        this.mContext = context;
    }

    public LinearAdapter(Context context, List<RecycleBean> list) {
        this.mContext = context;
        this.mList = list;
    }

    public void replace(List<RecycleBean> list) {
        this.mList = list;
        this.notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case TYPE_IMG:
                holder = new ImageHolder(LayoutInflater.from(mContext).inflate(R.layout.image_item_layout, null));
                break;
            case TYPE_TEXT:
                holder = new TextHolder(LayoutInflater.from(mContext).inflate(R.layout.text_item_layout, null));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(null != mList) {
            RecycleBean bean = mList.get(position);
            if(holder instanceof ImageHolder) {
                final ImageHolder imageHolder = (ImageHolder) holder;
                imageHolder.mLogoIv.setImageResource(R.drawable.ic_launcher);
                imageHolder.mLogoIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(null != mOnItemClickListener) {
                            mOnItemClickListener.onItemClick(imageHolder.mLogoIv, imageHolder.getPosition());
                        }
                    }
                });
            } else if(holder instanceof TextHolder) {
                final TextHolder textHolder = (TextHolder) holder;
                textHolder.mLogoIv.setImageResource(R.drawable.ic_launcher);
                textHolder.mTitleTv.setText(bean.getTitle()+"");
                textHolder.mLogoIv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(null != mOnItemClickListener) {
                            mOnItemClickListener.onItemClick(textHolder.mLogoIv, textHolder.getPosition());
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(null == mList) {
            return TYPE_IMG;
        }
        RecycleBean bean = mList.get(position);
        if(null != bean) {
            return bean.getType();
        }
        return TYPE_IMG;
    }

    @Override
    public int getItemCount() {
        if(null == mList) {
            return 0;
        }
        return mList.size();
    }

    public class ImageHolder extends RecyclerView.ViewHolder {

        private int position = 0;

        private ImageView mLogoIv = null;

        public ImageHolder(View itemView) {
            super(itemView);
            mLogoIv = (ImageView) itemView.findViewById(R.id.item_img);
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
        mOnItemClickListener = listener;
    }
}
