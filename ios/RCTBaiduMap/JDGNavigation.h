//
//  JDGNavigation.h
//  RCTBaiduMap
//
//  Created by JDG on 2017/5/2.
//  Copyright © 2017年 lovebing.org. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <CoreLocation/CoreLocation.h>
#import "BaseModule.h"

@interface JDGNavigation : BaseModule

+ (void)initSDK:(NSString*)key;

- (void)startNavigationFromMyLocationTo:(double)destPosLat long:(double)destPosLong;

- (void)startNavigationFrom:(double)startPosLat lng:(double)startPosLong  to:(double)destPosLat lng:(double)destPosLong;

@end
