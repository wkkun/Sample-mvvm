package com.wkkun.base.manager

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Bundle
import com.wkkun.base.ktx.isActive
import java.util.LinkedList
import java.util.concurrent.CopyOnWriteArrayList
import javax.inject.Inject

/**
 * Activity管理
 * 1，前后台变化监听
 * 2，
 */
class ActivityManagerImpl @Inject constructor() : ActivityLifecycleCallbacks,IActivityManager {

    private val mAppStatusChangeListener = CopyOnWriteArrayList<FrontBackChangeListener>()
    private val mActivityList = LinkedList<Activity>()
    private var foregroundActCount = 0

    private var mConfigCount = 0
    private var mForegroundCount = 0
    private var mIsBackground = false


    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        setToTop(activity)
        if (mActivityList.size == 0) {
            postAppStatus(activity, true)
        }
    }

    override fun onActivityStarted(activity: Activity) {
        ++foregroundActCount
        if (!mIsBackground) {
            setToTop(activity)
        }
        if (mConfigCount < 0) {
            ++mConfigCount
        } else {
            ++mForegroundCount
        }
    }

    override fun onActivityResumed(activity: Activity) {
        setToTop(activity)
        if (mIsBackground) {
            postAppStatus(activity, true)
        }
    }

    override fun onActivityPaused(activity: Activity) {

    }

    override fun onActivityStopped(activity: Activity) {
        --foregroundActCount
        if (activity.isChangingConfigurations) {
            --mConfigCount
        } else {
            --mForegroundCount
            if (mForegroundCount <= 0) {
                mIsBackground = true
                postAppStatus(activity, false)
            }
        }
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        mActivityList.remove(activity)
    }


    private fun postAppStatus(activity: Activity, isFront: Boolean) {
        if (mAppStatusChangeListener.isEmpty()) return
        mAppStatusChangeListener.forEach {
            if (isFront) {
                it.onForeground(activity)
            } else {
                it.onBackground(activity)
            }
        }
    }

    private fun setToTop(activity: Activity) {
        if (mActivityList.contains(activity)) {
            if (mActivityList.first != activity) {
                mActivityList.remove(activity)
                mActivityList.addFirst(activity)
            }
        } else {
            mActivityList.addFirst(activity)
        }
    }


    /**
     * 获取顶层Activity
     */
    public override fun getTopActivity(): Activity? {
        return mActivityList.firstOrNull()
    }

    /**
     * 获取顶层活跃的Activity
     */
    public override fun getTopActiveActivity(): Activity? {
        return mActivityList.firstOrNull {
            return@firstOrNull it.isActive()
        }
    }

    override fun getActivityList(): List<Activity> {
        return LinkedList(mActivityList)
    }

    override fun getFrontActCount(): Int {
        return foregroundActCount
    }

    /**
     * 注册前后台变化监听
     */
    public override fun registerFrontBackChangeListener(listener: FrontBackChangeListener) {
        if (!mAppStatusChangeListener.contains(listener)) {
            mAppStatusChangeListener.add(listener)
        }
    }

    /**
     * 取消注册前后台变化监听
     */
    public override fun unRegisterFrontBackChangeListener(listener: FrontBackChangeListener) {
        if (mAppStatusChangeListener.contains(listener)) {
            mAppStatusChangeListener.remove(listener)
        }
    }


}

interface FrontBackChangeListener {
    /**
     * 切换到前台
     */
    fun onForeground(currentActivity: Activity)

    /**
     * 切换到后台
     */
    fun onBackground(currentActivity: Activity)
}