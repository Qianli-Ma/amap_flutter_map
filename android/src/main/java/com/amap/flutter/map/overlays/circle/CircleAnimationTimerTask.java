package com.amap.flutter.map.overlays.circle;
/*
 * This code uses and modifies code from the following GitHub repository:
 *
 * Repository: android-location-circle
 * Author: amap-demo
 * URL: https://github.com/amap-demo/android-location-circle
 *
 */
import android.os.SystemClock;
import android.util.Log;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.amap.api.maps.model.Circle;
import com.amap.flutter.map.utils.LogUtil;

import java.util.TimerTask;

public class CircleAnimationTimerTask extends TimerTask {

    private static final String TAG = "CircleAnimation";
    private final Interpolator linearInterpolator = new LinearInterpolator();
    private final Circle circle;
    private final float targetRadius;
    private long startTime;
    private long duration = 2000;

    public CircleAnimationTimerTask(Circle circle, float targetRadius, long rate) {
        LogUtil.i(TAG, "CircleAnimationTimerTask: ");
        this.circle = circle;
        this.targetRadius = targetRadius;
        if (rate > 0) {
            this.duration = rate;
        }
        this.startTime = SystemClock.uptimeMillis();
    }

    @Override
    public void run() {
        try {
            LogUtil.i(TAG, "run: ");
            long elapsed = SystemClock.uptimeMillis() - this.startTime;
            float input = (float) elapsed / this.duration;
            float t = linearInterpolator.getInterpolation(input);
            double newRadius = 1 + t * (targetRadius - 1);
            // Log.d(TAG, "run: newRadius = " + newRadius);
            circle.setRadius(newRadius);
            if (newRadius >= this.targetRadius) {
                this.startTime = SystemClock.uptimeMillis(); // Reset the start time for a new animation cycle
            }
        } catch (Exception e) {
            LogUtil.e(TAG, "run: ", e);
        }
    }
}

