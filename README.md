
Baidu Map SDK modules and view for React Native(Android & IOS), support react native 0.30+

百度地图 React Native 模块，支持 react native 0.30+

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

### Usage 使用方法

    import { MapView, MapTypes, MapModule, Geolocation } from 'react-native-baidu-map

### Android is Not supported yet (sadly)
