package org.lovebing.reactnative.baidumap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.react.bridge.ReadableMap;
import com.squareup.picasso.Picasso;

/**
 * 自定义标记图
 * <p>
 * Created by Letto. on 2017/6/21.
 */

public class CustomMarkerView extends LinearLayout {
    private static final String TAG = "CustomMarkerView";
    private Context mContext;
    private TextView titleView;
    private TextView subTitleView;
    private ImageView backgroundImageView;
    private ImageView frontImageView;
    private ReadableMap option;


    public CustomMarkerView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item_marker_view, this);
        this.mContext = context;
        initView();
    }

    public CustomMarkerView(Context context, ReadableMap option) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.item_marker_view, this);
        initView();
        this.mContext = context;
        this.option = option;
        setData();
    }

    private void initView() {
        titleView = (TextView) findViewById(R.id.tv_title);
        subTitleView = (TextView) findViewById(R.id.tv_sub_title);
        backgroundImageView = (ImageView) findViewById(R.id.iv_background_image);
        frontImageView = (ImageView) findViewById(R.id.iv_front_image);
    }

    private void setData() {
        ReadableMap backgroundImage = option.getMap("backgroundImage");
        String uri = backgroundImage.getString("uri");
        int width = backgroundImage.getInt("width");
        int height = backgroundImage.getInt("height");
        int scale = backgroundImage.getInt("scale");
        setBackgroundView(uri, width, height, scale);
        setTitleView(option.getString("frontTitle"));
        setSubTitleView(option.getString("frontSubtitle"));
        if (option.hasKey("frontImage")) {
            ReadableMap frontImage = option.getMap("frontImage");
            String frontUri = frontImage.getString("uri");
            int frontWidth = frontImage.getInt("width");
            int frontHeight = frontImage.getInt("height");
            int frontScale = frontImage.getInt("scale");
            setFrontImageView(frontUri, frontWidth, frontHeight, frontScale);
        }

    }

    private void setTitleView(String title) {
        titleView.setText(title);
    }

    private void setSubTitleView(String subTitle) {
        subTitleView.setText(subTitle);
        subTitleView.setVisibility(VISIBLE);
    }

    private void setFrontImageView(String frontImageUri, int width, int height, int scale) {
        titleView.setVisibility(GONE);
        frontImageView.setVisibility(VISIBLE);
        //Toast.makeText(mContext, "frontImageUri = " + frontImageUri, Toast.LENGTH_LONG).show();
        Picasso.with(mContext).load(frontImageUri).resize(width * scale, height * scale).centerCrop().into(frontImageView);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.width = width * scale;
        params.height = height * scale;
        params.setMargins(0, 0, 0, 9 * scale);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL);
        frontImageView.setLayoutParams(params);
        if (frontImageUri.contains("resources_images_50")) {
            frontImageView.setImageResource(R.mipmap.jyyx_50);
        } else if (frontImageUri.contains("resources_images_51")) {
            frontImageView.setImageResource(R.mipmap.jyyx_51);
        } else if (frontImageUri.contains("resources_images_52")) {
            frontImageView.setImageResource(R.mipmap.jyyx_52);
        } else if (frontImageUri.contains("resources_images_53")) {
            frontImageView.setImageResource(R.mipmap.jyyx_53);
        }
    }

    public void setBackgroundView(int resId) {
        backgroundImageView.setImageResource(resId);
    }

    private void setBackgroundView(String uriStr, int width, int height, int scale) {
        Log.d(TAG, "uriStr = " + uriStr);
        // Toast.makeText(mContext, "uriStr = " + uriStr, Toast.LENGTH_LONG).show();
        Picasso.with(mContext).load(uriStr).resize(width * scale, height * scale).centerCrop().into(backgroundImageView);
//        Picasso.with(mContext).load(new File(uriStr.replace("_", "/") + "@3x")).resize(width * scale, height * scale).centerCrop().into(backgroundImageView);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.width = width * scale;
        params.height = height * scale;
        backgroundImageView.setLayoutParams(params);
        if (uriStr.contains("resources_images_35")) {
            backgroundImageView.setImageResource(R.mipmap.jyyx_35);
        } else if (uriStr.contains("resources_images_36")) {
            backgroundImageView.setImageResource(R.mipmap.jyyx_36);
        } else if (uriStr.contains("resources_images_37")) {
            backgroundImageView.setImageResource(R.mipmap.jyyx_37);
        } else if (uriStr.contains("resources_images_38")) {
            backgroundImageView.setImageResource(R.mipmap.jyyx_38);
        } else if (uriStr.contains("resources_images_46")) {
            backgroundImageView.setImageResource(R.mipmap.jyyx_46);
        }
    }
}

