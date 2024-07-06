package com.amap.flutter.map.overlays.circle;

import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;

/**
 * @Author: Vincent
 * @CreateAt: 2021/12/30 17:53
 * @Desc:
 */
class CircleOptionsBuilder implements CircleOptionsSink {
    final CircleOptions circleOptions;
    CircleOptionsBuilder() {
        circleOptions = new CircleOptions();
        //必须设置为true，否则会出现线条转折处出现断裂的现象
        circleOptions.usePolylineStroke(true);
    }

    public CircleOptions build(){
        return circleOptions;
    }

    @Override
    public void setCenter(LatLng center) {
        circleOptions.center(center);
    }

    @Override
    public void setRadius(double radius) {
        circleOptions.radius(radius);
    }

    @Override
    public void setStrokeDottedLineType(int type) {
        circleOptions.setStrokeDottedLineType(type);
    }

    @Override
    public void setStrokeWidth(float strokeWidth) {
        circleOptions.strokeWidth(strokeWidth);
    }

    @Override
    public void setStrokeColor(int color) {
        circleOptions.strokeColor(color);
    }

    @Override
    public void setFillColor(int color) {
        circleOptions.fillColor(color);
    }

    @Override
    public void setVisible(boolean visible) {
        circleOptions.visible(visible);
    }

    @Override
    public void setZIndex(float zIndex) {
        circleOptions.zIndex(zIndex);
    }
}
