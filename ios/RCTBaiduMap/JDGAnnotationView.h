//
//  JDGAnnotaionView.h
//  RCTBaiduMap
//
//  Created by JDG on 2017/4/17.
//  Copyright © 2017年 lovebing.org. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <BaiduMapAPI_Map/BMKAnnotationView.h>
#import <BaiduMapAPI_Map/BMKPointAnnotation.h>
#import "JDGAnnotation.h"
@interface JDGAnnotationView : BMKAnnotationView

- (void)customizedWithAnnotation:(JDGAnnotation *)anno;

- (void)setFrontTitle:(NSString *)title;
- (void)setFrontSubtitle:(NSString *)subtitle;

- (void)setBackgroundHeading:(CLLocationDegrees)heading;
- (void)setBackgroundImage:(UIImage *)image;
- (void)setBackgroundAnimateImages:(NSArray<UIImage*>*) animateBackgroundImages;
- (void)setBackgroundAnimating:(BOOL)isAnimating;
- (void)setBackgroundAnimateDuration:(NSTimeInterval) duration;

@end
