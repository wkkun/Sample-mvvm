package com.wkkun.base.manager

import android.app.Activity

interface IActivityManager {
    fun unRegisterFrontBackChangeListener(listener: FrontBackChangeListener)
    fun registerFrontBackChangeListener(listener: FrontBackChangeListener)

    fun getTopActivity(): Activity?
    fun getTopActiveActivity(): Activity?
    fun getActivityList():List<Activity>

    fun getFrontActCount():Int

}