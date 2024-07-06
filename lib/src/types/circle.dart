// Copyright 2019 The Chromium Authors. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.
// From https://github.com/lytian/amap_flutter_map

import 'package:flutter/material.dart' show Color;
import 'package:amap_flutter_base/amap_flutter_base.dart';
import 'base_overlay.dart';
import 'polyline.dart';

/// 圆形相关的覆盖物类，内部的属性，描述了覆盖物的半径、颜色、线宽等特征
class Circle extends BaseOverlay {
  /// 默认构造函数
  Circle(
      {required this.center,
      double radius = 1000,
      double strokeWidth = 2,
      this.strokeDottedLineType = DashLineType.none,
      this.strokeColor = const Color(0xCC00BFFF),
      this.fillColor = const Color(0xC487CEFA),
      this.visible = true,
      this.zIndex = 0.0,})
      : this.radius = (radius < 0 ? 0 : radius),
        this.strokeWidth = (strokeWidth <= 0 ? 10 : strokeWidth),
        super();

  /// 覆盖物的坐标点数组,不能为空
  final LatLng center;

  /// 圆的半径，单位：米
  final double radius;

  /// 虚线类型
  final DashLineType strokeDottedLineType;

  /// 边框宽度,单位为逻辑像素，同Android中的dp，iOS中的point
  final double strokeWidth;

  /// 边框颜色,默认值为(0xCCC4E0F0)
  final Color strokeColor;

  /// 填充颜色,默认值为(0xC4E0F0CC)
  final Color fillColor;

  /// 是否可见
  final bool visible;

  /// 圆的Z轴数值
  final double zIndex;

  /// 实际copy函数
  Circle copyWith({
    LatLng? centerParam,
    double? radiusParam,
    DashLineType? strokeDottedLineTypeParam,
    double? strokeWidthParam,
    Color? strokeColorParam,
    Color? fillColorParam,
    bool? visibleParam,
    double? zIndexParam,
  }) {
    Circle copyCircle = Circle(
      center: centerParam ?? center,
      radius: radiusParam ?? radius,
      strokeDottedLineType: strokeDottedLineTypeParam ?? strokeDottedLineType,
      strokeWidth: strokeWidthParam ?? strokeWidth,
      strokeColor: strokeColorParam ?? strokeColor,
      fillColor: fillColorParam ?? fillColor,
      visible: visibleParam ?? visible,
      zIndex: zIndexParam ?? zIndex,
    );
    copyCircle.setIdForCopy(id);
    return copyCircle;
  }

  Circle clone() => copyWith();

  /// 转换成可以序列化的map
  @override
  Map<String, dynamic> toMap() {
    final Map<String, dynamic> json = <String, dynamic>{};

    void addIfPresent(String fieldName, dynamic value) {
      if (value != null) {
        json[fieldName] = value;
      }
    }

    addIfPresent('id', id);
    json['center'] = center.toJson();
    addIfPresent('radius', radius);
    addIfPresent('strokeDottedLineType', strokeDottedLineType.index);
    addIfPresent('strokeWidth', strokeWidth);
    addIfPresent('strokeColor', strokeColor.value);
    addIfPresent('fillColor', fillColor.value);
    addIfPresent('visible', visible);
    addIfPresent('zIndex', zIndex);
    return json;
  }

  @override
  bool operator ==(Object other) {
    if (identical(this, other)) return true;
    if (other.runtimeType != runtimeType) return false;
    if (other is !Circle) return false;
    final Circle typedOther = other;
    return id == typedOther.id &&
        center == typedOther.center &&
        radius == typedOther.radius &&
        strokeDottedLineType == typedOther.strokeDottedLineType &&
        strokeWidth == typedOther.strokeWidth &&
        strokeColor == typedOther.strokeColor &&
        fillColor == typedOther.fillColor &&
        visible == typedOther.visible &&
        zIndex == typedOther.zIndex;
  }

  @override
  int get hashCode => super.hashCode;
}

Map<String, Circle> keyByCircleId(Iterable<Circle> circles) {
  // ignore: unnecessary_null_comparison
  if (circles == null) {
    return <String, Circle>{};
  }
  return Map<String, Circle>.fromEntries(circles.map((Circle circle) =>
      MapEntry<String, Circle>(circle.id, circle.clone())));
}
