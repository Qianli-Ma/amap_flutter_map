package com.amap.flutter.map.overlays.circle;

import com.amap.api.maps.model.LatLng;

/**
 * @Author: Vincent
 * @CreateAt: 2021/12/30 17:53
 * @Desc:
 */
interface CircleOptionsSink {
    //圆心经纬度坐标
    void setCenter(LatLng center);

    //设置半径。单位：米
    void setRadius(double radius);

    //边框虚线形状
    void setStrokeDottedLineType(int type);

    //边框宽度
    void setStrokeWidth(float strokeWidth);

    //边框颜色
    void setStrokeColor(int color);

    //填充颜色
    void setFillColor(int color);

    //是否显示
    void setVisible(boolean visible);

    //Z轴数值
    void setZIndex(float zIndex);
}
