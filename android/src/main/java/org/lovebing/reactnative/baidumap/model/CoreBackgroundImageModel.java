package org.lovebing.reactnative.baidumap.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 标注背景图模型详情
 * <p>
 * Created by Letto. on 2017/7/5.
 */

public class CoreBackgroundImageModel implements Parcelable{
    private int scale;
    private String uri;
    private int height;
    private int width;
    private boolean __packager_asset;

    protected CoreBackgroundImageModel(Parcel in) {
        scale = in.readInt();
        uri = in.readString();
        height = in.readInt();
        width = in.readInt();
        __packager_asset = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(scale);
        dest.writeString(uri);
        dest.writeInt(height);
        dest.writeInt(width);
        dest.writeByte((byte) (__packager_asset ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CoreBackgroundImageModel> CREATOR = new Creator<CoreBackgroundImageModel>() {
        @Override
        public CoreBackgroundImageModel createFromParcel(Parcel in) {
            return new CoreBackgroundImageModel(in);
        }

        @Override
        public CoreBackgroundImageModel[] newArray(int size) {
            return new CoreBackgroundImageModel[size];
        }
    };

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean is__packager_asset() {
        return __packager_asset;
    }

    public void set__packager_asset(boolean __packager_asset) {
        this.__packager_asset = __packager_asset;
    }
}
