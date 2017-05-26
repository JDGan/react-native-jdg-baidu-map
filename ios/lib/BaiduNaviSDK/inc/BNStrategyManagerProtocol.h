//
//  BNStrategyManagerProtocol.h
//  baiduNaviSDK
//
//  Created by Baidu on 11/10/13.
//  Copyright (c) 2013 baidu. All rights reserved.
//

/**
 *  导航策略管理器协议,包含导航过程中的设置项、电子狗的设置策略以及导航入口记录
 *
 */

#ifndef baiduNaviSDK_BNStrategyManagerProtocol_h
#define baiduNaviSDK_BNStrategyManagerProtocol_h

#import "BNCommonDef.h"

@protocol BNStrategyManagerProtocol

@required

//恢复到默认策略
- (void)reset;

// 停车场推送
@property (nonatomic, assign) BOOL parkInfo;

// 日夜模式
@property (nonatomic, assign) BNDayNight_CFG_Type dayNightType;

// 播报模式
@property (nonatomic, assign) BN_Speak_Mode_Enum speakMode;

/**
 *  设置路况是否开启，路况开启需要联网，没有网络，开启路况会失败
 *
 *  @param showTraffic 是否显示路况，默认显示
 *  @param success     成功的回调
 *  @param fail        失败的回调
 */
- (void)trySetShowTrafficInNavi:(BOOL)showTraffic success:(void (^)(void))success  fail:(void (^)(void))fail;

@end

#endif
