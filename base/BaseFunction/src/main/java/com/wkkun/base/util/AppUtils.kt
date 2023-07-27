package com.wkkun.base.util

import android.app.Application
import android.util.Log


fun getApplicationByReflect(): Application? {
    try {
        val activityThreadClass = Class.forName("android.app.ActivityThread")
        val thread: Any = getActivityThread() ?: return null
        val app = activityThreadClass.getMethod("getApplication").invoke(thread) ?: return null
        return app as Application
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}


private fun getActivityThread(): Any? {
    val activityThread = getActivityThreadInActivityThreadStaticField()
    return activityThread ?: getActivityThreadInActivityThreadStaticMethod()
}

private fun getActivityThreadInActivityThreadStaticField(): Any? {
    return try {
        val activityThreadClass = Class.forName("android.app.ActivityThread")
        val sCurrentActivityThreadField =
            activityThreadClass.getDeclaredField("sCurrentActivityThread")
        sCurrentActivityThreadField.isAccessible = true
        sCurrentActivityThreadField[null]
    } catch (e: java.lang.Exception) {
        Log.e(
            "UtilsActivityLifecycle",
            "getActivityThreadInActivityThreadStaticField: " + e.message
        )
        null
    }
}

private fun getActivityThreadInActivityThreadStaticMethod(): Any? {
    return try {
        val activityThreadClass = Class.forName("android.app.ActivityThread")
        activityThreadClass.getMethod("currentActivityThread").invoke(null)
    } catch (e: java.lang.Exception) {
        Log.e(
            "UtilsActivityLifecycle",
            "getActivityThreadInActivityThreadStaticMethod: " + e.message
        )
        null
    }
}