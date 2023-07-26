package com.example.quickdev

import android.os.Bundle
import com.wkkun.base.ui.base.BaseActivity
import com.example.quickdev.test.Car

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val car = Car()
        car.test()
    }

}