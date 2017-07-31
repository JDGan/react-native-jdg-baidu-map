//
//  BNCommonDef.h
//  baiduNaviSDK
//
//  Created by Baidu on 11/12/13.
//  Copyright (c) 2013 baidu. All rights reserved.
//

#ifndef baiduNaviSDK_BNCommonDef_h
#define baiduNaviSDK_BNCommonDef_h

typedef enum{
    //    算路相关参数错误（5000开始）
    BNAVI_ROUTEPLAN_ERROR_INVALIDSTARTENDNODE = 5000,   //请设置完整的起终点
    BNAVI_ROUTEPLAN_ERROR_INPUTERROR = 5001,    //节点输入有误
    BNAVI_ROUTEPLAN_ERROR_NODESTOONEAR = 5002,//节点之间距离太近
    
    //    检索错误（5100开始）
    BNAVI_ROUTEPLAN_ERROR_SEARCHFAILED =5100,   //检索失败
    
    //    定位错误（5200开始）
    BNAVI_ROUTEPLAN_ERROR_LOCATIONFAILED = 5200,    //获取地理位置失败
    BNAVI_ROUTEPLAN_ERROR_LOCATIONSERVICECLOSED = 5201, //定位服务未开启
    
    //    算路相关网络错误（5030开始）
    BNAVI_ROUTEPLAN_ERROR_NONETWORK = 5030, //网络不可用
    BNAVI_ROUTEPLAN_ERROR_NETWORKABNORMAL = 5031,//网络异常，尝试联网线路规划失败。自动切换到本地线路规划（客户端预留定义）
    //    算路过程错误（5050开始）
    BNAVI_ROUTEPLAN_ERROR_ROUTEPLANFAILED = 5050, //无法发起算路（客户端请求算路返回id<0）
    BNAVI_ROUTEPLAN_ERROR_SETSTARTPOSFAILED = 5051,//起点失败
    BNAVI_ROUTEPLAN_ERROR_SETENDPOSFAILED = 5052,   //设置终点失败
    BNAVI_ROUTEPLAN_ERROR_WAITAMOMENT = 5054,   //上次算路取消了，需要等一会
    BNAVI_ROUTEPLAN_ERROR_DATANOTREADY = 5055,  //行政区域数据没有
    BNAVI_ROUTEPLAN_ERROR_ENGINENOTINIT = 5056, //引擎未初始化
    BNAVI_ROUTEPLAN_ERROR_LIGHTSEARCHERROR = 5057,//light检索未成功发送
}BNAVI_ROUTEPLAN_ERROR;


/**
 *  路线计算类型
 */
typedef enum
{
    BNRoutePlanMode_Invalid 			= 0X00000000 ,  /**<  无效值 */
    BNRoutePlanMode_Recommend			= 0X00000001 ,	/**<  推荐 */
    BNRoutePlanMode_Highway             = 0X00000002 ,	/**<  高速优先 */
    BNRoutePlanMode_NoHighway           = 0X00000004 ,	/**<  少走高速 */
    BNRoutePlanMode_NoToll              = 0X00000008 ,	/**<  少收费 */
    BNRoutePlanMode_AvoidTrafficJam     = 0X00000010 ,	/**<  躲避拥堵（在线规划独有） */
}BNRoutePlanMode;

/**
 *  播报模式
 */
typedef enum {
    BN_Speak_Mode_High,                /**< 新手模式 */
    BN_Speak_Mode_Mid,                 /**< 专家模式 */
    BN_Speak_Mode_Low,                 /**< 静音模式 */
} BN_Speak_Mode_Enum;


/**
 *  白天，黑夜模式类型
 */
typedef enum
{
    BNDayNight_CFG_Type_Auto,   //自动
    BNDayNight_CFG_Type_Day,    //白天模式
    BNDayNight_CFG_Type_Night,  //黑夜模式
}BNDayNight_CFG_Type;

#endif
