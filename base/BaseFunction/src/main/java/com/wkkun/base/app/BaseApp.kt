package com.wkkun.base.app

import android.app.Application
import android.content.Context
import com.wkkun.base.manager.AppManager


open class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppManager.instance.attachApplication(this)
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
    }

}