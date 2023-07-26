package com.example.quickdev.test

import com.example.quickdev.test.dl.MakeCarModule
import dagger.Component

@Component(modules = [MakeCarModule::class])
interface CarComponent {
    fun inject(car: Car)
}