package org.lovebing.reactnative.baidumap.model;

import java.util.ArrayList;

/**
 * 标记模型
 * <p>
 * Created by Letto. on 2017/6/23.
 */

public class CoreMarkerModel {

    /**
     * 经度
     */
    private double longitude;
    /**
     * 动画时长（秒）
     */
    private double animateBackgroundDuration;
    /**
     * 背景图片
     */
    private CoreBackgroundImageModel backgroundImage;
    /**
     * 副标题
     */
    private String frontSubtitle;
    /**
     * 标题
     */
    private String frontTitle;
    /**
     * 纬度
     */
    private double latitude;
    /**
     * 是否播放动画
     */
    private boolean isBackgroundAnimating;
    /**
     * 标注标题
     */
    private String title;
    /**
     * id
     */
    private String id;
    /**
     * 朝向
     */
    private double backgroundImageHeading;
    /**
     * 图片（前景）
     */
    private CoreBackgroundImageModel frontImage;
    /**
     * 动画图片集合
     */
    private ArrayList<CoreBackgroundImageModel> animateBackgroundImages;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAnimateBackgroundDuration() {
        return animateBackgroundDuration;
    }

    public void setAnimateBackgroundDuration(double animateBackgroundDuration) {
        this.animateBackgroundDuration = animateBackgroundDuration;
    }

    public CoreBackgroundImageModel getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(CoreBackgroundImageModel backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getFrontSubtitle() {
        return frontSubtitle;
    }

    public void setFrontSubtitle(String frontSubtitle) {
        this.frontSubtitle = frontSubtitle;
    }

    public String getFrontTitle() {
        return frontTitle;
    }

    public void setFrontTitle(String frontTitle) {
        this.frontTitle = frontTitle;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public boolean isBackgroundAnimating() {
        return isBackgroundAnimating;
    }

    public void setBackgroundAnimating(boolean backgroundAnimating) {
        isBackgroundAnimating = backgroundAnimating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBackgroundImageHeading() {
        return backgroundImageHeading;
    }

    public void setBackgroundImageHeading(double backgroundImageHeading) {
        this.backgroundImageHeading = backgroundImageHeading;
    }

    public CoreBackgroundImageModel getFrontImage() {
        return frontImage;
    }

    public void setFrontImage(CoreBackgroundImageModel frontImage) {
        this.frontImage = frontImage;
    }

    public ArrayList<CoreBackgroundImageModel> getAnimateBackgroundImages() {
        return animateBackgroundImages;
    }

    public void setAnimateBackgroundImages(ArrayList<CoreBackgroundImageModel> animateBackgroundImages) {
        this.animateBackgroundImages = animateBackgroundImages;
    }
}
