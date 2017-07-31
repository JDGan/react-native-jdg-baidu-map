package org.lovebing.reactnative.baidumap;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;

/**
 * 百度导航
 * <p>
 * Created by Letto. on 2017/7/6 0006.
 */

public class NaviModule extends BaseModule {
    private static final String TAG = "NaviModule";

    public NaviModule(ReactApplicationContext reactContext) {
        super(reactContext);
        context = reactContext;
    }

    @Override
    public String getName() {
        return "BaiduNavigationModule";
    }

    /**
     * 从当前位置导航到指定地址
     */
    @ReactMethod
    public void startNavigationFromMyLocationTo(double toLat, double toLong) {
        Log.i(TAG, "startNavigationFromMyLocationTo");
        // 驾车导航
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("baidumap://map/navi?location=" + toLat + "," + toLong));
        context.startActivity(intent);
        WritableMap writableMap = Arguments.createMap();
        sendEvent("routePlanDidFinished", writableMap);
    }

    /**
     * 从指定位置导航到指定地址
     */
    @ReactMethod
    public void startNavigationFrom(double fromLat, double fromLong, double toLat, double toLong) {
        Log.i(TAG, "startNavigationFrom");
        // 驾车导航
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("baidumap://map/navi?location=origin=" + fromLat + "," + fromLong
                + "&destination=" + toLat + "," + toLong));
        context.startActivity(intent);
        WritableMap writableMap = Arguments.createMap();
        sendEvent("routePlanDidFinished", writableMap);
    }
}
