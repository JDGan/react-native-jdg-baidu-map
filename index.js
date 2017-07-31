import {
  requireNativeComponent,
  View,
  NativeModules,
  Platform,
  DeviceEventEmitter
} from 'react-native';

import React, {
  Component,
  PropTypes
} from 'react';

import _MapTypes from './js/MapTypes';
import _MapView from './js/MapView';
import _MapModule from './js/MapModule';
import _Geolocation from './js/Geolocation';
import _Navigation from './js/Navigation';

export const JDGMapTypes = _MapTypes;
export const JDGMapView = _MapView;
export const JDGMapModule = _MapModule;
export const JDGGeolocation = _Geolocation;
export const JDGNavigation = _Navigation;
