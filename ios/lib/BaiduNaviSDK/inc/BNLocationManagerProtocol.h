//
//  BNLocationManagerProtocol.h
//  baiduNaviSDK
//
//  Created by Baidu on 11/26/13.
//  Copyright (c) 2013 baidu. All rights reserved.
//

#ifndef baiduNaviSDK_BNLocationManagerProtocol_h
#define baiduNaviSDK_BNLocationManagerProtocol_h

#import <CoreLocation/CoreLocation.h>
#import "BNLocation.h"


@protocol BNLocationManagerProtocol <CLLocationManagerDelegate>

@required

/**
 *  开始更新位置, startUpdate 不能先后连续执行两次，中间必须间隔一个 stopUpdate
 */
- (void)startUpdate;

/**
 *  停止更新位置
 */
- (void)stopUpdate;

/**
 *  获取最后一次成功定位的位置
 */
- (CLLocation *)getLastLocation;

/**
 *  获取城市ID
 *
 *  @param location 要获取城市ID的定位点,wgs84ll坐标
 *  @param success 启动成功后回调 success block
 *  @param fail    启动失败后回调 fail block
 */
- (void)getCityIDByLocation:(CLLocationCoordinate2D)location
                     sucess:(void (^)(int))sucess
                       fail:(void (^)(void))fail;

@optional

/**
 *  gps点是否来自外部,默认为NO,位置信息从iOS设备的gps模块获取。设置为YES时，gps的信息从currentLocation中获取
 */
@property (nonatomic, assign) BOOL gpsFromExternal;

/**
 *  当前位置，当前仅当gpsFromExternal=YES有效。当外部设置需要自定义gps数据时，可以通过设置该属性。
 */
@property (nonatomic, strong) CLLocation* currentLocation;

@end

#endif
