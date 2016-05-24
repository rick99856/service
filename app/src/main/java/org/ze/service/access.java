package org.ze.service;

import android.accessibilityservice.AccessibilityService;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

/**
 * Created by Ze on 2016/5/25.
 */
public class access extends AccessibilityService {
    final String TAG = "access";
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        String packageName = event.getPackageName().toString();
        Log.e(TAG,packageName);
    }

    @Override
    public void onInterrupt() {

    }
}
