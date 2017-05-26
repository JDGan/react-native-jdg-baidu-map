//
//  RCTBaiduMap.m
//  RCTBaiduMap
//
//  Created by lovebing on 4/17/2016.
//  Copyright © 2016 lovebing.org. All rights reserved.
//

#import "RCTBaiduMapView.h"
#import "React/Views/RCTConvert+CoreLocation.h"

@implementation RCTBaiduMapView {
    JDGAnnotation* _annotation;
    NSMutableArray* _annotations;
    
    BOOL _isAutoShowAnnos;
}

-(void)setZoom:(float)zoom {
    self.zoomLevel = zoom;
}

-(void)setCenterLatLng:(NSDictionary *)LatLngObj {
    double lat = [RCTConvert double:LatLngObj[@"lat"]];
    double lng = [RCTConvert double:LatLngObj[@"lng"]];
    CLLocationCoordinate2D point = CLLocationCoordinate2DMake(lat, lng);
    self.centerCoordinate = point;
}

-(void)setMarker:(NSDictionary *)option {
    NSLog(@"setMarker");
    if(option != nil) {
        if(_annotation == nil) {
            _annotation = [[JDGAnnotation alloc]init];
            [self addMarker:_annotation option:option];
        }
        else {
            [self updateMarker:_annotation option:option];
        }
    }
}

-(void)setMarkers:(NSArray *)markers {
    NSInteger markersCount = [markers count];
    if(_annotations == nil) {
        _annotations = [[NSMutableArray alloc] init];
    }
    [self removeAnnotations:_annotations];
    if(markers != nil) {
        for (int i = 0; i < markersCount; i++)  {
            NSDictionary *option = [markers objectAtIndex:i];
            
            JDGAnnotation *annotation = nil;
            if(i < [_annotations count]) {
                annotation = [_annotations objectAtIndex:i];
            }
            if(annotation == nil) {
                annotation = [[JDGAnnotation alloc]init];
                [self addMarker:annotation option:option];
                [_annotations addObject:annotation];
            }
            else {
                [self updateMarker:annotation option:option];
            }
        }

        NSInteger _annotationsCount = [_annotations count];
        if(markersCount < _annotationsCount) {
            NSInteger start = _annotationsCount - 1;
            for(NSInteger i = start; i >= markersCount; i--) {
                JDGAnnotation *annotation = [_annotations objectAtIndex:i];
                [self removeAnnotation:annotation];
                [_annotations removeObject:annotation];
            }
        }
    }
}

-(void)addMarker:(JDGAnnotation *)annotation option:(NSDictionary *)option {
    [self updateMarker:annotation option:option];
}

-(void)updateMarker:(JDGAnnotation *)annotation option:(NSDictionary *)option {
    [annotation customizedWithOptions:option];
    [self addAnnotation:annotation];
}

- (BOOL)autoShowAllAnnotations {
    return _isAutoShowAnnos;
}

-(void)setAutoShowAllAnnotations:(BOOL)isAutoShow{
    _isAutoShowAnnos = isAutoShow;
}

- (void)showAllAnnos {
    CLLocationCoordinate2D center = kCLLocationCoordinate2DInvalid;
    CLLocationDegrees mLat=-181,iLat=181,mlng=-181,ilng=181;
    for (JDGAnnotation *a in _annotations) {
        if (a.coordinate.latitude > mLat) {mLat = a.coordinate.latitude;}
        if (a.coordinate.latitude < iLat) {iLat = a.coordinate.latitude;}
        if (a.coordinate.longitude > mlng) {mlng = a.coordinate.longitude;}
        if (a.coordinate.longitude < ilng) {ilng = a.coordinate.longitude;}
    }
    center = CLLocationCoordinate2DMake((mLat+iLat)/2, (mlng+ilng)/2);
    
    if (CLLocationCoordinate2DIsValid(center)) {
        [self setVisibleCenter:center leastRadius:0];
    } else {
        [self setZoomLevel:10];
    }
}

-(void)setVisibleCenter:(CLLocationCoordinate2D)centerCoordinate leastRadius:(CLLocationDistance)leastRadius {
    CLLocationDistance distance = 0;
    CLLocationCoordinate2D furtherPoint = kCLLocationCoordinate2DInvalid;
    for (JDGAnnotation *a in _annotations) {
        CLLocationDistance nd = [self distanceBetween:a.coordinate :centerCoordinate];
        if (nd > distance) {
            distance = nd;
            furtherPoint = a.coordinate;
        }
    }
    if (CLLocationCoordinate2DIsValid(furtherPoint)) {
        if (distance > leastRadius) {
            [self setVisibleCenter:centerCoordinate latDelta:fabs(centerCoordinate.latitude-furtherPoint.latitude)*2.1 lonDelta: fabs(centerCoordinate.longitude-furtherPoint.longitude)*2.1];
        } else {
            [self setVisibleCenter:centerCoordinate latDistanceDelta:leastRadius*2.1 lonDistanceDelta:leastRadius*2.1];
        }
    } else {
        [self setCenterCoordinate:centerCoordinate];
    }
}

-(void) setVisibleCenter:(CLLocationCoordinate2D)centerCoordinate latDelta:(CLLocationDegrees)latDelta lonDelta:(CLLocationDegrees)lonDelta {
    BMKCoordinateSpan span = BMKCoordinateSpanMake(latDelta, lonDelta);
    BMKCoordinateRegion region = BMKCoordinateRegionMake(centerCoordinate, span);
    [self setRegion:region animated:YES];
}

-(void) setVisibleCenter:(CLLocationCoordinate2D) centerCoordinate latDistanceDelta:(CLLocationDistance)latDistanceDelta lonDistanceDelta:(CLLocationDistance)lonDistanceDelta {
    BMKCoordinateRegion region = BMKCoordinateRegionMakeWithDistance(centerCoordinate, latDistanceDelta, lonDistanceDelta);
    [self setRegion:region animated:YES];
}

-(CLLocationDistance) distanceBetween:(CLLocationCoordinate2D)locA  :(CLLocationCoordinate2D)locB {
    BMKMapPoint point1 = BMKMapPointForCoordinate(locA);
    BMKMapPoint point2 = BMKMapPointForCoordinate(locB);
    return BMKMetersBetweenMapPoints(point1,point2);
}

@end
