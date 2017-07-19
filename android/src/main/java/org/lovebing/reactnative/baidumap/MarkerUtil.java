package org.lovebing.reactnative.baidumap;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ThemedReactContext;

/**
 * Created by lovebing on Sept 28, 2016.
 */
public class MarkerUtil {
    private static final String TAG = "MarkerUtil";

    public static void updateMaker(Marker maker, ReadableMap option) {
        LatLng position = getLatLngFromOption(option);
        maker.setPosition(position);
        maker.setTitle(option.getString("frontTitle"));
    }

    public static Marker addMarker(MapView mapView, ReadableMap option, ThemedReactContext context) {
        Log.d(TAG, "option = " + option.toString());
        LatLng position = getLatLngFromOption(option);
        CustomMarkerView customMarkerView = new CustomMarkerView(context, option);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(getBitmapFromView(customMarkerView));
        MarkerOptions markerRed = new MarkerOptions()
                .position(position).icon(bitmapDescriptor);
        Bundle bundle = new Bundle();
        if (option.hasKey("id")) {
            bundle.putString("id", option.getString("id"));
        }
        if (option.hasKey("longitude") && !option.isNull("longitude")) {
            bundle.putDouble("longitude", option.getDouble("longitude"));
        }
        if (option.hasKey("latitude") && !option.isNull("latitude")) {
            bundle.putDouble("latitude", option.getDouble("latitude"));
        }
        if (option.hasKey("title")) {
            bundle.putString("title", option.getString("title"));
        }
        if (option.hasKey("frontTitle")) {
            bundle.putString("frontTitle", option.getString("frontTitle"));
        }
        Marker marker = (Marker) mapView.getMap().addOverlay(markerRed);
        marker.setExtraInfo(bundle);
        return marker;
    }


    private static LatLng getLatLngFromOption(ReadableMap option) {
        if (option.hasKey("latitude") && !option.isNull("latitude")) {
            Log.d(TAG, "option = " + option.toString());
            Log.d(TAG, "latitude type = " + option.getType("latitude"));
            double latitude = option.getDouble("latitude");
            double longitude = option.getDouble("longitude");
            return new LatLng(latitude, longitude);
        } else {
            return new LatLng(0, 0);
        }

    }

    private static Bitmap getBitmapFromView(View view) {
        view.destroyDrawingCache();
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.UNSPECIFIED);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.setDrawingCacheEnabled(true);
        Bitmap bitmap = view.getDrawingCache();
        return bitmap;
    }
}
