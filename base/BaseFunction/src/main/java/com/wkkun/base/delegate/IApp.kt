package com.wkkun.base.delegate

import android.app.Application
import android.content.Context


interface IApp {

    fun attachBaseContext(context: Context)

    fun onCreate(app: Application)
}