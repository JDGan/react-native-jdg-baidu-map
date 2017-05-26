//
//  BNRoutePlanManagerProtocol.h
//  baiduNaviSDK
//
//  Created by Baidu on 11/10/13.
//  Copyright (c) 2013 baidu. All rights reserved.
//

#ifndef baiduNaviSDK_BNRoutePlanManagerProtocol_h
#define baiduNaviSDK_BNRoutePlanManagerProtocol_h

#import "BNRoutePlanModel.h"

/**
 *  导航算路入口
 */

@class BNaviRoutePlanNode;
@protocol BNNaviRoutePlanDelegate;

@protocol BNRoutePlanManagerProtocol

@required

/**
 *  发起算路
 *
 *  @param eMode     算路方式，定义见BNRoutePlanMode
 *  @param naviNodes 算路节点数组，起点、途经点、终点按顺序排列，节点信息为BNRoutePlanNode结构
 *  @param naviTime  发起算路时间，用于优化算路结果,可以为nil
 *  @param delegate  算路委托，用于回调
 *  @param userInfo  用户需要传入的参数
 */
-(void)startNaviRoutePlan:(BNRoutePlanMode)eMode
                naviNodes:(NSArray*)naviNodes
                     time:(BNaviCalcRouteTime*)naviTime
                 delegete:(id<BNNaviRoutePlanDelegate>)delegate
                 userInfo:(NSDictionary*)userInfo;


/**
 *  获得当前节点总数
 *
 *  @return 当前节点总数
 */
-(NSInteger)getCurNodeCount;


/**
 *  获得第index个节点
 *
 *  @param index 节点序号
 *
 *  @return 第index个节点
 */
-(BNRoutePlanNode*)getNaviNodeAtIndex:(NSInteger)index;


/**
 *  设置算路节点
 *
 *  @param naviNodes 算路节点
 */
-(void)setNaviNodes:(NSArray*)naviNodes;


/**
 *  获取当前的路线规划方式
 *
 *  @return 当前的路线规划方式
 */
-(int)getCurRoutePlanMode;

/*
 * 获取选择的路线索引
 * return 路线索引
 */
- (NSInteger)GetCurrentSelectRouteIdx;

/**
 *	获取当前规划方式的路线详情信息
 *  param [in] stRouteIdx   路线下标    多路线为选择的路线下标，单路线就传0
 *	@return	返回路线详情信息
 */
-(BNRouteDetailInfo*)getCurrentRouteDetailInfo:(int)stRouteIdx;

/**
 *	获取当前用户保存的算路偏好
 */
- (int)getCurrentPreference;

@optional

@property (nonatomic, assign) BOOL disableOpenUrl;

@end

@protocol BNNaviRoutePlanDelegate <NSObject>

@optional

/**
 *  算路成功回调
 *
 *  @param userInfo 用户信息
 */
- (void)routePlanDidFinished:(NSDictionary*)userInfo;

/**
 *  检索成功回调
 *
 *  @param userInfo 用户信息
 */
- (void)searchDidFinished:(NSDictionary*)userInfo;

/**
 *  算路失败回调
 *
 *  @param error    失败信息
 *  @param userInfo 用户信息
 */
- (void)routePlanDidFailedWithError:(NSError *)error andUserInfo:(NSDictionary*)userInfo;

/**
 *  算路取消
 *
 *  @param userInfo 用户信息
 */
-(void)routePlanDidUserCanceled:(NSDictionary*)userInfo;

/**
 *  更新路况成功回调
 *
 *  @param pbData pb数据
 */
- (void)updateRoadConditionDidFinished:(NSData *)pbData;

/**
 *  更新路况成功失败
 *
 *  @param pbData pb数据
 */
- (void)updateRoadConditionFailed:(NSData *)pbData;

@end

#endif
