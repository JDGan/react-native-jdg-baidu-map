//
//  BNaviRoutePlanModel.h
//  OfflineNavi
//
//  Created by Baidu on 4/11/13.
//  Copyright (c) 2013 baidu. All rights reserved.
//
//  路线规划节点数据结构类


#import <Foundation/Foundation.h>

#import "BNCommonDef.h"


/**
 * 坐标系类型
 */
typedef enum
{
    BNCoordinate_OriginalGPS = 0,/**< 原始的经纬度坐标 */
    BNCoordinate_BaiduMapSDK = 1,/**< 从百度地图中获取的sdk */
}BNCoordinate_Type;

/**
 *  位置原始坐标
 */
@interface BNPosition : NSObject

/**
 *  经度
 */
@property(nonatomic,assign)double x;

/**
 *  纬度
 */
@property(nonatomic,assign)double y;

/**
 *  坐标系类型，默认是BNCoordinate_OriginalGPS
 */
@property(nonatomic,assign)BNCoordinate_Type eType;

@end


/**
 *  路径规划节点
 */
@interface BNRoutePlanNode : NSObject

/**
 *  位置，原始经纬度信息
 */
@property(nonatomic,strong)BNPosition* pos;

/**
 *  城市ID
 */
@property(nonatomic,strong)NSString *cityID;

/**
 *  描述信息（如家，公司，地图上选的点，我的位置等）
 */
@property(nonatomic,copy)NSString* title;

/**
 *  地址信息（如上地十街十号百度大厦）
 */
@property(nonatomic,copy)NSString* address;

@end

/**
 *  每个路段信息
 */
@interface BNRouteItem : NSObject

/**
 *  路名
 */
@property(nonatomic,copy)NSString* nextRoadName;

/**
 *  长度，单位米
 */
@property(nonatomic,assign)int nLength;

/**
 *  时间，单位秒
 */
@property(nonatomic,assign)int nTime;

/**
 *  交叉路口坐标
 */
@property(nonatomic,strong)BNPosition* crossPos;

/**
 *  在路线中的显示点索引
 */
@property(nonatomic,assign)int nShapePointIdx;

/**
 *  脱产Link和正北方向的夹角，单位度
 */
@property(nonatomic,assign)int unOutLinkAngle;

@end

/**
 *  路线详情信息
 */
@interface BNRouteDetailInfo : NSObject

/**
 *  路线标签
 */
@property(nonatomic, assign) int unLabel;

/**
 *  总长度，单位米
 */
@property(nonatomic,assign)int unLength;

/**
 *  总时间，单位秒
 */
@property(nonatomic,assign)int unPasstime;

/**
 *  路段详情列表,每一个元素是BNaviRouteItem类型
 */
@property(nonatomic,strong)NSArray*  routeItemList;

/**
 *  是否包含收费路段
 */
@property(nonatomic,assign)BOOL bTolled;

@end

/**
 *  分时段规划,路线的时间
 */
@interface BNaviCalcRouteTime : NSObject

/**
 *  24小时,0-23
 */
@property(nonatomic,assign)int unHour;

/**
 *  分钟,0-59
 */
@property(nonatomic,assign)int unMin;

/**
 *  是否有效
 */
@property(nonatomic,assign)bool bValid;

@end