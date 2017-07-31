//
//  JDGAnnotation.h
//  RCTBaiduMap
//
//  Created by JDG on 2017/4/17.
//  Copyright © 2017年 lovebing.org. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>
#import <BaiduMapAPI_Map/BMKPointAnnotation.h>
@interface JDGAnnotation : BMKPointAnnotation

@property (nonatomic ,copy) NSString *identifier;

@property (nonatomic ,copy) NSString *frontTitle;
@property (nonatomic ,copy) NSString *frontSubtitle;

@property (nonatomic ,assign) CLLocationDegrees backgroundImageHeading;
@property (nonatomic ,strong) UIImage *frontImage;
@property (nonatomic ,strong) UIImage *backgroundImage;
@property (nonatomic ,assign) BOOL isBackgroundAnimating;
@property (nonatomic ,strong) NSArray<UIImage*>* animateBackgroundImages;
@property (nonatomic ,assign) NSTimeInterval animateBackgroundDuration;

- (void)customizedWithOptions: (NSDictionary *)options;

@end
