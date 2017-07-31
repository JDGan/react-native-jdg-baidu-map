package org.lovebing.reactnative.baidumap;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MapViewLayoutParams;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.model.LatLng;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import org.lovebing.reactnative.baidumap.utils.MarkerUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by lovebing on 12/20/2015.
 */
public class BaiduMapViewManager extends ViewGroupManager<MapView> {
    private static final String TAG = "BaiduMapViewManager";
    private static final String REACT_CLASS = "RCTBaiduMapView";

    private ThemedReactContext mReactContext;

    private ReadableArray childrenPoints;
    private HashMap<String, Marker> mMarkerMap = new HashMap<>();
    private HashMap<String, List<Marker>> mMarkersMap = new HashMap<>();

    public String getName() {
        return REACT_CLASS;
    }


    public void initSDK(Context context) {
        SDKInitializer.initialize(context);
    }

    public MapView createViewInstance(ThemedReactContext context) {
        mReactContext = context;
        MapView mapView = new MapView(context);
        setListeners(mapView);
        return mapView;
    }

    @Override
    public void addView(MapView parent, View child, int index) {
        if (childrenPoints != null) {
            Point point = new Point();
            ReadableArray item = childrenPoints.getArray(index);
            if (item != null) {
                point.set(item.getInt(0), item.getInt(1));
                MapViewLayoutParams mapViewLayoutParams = new MapViewLayoutParams
                        .Builder()
                        .layoutMode(MapViewLayoutParams.ELayoutMode.absoluteMode)
                        .point(point)
                        .build();
                parent.addView(child, mapViewLayoutParams);
            }
        }

    }

    @ReactProp(name = "zoomControlsVisible")
    public void setZoomControlsVisible(MapView mapView, boolean zoomControlsVisible) {
        mapView.showZoomControls(zoomControlsVisible);
    }

    @ReactProp(name = "trafficEnabled")
    public void setTrafficEnabled(MapView mapView, boolean trafficEnabled) {
        mapView.getMap().setTrafficEnabled(trafficEnabled);
    }

    @ReactProp(name = "baiduHeatMapEnabled")
    public void setBaiduHeatMapEnabled(MapView mapView, boolean baiduHeatMapEnabled) {
        mapView.getMap().setBaiduHeatMapEnabled(baiduHeatMapEnabled);
    }

    @ReactProp(name = "mapType")
    public void setMapType(MapView mapView, int mapType) {
        mapView.getMap().setMapType(mapType);
    }

    @ReactProp(name = "zoom")
    public void setZoom(MapView mapView, float zoom) {
        Log.d(TAG, "zoom = " + zoom);
        MapStatus mapStatus = new MapStatus.Builder().zoom(zoom).build();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
        mapView.getMap().setMapStatus(mapStatusUpdate);
    }

