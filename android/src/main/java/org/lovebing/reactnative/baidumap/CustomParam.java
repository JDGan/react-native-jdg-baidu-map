package com.letto.test.baidumapdemo.npm;

import com.baidu.mapapi.map.OverlayOptions;

import java.util.ArrayList;

/**
 * Created by Administrator. on 2017/4/25 0025.
 */

public class CustomParam extends OverlayOptions {
    private String identifier;
    private String frontTitle;
    private String frontSubtitle;
    private float  backgroundImageHeading;
    private String backgroundImage;
    private boolean isBackgroundAnimating;
    private ArrayList<String> animateBackgroundImages;
    private double animateBackgroundDuration;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getFrontTitle() {
        return frontTitle;
    }

    public void setFrontTitle(String frontTitle) {
        this.frontTitle = frontTitle;
    }

    public String getFrontSubtitle() {
        return frontSubtitle;
    }

    public void setFrontSubtitle(String frontSubtitle) {
        this.frontSubtitle = frontSubtitle;
    }

    public float getBackgroundImageHeading() {
        return backgroundImageHeading;
    }

    public void setBackgroundImageHeading(float backgroundImageHeading) {
        this.backgroundImageHeading = backgroundImageHeading;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public boolean isBackgroundAnimating() {
        return isBackgroundAnimating;
    }

    public void setBackgroundAnimating(boolean backgroundAnimating) {
        isBackgroundAnimating = backgroundAnimating;
    }

    public ArrayList<String> getAnimateBackgroundImages() {
        return animateBackgroundImages;
    }

    public void setAnimateBackgroundImages(ArrayList<String> animateBackgroundImages) {
        this.animateBackgroundImages = animateBackgroundImages;
    }

    public double getAnimateBackgroundDuration() {
        return animateBackgroundDuration;
    }

    public void setAnimateBackgroundDuration(double animateBackgroundDuration) {
        this.animateBackgroundDuration = animateBackgroundDuration;
    }
}
