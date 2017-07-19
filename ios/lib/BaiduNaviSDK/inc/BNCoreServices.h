
/**
 * @header BNCoreServices.h
 * @abstract baiduNaviSDK
 * @author - Copyright (c) 2013 baidu. All rights reserved.
 * @version
 */

#ifndef baiduNaviSDK_BNCoreServices_h
#define baiduNaviSDK_BNCoreServices_h

#import <Foundation/Foundation.h>

#import "BNUIManagerProtocol.h"
#import "BNRoutePlanManagerProtocol.h"
#import "BNStrategyManagerProtocol.h"
#import "BNLocationManagerProtocol.h"


#define BNCoreServices_Instance ([BNCoreServices GetInstance])

#define BNCoreServices_UI ([BNCoreServices UIService])
#define BNCoreServices_RoutePlan ([BNCoreServices RoutePlanService])
#define BNCoreServices_Strategy ([BNCoreServices StrategyService])
#define BNCoreServices_Location ([BNCoreServices LocationService])

#define BNGetNaviVC ((UINavigationController*)[BNCoreServices_UI navigationController])
#define BNGetTopVC [BNGetNaviVC topViewController]


/**
 *  @class
 *  @abstract 核心服务
 */
@interface BNCoreServices : NSObject

/**
 *  获取单体
 *
 *  @return BNCoreService单体
 */
+ (BNCoreServices*)GetInstance;


/**
 *  释放单体
 */
+ (void)ReleaseInstance;

/**
 *  获取当前版本号
 */
+ (NSString *)libVersion;

/**
 *  初始化服务，需要在AppDelegate的 application:didFinishLaunchingWithOptions:
 *  中调用
 *
 *  @param ak AppKey
 */
- (void)initServices:(NSString *)ak;


/**
 *  启动服务,同步方法,会导致阻塞
 *
 *  @return  启动结果
 */
- (BOOL)startServices;


/**
 *  启动服务,异步方法
 *
 *  @param success 启动成功后回调 success block
 *  @param fail    启动失败后回调 fail block
 */
-(void)startServicesAsyn:(void (^)(void))success  fail:(void (^)(void))fail;

/**
 *  查询引擎是否初始化完成
 *
 *  @return 是否初始化完成
 */
-(BOOL)isServicesInited;

/**
 *  停止所有服务
 */
- (void)stopServices;

#pragma mark - data type transfer

/**
 *  coordinate conversion
 *
 *  @param coordinate in wgs84ll standard
 *
 *  @return coordinate in BD09ll standard
 */
- (CLLocationCoordinate2D)convertToBD09MCWithWGS84ll:(CLLocationCoordinate2D)coordinate;

#pragma mark - 获取提供各种服务的实体对象

/**
 *  获取到导航过程页管理器，用于进入退出导航过程页
 *
 *  @return 导航过程页管理器
 */
+ (id<BNUIManagerProtocol>)UIService;

/**
 *  获取路径规划管理器，用于路径规划
 *
 *  @return 路径规划管理器
 */
+ (id<BNRoutePlanManagerProtocol>)RoutePlanService;

/**
 *  获取策略管理器，用于调整在离线策略、白天黑夜策略、横竖向切换策略等等
 *
 *  @return 策略管理器
 */
+ (id<BNStrategyManagerProtocol>)StrategyService;

/**
 *  获取定位服务器，用于获取当前定位
 *
 *  @return 定位服务器
 */
+ (id<BNLocationManagerProtocol>)LocationService;

@end

#endif
