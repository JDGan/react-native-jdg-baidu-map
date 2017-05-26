//
//  BNaviModel.h
//  NaviDemo
//
//  Created by ssh on 16/12/20.
//  Copyright © 2016年 baidu. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface BNaviModel : NSObject

/**
 *  当前的导航controller
 */
@property (nonatomic, weak, readonly) UIViewController *naviViewController;

/**
 *  获取导航Model单例，该单例不可以释放
 */
+ (BNaviModel*)getInstance;

/**
 *  退出导航
 */
-(void)exitNavi;

/**
 *  导航中改变终点
 *
 *  @param endNode  要切换的终点
 */
- (void)resetNaviEndPoint:(BNRoutePlanNode *)endNode;

@end
