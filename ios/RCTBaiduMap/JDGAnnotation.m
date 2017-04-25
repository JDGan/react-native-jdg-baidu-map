//
//  JDGAnnotation.m
//  RCTBaiduMap
//
//  Created by JDG on 2017/4/17.
//  Copyright © 2017年 lovebing.org. All rights reserved.
//

#import "JDGAnnotation.h"
#import "RCTConvert+CoreLocation.h"

@implementation JDGAnnotation

- (void)customizedWithOptions:(NSDictionary *)options {
    self.identifier = [RCTConvert NSString:option[@"id"]];
    self.frontTitle = [RCTConvert NSString:option[@"frontTitle"]];
    self.frontSubtitle = [RCTConvert NSString:option[@"frontSubtitle"]];
    self.backgroundImageHeading = [RCTConvert double:option[@"backgroundImageHeading"]];
    self.backgroundImage = [RCTConvert UIImage:option[@"backgroundImage"]];
    self.isBackgroundAnimating = [RCTConvert BOOL:option[@"isBackgroundAnimating"]];
    self.animateBackgroundImages = [RCTConvert NSArray:option[@"animateBackgroundImages"]];
    self.animateBackgroundDuration = [RCTConvert NSTimeInterval:option[@"animateBackgroundDuration"]];
    
    self.title = [RCTConvert NSString:option[@"title"]];
    CLLocationCoordinate2D loc = [self getCoordinateFromOption:options];
    self.coordinate = loc;
}

- (CLLocationCoordinate2D)getCoordinateFromOption:(NSDictionary *)option {
    double lat = [RCTConvert double:option[@"latitude"]];
    double lng = [RCTConvert double:option[@"longitude"]];
    CLLocationCoordinate2D coor;
    coor.latitude = lat;
    coor.longitude = lng;
    return coor;
}

@end
