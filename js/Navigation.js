/**
 * Created by jdg on 2017/5/2.
 */

import {
  requireNativeComponent,
  NativeModules,
  Platform,
  DeviceEventEmitter
} from 'react-native';

import React, {
  Component,
  PropTypes
} from 'react';


const _module = NativeModules.BaiduNavigationModule;

export default {
  startNavigationFrom(fromLat, fromLong, toLat, toLong) {
    return new Promise((resolve, reject) => {
      try {
        _module.startNavigationFrom(fromLat, fromLong, toLat, toLong);
      }
      catch (e) {
        reject(e);
        return;
      }
      DeviceEventEmitter.once('routePlanDidFinished', resp => {
        resolve(resp);
        DeviceEventEmitter.removeAllListeners();
      });
      DeviceEventEmitter.once('routePlanDidFailedWithError', err => {
        reject(err);
        DeviceEventEmitter.removeAllListeners();
      });
    });
  },

  startNavigationFromMyLocationTo(toLat, toLong) {
    return new Promise((resolve, reject) => {
      try {
        _module.startNavigationFromMyLocationTo(toLat, toLong);
      }
      catch (e) {
        reject(e);
        return;
      }
      DeviceEventEmitter.once('routePlanDidFinished', resp => {
        resolve(resp);
        DeviceEventEmitter.removeAllListeners();
      });
      DeviceEventEmitter.once('routePlanDidFailedWithError', err => {
        reject(err);
        DeviceEventEmitter.removeAllListeners();
      });
    });
  },
};
