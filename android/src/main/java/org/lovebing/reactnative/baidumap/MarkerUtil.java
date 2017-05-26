package org.lovebing.reactnative.baidumap;


import android.util.Log;
import android.widget.Button;

import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.facebook.react.bridge.ReadableMap;

/**
 * Created by lovebing on Sept 28, 2016.
 */
public class MarkerUtil {

    public static void updateMaker(Marker maker, ReadableMap option) {
        LatLng position = getLatLngFromOption(option);
        maker.setPosition(position);
        maker.setTitle(option.getString("title"));
    }

    public static Marker addMarker(MapView mapView, ReadableMap option) {
//        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.color.black);
        LatLng position = getLatLngFromOption(option);
        CustomParam overlayOptions = new CustomParam();
//        overlayOptions.icon(bitmap)
//                .position(position)
//                .title(option.getString("title"));
        overlayOptions.setFrontTitle(option.getString("frontTitle"));
        overlayOptions.setFrontSubtitle(option.getString("frontSubtitle"));
//        overlayOptions.setBackgroundImageHeading(option.getFloat("backgroundImageHeading"));
//        overlayOptions.setBackgroundImage(option.getMap("backgroundImage").getString("uri"));
//        overlayOptions.setBackgroundAnimating(option.getBoolean("isBackgroundAnimating"));
//        overlayOptions.setAnimateBackgroundImages(option.getMap("animateBackgroundImages").getString("uri"));
//        overlayOptions.setAnimateBackgroundDuration(option.getDouble("animateBackgroundDuration"));

        Marker marker = (Marker) mapView.getMap().addOverlay(overlayOptions);
        return marker;
    }


    private static LatLng getLatLngFromOption(ReadableMap option) {
        double latitude = option.getDouble("latitude");
        double longitude = option.getDouble("longitude");
        return new LatLng(latitude, longitude);

    }
}
