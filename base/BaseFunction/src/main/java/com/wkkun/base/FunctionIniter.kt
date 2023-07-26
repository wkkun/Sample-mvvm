package com.wkkun.base

import android.content.Context
import android.util.Log
import androidx.startup.Initializer

class FunctionInitializer :Initializer<Unit>{
    override fun create(context: Context) {

    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }
}