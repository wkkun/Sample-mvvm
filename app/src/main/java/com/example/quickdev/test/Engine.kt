package com.example.quickdev.test

import android.util.Log
import javax.inject.Inject

class Engine @Inject constructor() {
    fun start(){
        Log.d("===", "Engine---start: ")
    }
}