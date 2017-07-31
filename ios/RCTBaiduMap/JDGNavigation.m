//
//  JDGNavigation.m
//  RCTBaiduMap
//
//  Created by JDG on 2017/5/2.
//  Copyright © 2017年 lovebing.org. All rights reserved.
//

#import "React/Views/RCTViewManager.h"
#import "React/Views/RCTConvert+CoreLocation.h"
#import "JDGNavigation.h"
#import "BNCoreServices.h"

@interface JDGNavigation ()<BNNaviUIManagerDelegate,BNNaviRoutePlanDelegate>

@end

@implementation JDGNavigation
RCT_EXPORT_MODULE(BaiduNavigationModule)

+ (void)initSDK:(NSString*)key {
    [BNCoreServices_Instance initServices: key];
    [BNCoreServices_Instance startServicesAsyn:nil fail:nil];
}

RCT_EXPORT_METHOD(startNavigationFromMyLocationTo:(double)destPosLat long:(double)destPosLong)
{
    
    CLLocation *myLocation = [BNCoreServices_Location getLastLocation];
    [self startNavigationFrom:myLocation.coordinate.latitude lng:myLocation.coordinate.longitude to:destPosLat lng:destPosLong];
}

RCT_EXPORT_METHOD(startNavigationFrom:(double)startPosLat lng:(double)startPosLong  to:(double)destPosLat lng:(double)destPosLong)
{
    NSMutableArray *nodesArray = [[NSMutableArray alloc]initWithCapacity:2];
    //起点 传入的是原始的经纬度坐标，若使用的是百度地图坐标，可以使用BNTools类进行坐标转化
    BNRoutePlanNode *startNode = [[BNRoutePlanNode alloc] init];
    startNode.pos = [[BNPosition alloc] init];
    startNode.pos.x = startPosLong;
    startNode.pos.y = startPosLat;
    startNode.pos.eType = BNCoordinate_BaiduMapSDK;
    [nodesArray addObject:startNode];
    
    //终点
    BNRoutePlanNode *endNode = [[BNRoutePlanNode alloc] init];
    endNode.pos = [[BNPosition alloc] init];
    endNode.pos.x = destPosLong;
    endNode.pos.y = destPosLat;
    endNode.pos.eType = BNCoordinate_BaiduMapSDK;
    [nodesArray addObject:endNode];
    
    //关闭openURL,不想跳转百度地图可以设为YES
    dispatch_async(dispatch_get_main_queue(), ^{
        [BNCoreServices_RoutePlan setDisableOpenUrl:YES];
        [BNCoreServices_RoutePlan startNaviRoutePlan:BNRoutePlanMode_Recommend naviNodes:nodesArray time:nil delegete:self userInfo:nil];
    });
}

#pragma mark - BNNaviRoutePlanDelegate
//算路成功回调
-(void)routePlanDidFinished:(NSDictionary *)userInfo
{
    NSLog(@"算路成功");
    
    //路径规划成功，开始导航
    [BNCoreServices_UI showPage:BNaviUI_NormalNavi delegate:self extParams:nil];
    
    //导航中改变终点方法示例
    /*dispatch_after(dispatch_time(DISPATCH_TIME_NOW, (int64_t)(15 * NSEC_PER_SEC)), dispatch_get_main_queue(), ^{
     BNRoutePlanNode *endNode = [[BNRoutePlanNode alloc] init];
     endNode.pos = [[BNPosition alloc] init];
     endNode.pos.x = 114.189863;
     endNode.pos.y = 22.546236;
     endNode.pos.eType = BNCoordinate_BaiduMapSDK;
     [[BNaviModel getInstance] resetNaviEndPoint:endNode];
     });*/
    [self sendEvent:@"routePlanDidFinished" body:userInfo];
}

//算路失败回调
- (void)routePlanDidFailedWithError:(NSError *)error andUserInfo:(NSDictionary*)userInfo
{
    NSMutableDictionary *body = [self getEmptyBody];
    NSString *errormsg = @"未知";
    switch ([error code]%10000)
    {
        case BNAVI_ROUTEPLAN_ERROR_LOCATIONFAILED:
            errormsg = @"暂时无法获取您的位置,请稍后重试";
            break;
        case BNAVI_ROUTEPLAN_ERROR_ROUTEPLANFAILED:
            errormsg = (@"无法发起导航");
            break;
        case BNAVI_ROUTEPLAN_ERROR_LOCATIONSERVICECLOSED:
            errormsg = (@"定位服务未开启,请到系统设置中打开定位服务。");
            break;
        case BNAVI_ROUTEPLAN_ERROR_NODESTOONEAR:
            errormsg = (@"起终点距离起终点太近");
            break;
        default:
            errormsg = (@"算路失败");
            break;
    }
    body[@"errcode"] = [NSString stringWithFormat:@"%d", error];
    body[@"errmsg"] = errormsg;
    [self sendEvent:@"routePlanDidFailedWithError" body:body];
}

//算路取消回调
-(void)routePlanDidUserCanceled:(NSDictionary*)userInfo {
    [self sendEvent:@"routePlanDidUserCanceled" body:userInfo];
}


#pragma mark - 安静退出导航

- (void)exitNaviUI
{
    [BNCoreServices_UI exitPage:EN_BNavi_ExitTopVC animated:YES extraInfo:nil];
}

#pragma mark - BNNaviUIManagerDelegate

//退出导航页面回调
- (void)onExitPage:(BNaviUIType)pageType  extraInfo:(NSDictionary*)extraInfo
{
    if (pageType == BNaviUI_NormalNavi)
    {
        NSLog(@"退出导航");
    }
    else if (pageType == BNaviUI_Declaration)
    {
        NSLog(@"退出导航声明页面");
    }
}

@end
