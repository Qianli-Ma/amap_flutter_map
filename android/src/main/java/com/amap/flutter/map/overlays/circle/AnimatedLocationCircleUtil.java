package com.amap.flutter.map.overlays.circle;

import android.graphics.Color;

import com.amap.api.maps.AMap;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.flutter.map.utils.LogUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class AnimatedLocationCircleUtil {
    private static final String CLASS_NAME = "AnimatedLocationCircleController";

    private static AnimatedLocationCircleUtil instance;

    private AMap amap;
    private Circle myLocationCircle;
    private Circle myLocationCircleOuter;
    private int circleFillColor;
    private int circleStrokeColor;
    private int circleStrokeWidth;
    private boolean isCircleVisible = false;
    private final Timer myLocationCircleAnimationTimer = new Timer();

    public AnimatedLocationCircleUtil(AMap amap) {
        this.amap = amap;
    }

    public static AnimatedLocationCircleUtil getInstance(AMap amap) {
        if (instance == null) {
            instance = new AnimatedLocationCircleUtil(amap);
        }
        if (instance.amap != amap) {
            instance.amap = amap;
        }
        return instance;
    }

    public void animatedLocationCircle(boolean animateLocationCircle, int circleFillColor, int circleStrokeColor, int circleStrokeWidth) {
        this.isCircleVisible = animateLocationCircle;
        if (animateLocationCircle) {
            addAnimatedLocationCircle(circleFillColor, circleStrokeColor, circleStrokeWidth);
        }
    }

    public void addAnimatedLocationCircle(int circleFillColor, int circleStrokeColor, int circleStrokeWidth) {
        // Add animated location circle
        LogUtil.i(CLASS_NAME, "addAnimatedLocationCircle");
        this.circleFillColor = circleFillColor;
        this.circleStrokeColor = circleStrokeColor;
        this.circleStrokeWidth = circleStrokeWidth;
    }

    public void updateAnimatedLocationCircle(Object o) {
        // Update animated location circle
        LogUtil.i(CLASS_NAME, "updateAnimatedLocationCircle isCircleVisible = " + isCircleVisible + " o = " + o);
        if (isCircleVisible && o != null) {
            Map<String, Object> location = (HashMap) o;
            if (location.get("latLng") instanceof List) {
                List<?> latLngList = (List<?>) location.get("latLng");
                float accuracy = (float) location.get("accuracy");
                if (!latLngList.isEmpty() && latLngList.get(0) instanceof Double && latLngList.get(1) instanceof Double) {
                    double latitude = (Double) latLngList.get(0);
                    double longitude = (Double) latLngList.get(1);
                    LatLng latLng = new LatLng(latitude, longitude);
                    if (myLocationCircle == null) {
                        myLocationCircle = amap.addCircle(new CircleOptions()
                                .center(latLng)
                                .radius(0)
                                .fillColor(circleFillColor)
                                .strokeColor(circleStrokeColor)
                                .strokeWidth(circleStrokeWidth));
                        int reducedAlpha = Color.alpha(circleFillColor) - 50;
                        if (reducedAlpha < 0) {
                            reducedAlpha = 0;
                        }
                        int reducedFillColor = Color.argb(reducedAlpha,
                                Color.red(circleFillColor),
                                Color.green(circleFillColor),
                                Color.blue(circleFillColor));
                        myLocationCircleOuter = amap.addCircle(new CircleOptions()
                                .center(latLng)
                                .radius(0)
                                .fillColor(Color.TRANSPARENT)
                                .strokeColor(reducedFillColor)
                                .strokeWidth(circleStrokeWidth));
                    } else {
                        myLocationCircle.setCenter(latLng);
                        myLocationCircle.setRadius(1);
                        myLocationCircleOuter.setCenter(latLng);
                        myLocationCircleOuter.setRadius(1);
                    }
                    animateCircle(myLocationCircleOuter, accuracy);
                }
            }
        }
    }

    private void animateCircle(final Circle circle, final float accuracy) {
        LogUtil.i(CLASS_NAME, "animateCircle: accuracy = " + accuracy);
        if (circle != null) {
            TimerTask myLocationCircleAnimationTask = new CircleAnimationTimerTask(circle, accuracy, 1000);
            myLocationCircleAnimationTimer.schedule(myLocationCircleAnimationTask, 0, 30);
        }
    }
}
