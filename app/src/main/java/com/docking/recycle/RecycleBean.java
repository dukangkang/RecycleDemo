package com.docking.recycle;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by docking on 16/4/25 22:35.
 */
public class RecycleBean implements Parcelable {

    private String title = null;
    private String url = null;
    private int type = 0;
    private String tag = null; // 未使用

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeInt(this.type);
        dest.writeString(this.tag);
    }

    public RecycleBean() {
    }

    protected RecycleBean(Parcel in) {
        this.title = in.readString();
        this.url = in.readString();
        this.type = in.readInt();
        this.tag = in.readString();
    }

    public static final Parcelable.Creator<RecycleBean> CREATOR = new Parcelable.Creator<RecycleBean>() {
        public RecycleBean createFromParcel(Parcel source) {
            return new RecycleBean(source);
        }

        public RecycleBean[] newArray(int size) {
            return new RecycleBean[size];
        }
    };
}
