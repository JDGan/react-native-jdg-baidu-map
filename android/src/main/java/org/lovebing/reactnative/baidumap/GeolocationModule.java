package org.lovebing.reactnative.baidumap;

import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.utils.CoordinateConverter;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import java.util.List;

/**
 * Created by lovebing on 2016/10/28.
 */
public class GeolocationModule extends BaseModule
        implements BDLocationListener, OnGetGeoCoderResultListener, OnGetPoiSearchResultListener {
    private static final String TAG = "GeolocationModule";
    private LocationClient locationClient;
    private static GeoCoder geoCoder;
    private PoiSearch mPoiSearch;

    public GeolocationModule(ReactApplicationContext reactContext) {
        super(reactContext);
        context = reactContext;
    }

    public String getName() {
        return "BaiduGeolocationModule";
    }


    private void initLocationClient() {
        LocationClientOption option = new LocationClientOption();
        option.setCoorType("bd09ll");
        option.setIsNeedAddress(true);
//        option.setIsNeedAltitude(true);
        option.setIsNeedLocationDescribe(true);
        option.setOpenGps(true);
        locationClient = new LocationClient(context.getApplicationContext());
        locationClient.setLocOption(option);
        Log.i("locationClient", "locationClient");
        locationClient.registerLocationListener(this);
    }

    /**
     * @return
     */
    protected GeoCoder getGeoCoder() {
        if (geoCoder != null) {
            geoCoder.destroy();
        }
        geoCoder = GeoCoder.newInstance();
        geoCoder.setOnGetGeoCodeResultListener(this);
        return geoCoder;
    }

    protected PoiSearch getPoiSearch() {
        if (mPoiSearch != null) {
            mPoiSearch.destroy();
        }
        mPoiSearch = PoiSearch.newInstance();
        mPoiSearch.setOnGetPoiSearchResultListener(this);
        return mPoiSearch;
    }

    /**
     * @param sourceLatLng
     * @return
     */
    protected LatLng getBaiduCoorFromGPSCoor(LatLng sourceLatLng) {
        CoordinateConverter converter = new CoordinateConverter();
        converter.from(CoordinateConverter.CoordType.GPS);
        converter.coord(sourceLatLng);
        LatLng desLatLng = converter.convert();
        return desLatLng;

    }

    @ReactMethod
    public void getCurrentPosition() {
        if (locationClient == null) {
            initLocationClient();
        }
        Log.i("getCurrentPosition", "getCurrentPosition");
        locationClient.start();
    }

    @ReactMethod
    public void geocode(String city, String addr) {
        getGeoCoder().geocode(new GeoCodeOption()
                .city(city).address(addr));
    }

    @ReactMethod
    public void reverseGeoCode(double lat, double lng) {
        getGeoCoder().reverseGeoCode(new ReverseGeoCodeOption()
                .location(new LatLng(lat, lng)));
    }

    @ReactMethod
    public void reverseGeoCodeGPS(double lat, double lng) {
        getGeoCoder().reverseGeoCode(new ReverseGeoCodeOption()
                .location(getBaiduCoorFromGPSCoor(new LatLng(lat, lng))));
    }

    @ReactMethod
    public void searchNearbyPoi(double lat, double lng, String keyword) {
        getPoiSearch().searchNearby(new PoiNearbySearchOption()
                .keyword(keyword)
                .location(new LatLng(lat, lng))
                .radius(100000));
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        WritableMap params = Arguments.createMap();
        Log.d(TAG, "latitude = " + bdLocation.getLatitude());
        Log.d(TAG, "longitude = " + bdLocation.getLongitude());
        params.putDouble("latitude", bdLocation.getLatitude());
        params.putDouble("longitude", bdLocation.getLongitude());
        params.putDouble("direction", bdLocation.getDirection());
        params.putDouble("altitude", bdLocation.getAltitude());
        params.putDouble("radius", bdLocation.getRadius());
        params.putString("address", bdLocation.getAddrStr());
        params.putString("countryCode", bdLocation.getCountryCode());
        params.putString("country", bdLocation.getCountry());
        params.putString("province", bdLocation.getProvince());
        params.putString("cityCode", bdLocation.getCityCode());
        params.putString("city", bdLocation.getCity());
        params.putString("district", bdLocation.getDistrict());
        params.putString("street", bdLocation.getStreet());
        params.putString("streetNumber", bdLocation.getStreetNumber());
        params.putString("buildingId", bdLocation.getBuildingID());
        params.putString("buildingName", bdLocation.getBuildingName());
        Log.i("onReceiveLocation", "onGetCurrentLocationPosition");
        sendEvent("onGetCurrentLocationPosition", params);
        locationClient.stop();
    }

    @Override
    public void onGetGeoCodeResult(GeoCodeResult result) {
        WritableMap params = Arguments.createMap();
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            params.putInt("errcode", -1);
        } else {
            params.putDouble("latitude", result.getLocation().latitude);
            params.putDouble("longitude", result.getLocation().longitude);
        }
        Log.e(TAG, "latitude = " + result.getLocation().latitude);
        Log.e(TAG, "longitude = " + result.getLocation().longitude);
        sendEvent("onGetGeoCodeResult", params);
    }

    @Override
    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
        WritableMap params = Arguments.createMap();
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
            params.putInt("errcode", -1);
            if (result == null) {
                params.putString("errmsg", "返回无结果");
            } else {
                params.putString("errmsg", result.error.name());
            }

        } else {
            ReverseGeoCodeResult.AddressComponent addressComponent = result.getAddressDetail();
            params.putString("address", result.getAddress());
            params.putString("province", addressComponent.province);
            params.putString("city", addressComponent.city);
            params.putString("district", addressComponent.district);
            params.putString("streetName", addressComponent.street);
            params.putString("streetNumber", addressComponent.streetNumber);
            params.putDouble("latitude", result.getLocation().latitude);
            params.putDouble("longitude", result.getLocation().longitude);
        }

        sendEvent("onGetReverseGeoCodeResult", params);
    }

    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        WritableMap results = Arguments.createMap();
        if (poiResult == null || poiResult.error != SearchResult.ERRORNO.NO_ERROR) {
            results.putInt("errcode", -1);
            if (poiResult == null) {
                results.putString("errmsg", "返回无结果");
            } else {
                results.putString("errmsg", poiResult.error.name());
            }

        } else {
            List<PoiInfo> result = poiResult.getAllPoi();
            WritableArray writableArray = Arguments.createArray();
            int size = result.size();
            for (int i = 0; i < size; i++) {
                WritableMap writableMap = Arguments.createMap();
                writableMap.putString("name", result.get(i).name);
                writableMap.putString("address", result.get(i).address);
                writableMap.putString("city", result.get(i).city);
                writableMap.putString("phone", result.get(i).phoneNum);
                writableMap.putDouble("longitude", result.get(i).location.longitude);
                writableMap.putDouble("latitude", result.get(i).location.latitude);
                writableArray.pushMap(writableMap);
            }
            results.putArray("results", writableArray);
        }
        sendEvent("onGetPoiResult", results);
    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

    }

    @Override
    public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

    }
}
