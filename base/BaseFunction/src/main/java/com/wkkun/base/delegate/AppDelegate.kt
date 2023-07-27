package com.wkkun.base.delegate

import android.app.Application
import android.content.Context
import android.util.Log

/**
 * 将Application中的框架功能分离到代理类中
 */
class AppDelegate(val context: Context)  : IApp {
    companion object{
        const val TAG = "AppDelegate:"
    }

    override fun attachBaseContext(context: Context) {
        Log.d(TAG, "attachBaseContext: ")
    }

    override fun onCreate(app: Application) {
        Log.d(TAG, "onCreate: ")
    }
}