    @ReactProp(name = "center")
    public void setCenter(MapView mapView, ReadableMap position) {
        if (position != null) {
            double latitude = 0;
            double longitude = 0;
            Log.d(TAG, "position = " + position.toString());
            if (position.hasKey("latitude") && !position.isNull("latitude")) {
                latitude = position.getDouble("latitude");
            }
            if (position.hasKey("longitude") && !position.isNull("longitude")) {
                longitude = position.getDouble("longitude");
            }
            if (latitude != 0 && longitude != 0) {
                LatLng point = new LatLng(latitude, longitude);
                MapStatus mapStatus = new MapStatus.Builder()
                        .target(point)
                        .build();
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mapStatus);
                mapView.getMap().setMapStatus(mapStatusUpdate);
            }
        }
    }

    @ReactProp(name = "marker")
    public void setMarker(MapView mapView, ReadableMap option) {
        if (option != null) {
            String key = "marker_" + mapView.getId();
            Marker marker = mMarkerMap.get(key);
            marker.setTitle(option.getString("id"));
//            if (marker != null) {
//                MarkerUtil.updateMaker(marker, option);
//            } else {
            marker = MarkerUtil.addMarker(mapView, option, mReactContext);
            mMarkerMap.put(key, marker);
//            }
        }
    }

    @ReactProp(name = "markers")
    public void setMarkers(MapView mapView, ReadableArray options) {
        final BaiduMap map = mapView.getMap();
        map.clear();
        String key = "markers_" + mapView.getId();
        List<Marker> markers = mMarkersMap.get(key);
        if (markers == null) {
            markers = new ArrayList<>();
        }
        for (int i = 0; i < options.size(); i++) {
            ReadableMap option = options.getMap(i);
//            if (markers.size() > i + 1 && markers.get(i) != null) {
//                MarkerUtil.updateMaker(markers.get(i), option);
//            } else {
            markers.add(i, MarkerUtil.addMarker(mapView, option, mReactContext));
//            }
        }
        if (options.size() < markers.size()) {
            int start = markers.size() - 1;
            int end = options.size();
            for (int i = start; i >= end; i--) {
                markers.get(i).remove();
                markers.remove(i);
            }
        }
        mMarkersMap.put(key, markers);
    }

    @ReactProp(name = "childrenPoints")
    public void setChildrenPoints(MapView mapView, ReadableArray childrenPoints) {
        this.childrenPoints = childrenPoints;
    }

    /**
     * @param mapView
     */
    private void setListeners(final MapView mapView) {
        final BaiduMap map = mapView.getMap();
        map.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {

            private WritableMap getEventParams(MapStatus mapStatus) {
                WritableMap writableMap = Arguments.createMap();
                WritableMap target = Arguments.createMap();
                target.putDouble("latitude", mapStatus.target.latitude);
                target.putDouble("longitude", mapStatus.target.longitude);
                writableMap.putMap("target", target);
                writableMap.putDouble("zoom", mapStatus.zoom);
                writableMap.putDouble("overlook", mapStatus.overlook);
                return writableMap;
            }

            @Override
            public void onMapStatusChangeStart(MapStatus mapStatus) {
                sendEvent(mapView, "onMapStatusChangeStart", getEventParams(mapStatus));
            }

            @Override
            public void onMapStatusChange(MapStatus mapStatus) {
                sendEvent(mapView, "onMapStatusChange", getEventParams(mapStatus));
            }

            @Override
            public void onMapStatusChangeFinish(MapStatus mapStatus) {
                sendEvent(mapView, "onMapStatusChangeFinish", getEventParams(mapStatus));
            }
        });

        map.setOnMapLoadedCallback(new BaiduMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                sendEvent(mapView, "onMapLoaded", null);
            }
        });

        map.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                WritableMap writableMap = Arguments.createMap();
                writableMap.putDouble("latitude", latLng.latitude);
                writableMap.putDouble("longitude", latLng.longitude);
                sendEvent(mapView, "onMapClick", writableMap);
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                WritableMap writableMap = Arguments.createMap();
                writableMap.putString("name", mapPoi.getName());
                writableMap.putString("uid", mapPoi.getUid());
                writableMap.putDouble("latitude", mapPoi.getPosition().latitude);
                writableMap.putDouble("longitude", mapPoi.getPosition().longitude);
                sendEvent(mapView, "onMapPoiClick", writableMap);
                return true;
            }
        });
        map.setOnMapDoubleClickListener(new BaiduMap.OnMapDoubleClickListener() {
            @Override
            public void onMapDoubleClick(LatLng latLng) {
                WritableMap writableMap = Arguments.createMap();
                writableMap.putDouble("latitude", latLng.latitude);
                writableMap.putDouble("longitude", latLng.longitude);
                sendEvent(mapView, "onMapDoubleClick", writableMap);
            }
        });

        map.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Bundle bundle = marker.getExtraInfo();
                WritableMap writableMap = Arguments.createMap();
                WritableMap position = Arguments.createMap();
                writableMap.putString("id", bundle.getString("id"));
                writableMap.putString("title", bundle.getString("title"));
                writableMap.putString("frontTitle", bundle.getString("frontTitle"));
                position.putDouble("longitude", bundle.getDouble("longitude"));
                position.putDouble("latitude", bundle.getDouble("latitude"));
                writableMap.putMap("position", position);
                String id = bundle.getString("id");
                double longitude = bundle.getDouble("longitude");
                double latitude = bundle.getDouble("latitude");
                OnInfoWindowsListener listener = new OnInfoWindowsListener(map, mapView, id, longitude, latitude);
                TextView markerText = new TextView(mapView.getContext());
                markerText.setBackgroundResource(R.drawable.popup);
                markerText.setGravity(Gravity.CENTER);
                markerText.setPadding(32, 32, 32, 32);
                markerText.setText(writableMap.getString("title"));
                InfoWindow infoWindow = new InfoWindow(BitmapDescriptorFactory.fromView(markerText), marker.getPosition(), -80, listener);
                markerText.setVisibility(View.GONE);
                map.showInfoWindow(infoWindow);
                sendEvent(mapView, "onMarkerClick", writableMap);
                return true;
            }
        });

    }

    /**
     * @param eventName
     * @param params
     */
    private void sendEvent(MapView mapView, String eventName, @Nullable WritableMap params) {
        WritableMap event = Arguments.createMap();
        event.putMap("params", params);
        event.putString("type", eventName);
        mReactContext
                .getJSModule(RCTEventEmitter.class)
                .receiveEvent(mapView.getId(),
                        "topChange",
                        event);
    }

    private class OnInfoWindowsListener implements InfoWindow.OnInfoWindowClickListener {
        private String id;
        private double longitude;
        private double latitude;
        private MapView mapView;
        private BaiduMap map;

        public OnInfoWindowsListener(BaiduMap map, MapView mapView, String id, double longitude, double latitude) {
            this.map = map;
            this.mapView = mapView;
            this.id = id;
            this.longitude = longitude;
            this.latitude = latitude;
        }

        @Override
        public void onInfoWindowClick() {
            WritableMap target = Arguments.createMap();
            WritableMap writableMap = Arguments.createMap();
            target.putDouble("latitude", latitude);
            target.putDouble("longitude", longitude);
            target.putString("identifier", id);
            writableMap.putMap("target", target);
            sendEvent(mapView, "onMapAnnotationBubbleClick", writableMap);
            map.hideInfoWindow();
        }
    }
}
