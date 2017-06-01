//
//  RCTBaiduMapViewManager.m
//  RCTBaiduMap
//
//  Created by lovebing on Aug 6, 2016.
//  Copyright © 2016 lovebing.org. All rights reserved.
//

#import "RCTBaiduMapViewManager.h"

@implementation RCTBaiduMapViewManager {
    RCTBaiduMapView * _mapView;
}

RCT_EXPORT_MODULE(RCTBaiduMapView)

RCT_EXPORT_VIEW_PROPERTY(mapType, int)
RCT_EXPORT_VIEW_PROPERTY(zoom, float)
RCT_EXPORT_VIEW_PROPERTY(trafficEnabled, BOOL)
RCT_EXPORT_VIEW_PROPERTY(baiduHeatMapEnabled, BOOL)
RCT_EXPORT_VIEW_PROPERTY(marker, NSDictionary*)
RCT_EXPORT_VIEW_PROPERTY(markers, NSArray*)
RCT_EXPORT_VIEW_PROPERTY(autoShowAllAnnotations,BOOL)
RCT_EXPORT_VIEW_PROPERTY(onChange, RCTBubblingEventBlock)

RCT_CUSTOM_VIEW_PROPERTY(center, CLLocationCoordinate2D, RCTBaiduMapView) {
    [view setCenterCoordinate:json ? [RCTConvert CLLocationCoordinate2D:json] : defaultView.centerCoordinate];
}



+(void)initSDK:(NSString*)key {
    
    BMKMapManager* _mapManager = [[BMKMapManager alloc]init];
    BOOL ret = [_mapManager start:key  generalDelegate:nil];
    if (!ret) {
        NSLog(@"manager start failed!");
    }
}

- (UIView *)view {
    _mapView = [[RCTBaiduMapView alloc] init];
    _mapView.delegate = self;
    
    return _mapView;
}

-(void)mapview:(BMKMapView *)mapView
 onDoubleClick:(CLLocationCoordinate2D)coordinate {
    NSDictionary* event = @{
                            @"type": @"onMapDoubleClick",
                            @"params": @{
                                    @"latitude": @(coordinate.latitude),
                                    @"longitude": @(coordinate.longitude)
                                    }
                            };
    [self sendEvent:mapView params:event];
}

-(void)mapView:(BMKMapView *)mapView
onClickedMapBlank:(CLLocationCoordinate2D)coordinate {
    NSDictionary* event = @{
                            @"type": @"onMapClick",
                            @"params": @{
                                    @"latitude": @(coordinate.latitude),
                                    @"longitude": @(coordinate.longitude)
                                    }
                            };
    [self sendEvent:mapView params:event];
}

-(void)mapViewDidFinishLoading:(BMKMapView *)mapView {
    NSDictionary* event = @{
                            @"type": @"onMapLoaded",
                            @"params": @{}
                            };
    [self sendEvent:mapView params:event];
    if (_mapView.autoShowAllAnnotations) {
        [_mapView showAllAnnos];
    }
}

-(void)mapView:(BMKMapView *)mapView
didSelectAnnotationView:(BMKAnnotationView *)view {
    if ([view.annotation isKindOfClass:[JDGAnnotation class]]) {
        JDGAnnotation * anno = (JDGAnnotation *)view.annotation;
        NSDictionary * event = @{
                                @"type": @"onMarkerClick",
                                @"params": @{
                                        @"id": anno.identifier,
                                        @"title": anno.title,
                                        @"frontTitle": anno.frontTitle,
                                        @"position": @{
                                                @"latitude": @(anno.coordinate.latitude),
                                                @"longitude": @(anno.coordinate.longitude)
                                                }
                                        }
                                };
        [self sendEvent:mapView params:event];
    }
}

- (void) mapView:(BMKMapView *)mapView
 onClickedMapPoi:(BMKMapPoi *)mapPoi {
    NSDictionary* event = @{
                            @"type": @"onMapPoiClick",
                            @"params": @{
                                    @"name": mapPoi.text,
                                    @"uid": mapPoi.uid,
                                    @"latitude": @(mapPoi.pt.latitude),
                                    @"longitude": @(mapPoi.pt.longitude)
                                    }
                            };
    [self sendEvent:mapView params:event];
}

- (BMKAnnotationView *)mapView:(BMKMapView *)mapView viewForAnnotation:(id <BMKAnnotation>)annotation {
    if ([annotation isKindOfClass:[JDGAnnotation class]]) {
        JDGAnnotation * anno = (JDGAnnotation *)annotation;
        JDGAnnotationView *newAnnotationView = [[JDGAnnotationView alloc] initWithAnnotation:anno reuseIdentifier:anno.identifier];
        [newAnnotationView customizedWithAnnotation:annotation];
        return newAnnotationView;
    }
    return nil;
}

-(void)mapStatusDidChanged: (BMKMapView *)mapView	 {
    CLLocationCoordinate2D targetGeoPt = [mapView getMapStatus].targetGeoPt;
    NSDictionary* event = @{
                            @"type": @"onMapStatusChange",
                            @"params": @{
                                    @"target": @{
                                            @"latitude": @(targetGeoPt.latitude),
                                            @"longitude": @(targetGeoPt.longitude)
                                            },
                                    @"zoom": @"",
                                    @"overlook": @""
                                    }
                            };
    [self sendEvent:mapView params:event];
}

- (void)mapView:(BMKMapView *)mapView annotationViewForBubble:(BMKAnnotationView *)view {
    JDGAnnotation * anno = view.annotation;
    NSDictionary* event = @{
                            @"type": @"onMapAnnotationBubbleClick",
                            @"params": @{
                                    @"target": @{
                                            @"latitude": @(anno.coordinate.latitude),
                                            @"longitude": @(anno.coordinate.longitude),
                                            @"identifier": anno.identifier,
                                            },
                                    }
                            };
    [self sendEvent:mapView params:event];
}

- (void)sendEvent:(BMKMapView *)bMapView params:(NSDictionary *) params {
    RCTBaiduMapView *mapView = (RCTBaiduMapView*)bMapView;
    if (!mapView.onChange) {
        return;
    }
    mapView.onChange(params);
}

@end
