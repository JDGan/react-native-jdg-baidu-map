//
//  RCTBaiduMap.h
//  RCTBaiduMap
//
//  Created by lovebing on 4/17/2016.
//  Copyright © 2016 lovebing.org. All rights reserved.
//

#ifndef RCTBaiduMapView_h
#define RCTBaiduMapView_h

#import "React/RCTViewManager.h"
#import <BaiduMapAPI_Map/BMKMapView.h>
#import <BaiduMapAPI_Base/BMKTypes.h>
#import <BaiduMapAPI_Utils/BMKGeometry.h>
#import "JDGAnnotationView.h"
#import "React/RCTConvert+CoreLocation.h"
#import <UIKit/UIKit.h>

@interface RCTBaiduMapView : BMKMapView <BMKMapViewDelegate>

@property (nonatomic, copy) RCTBubblingEventBlock onChange;

@property (nonatomic, assign) BOOL autoShowAllAnnotations;

-(void)setZoom:(float)zoom;
-(void)setCenterLatLng:(NSDictionary *)LatLngObj;
-(void)setMarker:(NSDictionary *)Options;

- (void)showAllAnnos;

@end

#endif
