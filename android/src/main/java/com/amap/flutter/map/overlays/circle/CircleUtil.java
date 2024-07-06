package com.amap.flutter.map.overlays.circle;

import com.amap.flutter.map.utils.ConvertUtil;

import java.util.Map;

/**
 * @Author: Vincent
 * @CreateAt: 2021/12/30 17:53
 * @Desc:
 */
class CircleUtil {//虚线类型
    private static final int[] DASH_LINE_TYPE = {-1,0,1};

    static String interpretOptions(Object o, CircleOptionsSink sink) {
        final Map<?, ?> data = ConvertUtil.toMap(o);
        final Object center = data.get("center");
        if (center != null) {
            sink.setCenter(ConvertUtil.toLatLng(center));
        }

        final Object radius = data.get("radius");
        if (radius != null) {
            sink.setRadius(ConvertUtil.toDouble(radius));
        }

        final Object strokeDottedLineType = data.get("strokeDottedLineType");
        if (strokeDottedLineType != null) {
            int rawType = ConvertUtil.toInt(strokeDottedLineType);
            if (rawType > DASH_LINE_TYPE.length) {
                rawType = -1;
            }
            sink.setStrokeDottedLineType(DASH_LINE_TYPE[rawType]);
        }

        final Object width = data.get("strokeWidth");
        if (width != null) {
            sink.setStrokeWidth(ConvertUtil.toFloatPixels(width));
        }

        final Object strokeColor = data.get("strokeColor");
        if (strokeColor != null) {
            sink.setStrokeColor(ConvertUtil.toInt(strokeColor));
        }

        final Object fillColor = data.get("fillColor");
        if (fillColor != null) {
            sink.setFillColor(ConvertUtil.toInt(fillColor));
        }

        final Object visible = data.get("visible");
        if (visible != null) {
            sink.setVisible(ConvertUtil.toBoolean(visible));
        }

        final Object zIndex = data.get("zIndex");
        if (zIndex != null) {
            sink.setZIndex(ConvertUtil.toFloat(zIndex));
        }

        final String circleId = (String) data.get("id");
        if (circleId == null) {
            throw new IllegalArgumentException("circleId was null");
        } else {
            return circleId;
        }
    }


}
