//
//  BNLocation.h
//  baiduNaviSDK
//
//  Created by Baidu on 14/12/29.
//  Copyright (c) 2014年 baidu. All rights reserved.
//

#import <CoreLocation/CLLocation.h>

@interface BNLocation : NSObject

/**
 *  wgs84ll格式的经纬度
 */
@property (assign, nonatomic) CLLocationCoordinate2D coordinate;

/**
 *  海拔，单位为米
 */
@property (assign, nonatomic) CLLocationDistance altitude;

/**
 *  水平精度，单位为米
 */
@property (assign, nonatomic) CLLocationAccuracy horizontalAccuracy;

/**
 *  垂直精度，单位为米
 */
@property (assign, nonatomic) CLLocationAccuracy verticalAccuracy;

/**
 *  方向角度，单位为度，范围位0.0-359.9，0表示正北
 */
@property (assign, nonatomic) CLLocationDirection course;

/**
 *  速度，单位为米/秒
 */
@property (assign, nonatomic) CLLocationSpeed speed;

@property (strong, nonatomic) NSDate* timestamp;

@end


@interface BNHeading : NSObject

@property(assign, nonatomic) CLLocationDirection magneticHeading;

/**
 *  旋转角精度
 */
@property (assign, nonatomic) CLLocationDirection headingAccuracy;

/**
 *  旋转角度大小，单位位度，范围位0-359.9，0表示正北
 */
@property (assign, nonatomic) CLLocationDirection trueHeading;

@end
