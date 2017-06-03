
Baidu Map SDK modules and view for React Native(Android & IOS), support react native 0.40+

百度地图 React Native 模块，支持 react native 0.40+

### Install 安装
    npm install react-native-jdg-baidu-map --save
### Import 导入

#### Android Studio
- settings.gradle `
include ':react-native-jdg-baidu-map'
project(':react-native-jdg-baidu-map').projectDir = new File(settingsDir, '../node_modules/react-native-jdg-baidu-map/android')`

- build.gradle `compile project(':react-native-jdg-baidu-map')`

- MainApplication`new BaiduMapPackage(getApplicationContext())`
- AndroidMainifest.xml `<meta-data
            android:name="com.baidu.lbsapi.API_KEY" android:value="xx"/>`

#### Xcode
- Project navigator->Libraries->Add Files to 选择 react-native-jdg-baidu-map/ios/RCTBaiduMap.xcodeproj
- Project navigator->Build Phases->Link Binary With Libraries 加入 libRCTBaiduMap.a
- Project navigator->Build Settings->Search Paths， Framework search paths 添加 $(SRCROOT)/../node_modules/react-native-jdg-baidu-map/ios/lib
    Header search paths 添加 $(SRCROOT)/../node_modules/react-native-jdg-baidu-map/ios/RCTBaiduMap/
- 添加依赖, react-native-jdg-baidu-map/ios/lib 下的全部 framwork， CoreLocation.framework和QuartzCore.framework、OpenGLES.framework、SystemConfiguration.framework、CoreGraphics.framework、Security.framework、libsqlite3.0.tbd（xcode7以前为 libsqlite3.0.dylib）、CoreTelephony.framework 、libstdc++.6.0.9.tbd（xcode7以前为libstdc++.6.0.9.dylib）
- 添加 BaiduMapAPI_Map.framework/Resources/mapapi.bundle
- 添加 BaiduNaviSDK/baiduNaviSDK.bundle、BaiduNaviSDK/baiduNaviSDK.xcassets

# 注意！导航库需要自行下载，文件太大，不能放到git上，导航版本为v3.1.0
下载后的libbaiduNaviSDK请放到 react-native-jdg-baidu-map/ios/lib/BaiduNaviSDK/ 下

- 其它一些注意事项可参考百度地图LBS文档

##### AppDelegate.m init 初始化

    #import "RCTBaiduMapViewManager.h"
    #import "JDGNavigation.h"
    - (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
    {
        ...
        [RCTBaiduMapViewManager initSDK:@"api key"];
        [JDGNavigation initSDK:@"api key"];
        ...
    }

### Android is Not supported yet (sadly)

***

### Usage 使用方法
`
import {
  JDGMapView,
  JDGMapTypes,
  JDGGeolocation,
  JDGNavigation
} from 'react-native-jdg-baidu-map';
`

| JDGMapView                 | PropTypes          |  Description |
| -------------------------- | :----------------: | :----------- |
|         zoomControlsVisible|      PropTypes.bool|是否显示缩放控件|
|              trafficEnabled|      PropTypes.bool|是否显示实时交通路况|
|         baiduHeatMapEnabled|      PropTypes.bool|是否显示热力|
|                     mapType|    PropTypes.number|地图类型|
|                        zoom|    PropTypes.number|放大系数|
|                      center|    PropTypes.object|中心点经纬度|
|                      marker|    PropTypes.object|单个地图标注|
|                     markers|     PropTypes.array|多个地图标注|
|              childrenPoints|     PropTypes.array|for Android Only|
|      autoShowAllAnnotations|      PropTypes.bool|是否显示所有标注，自适应全显示|
|      onMapStatusChangeStart|      PropTypes.func|地图状态变更开始回调|
|           onMapStatusChange|      PropTypes.func|地图状态变更回调|
|     onMapStatusChangeFinish|      PropTypes.func|地图状态变更完成回调|
|                 onMapLoaded|      PropTypes.func|地图加载完成回调|
|                  onMapClick|      PropTypes.func|地图单击回调|
|            onMapDoubleClick|      PropTypes.func|地图双击回调|
|               onMarkerClick|      PropTypes.func|地图标注点击回调|
|               onMapPoiClick|      PropTypes.func|地图POI点击回调|
|  onMapAnnotationBubbleClick|      PropTypes.func|地图标注气泡点击回调|

