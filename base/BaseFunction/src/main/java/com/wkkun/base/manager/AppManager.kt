package com.wkkun.base.manager

import android.app.Activity
import android.app.Application
import android.util.Log
import com.wkkun.base.util.getApplicationByReflect
import javax.inject.Inject
import kotlin.system.exitProcess


/**
 * App管理
 */
class AppManager private constructor() :IActivityManager{
    companion object {
        const val TAG = "AppManager:"
        val instance: AppManager by lazy {
            AppManager()
        }
    }


    @Inject
    lateinit var activityManager: ActivityManagerImpl

    private lateinit var application: Application

    fun attachApplication(application: Application) {
        Log.d(TAG, "attachApplication: ")
        this.application = application
        application.registerActivityLifecycleCallbacks(activityManager)
    }


    fun getApplication(): Application? {
        if (!::application.isInitialized){
            Log.e(TAG, "getApplication: application is not init !!!")
            return getApplicationByReflect()
        }
        return application
    }


    public fun exitApp(){
        getActivityList().forEach {
            it.finish()
        }
        exitProcess(0)
    }



    override fun unRegisterFrontBackChangeListener(listener: FrontBackChangeListener) {
        activityManager.unRegisterFrontBackChangeListener(listener)
    }
    override fun registerFrontBackChangeListener(listener: FrontBackChangeListener) {
        activityManager.registerFrontBackChangeListener(listener)
    }
    override fun getTopActivity(): Activity? =activityManager.getTopActivity()

    override fun getTopActiveActivity(): Activity? =activityManager.getTopActiveActivity()

    override fun getActivityList(): List<Activity> =activityManager.getActivityList()

    override fun getFrontActCount(): Int =activityManager.getFrontActCount()

}
