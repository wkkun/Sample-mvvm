package com.example.quickdev.test

import javax.inject.Inject

class Car {
    @Inject
    lateinit var engine: Engine

    @Inject
    lateinit var door:Door

    init {
        DaggerCarComponent.create().inject(this)
    }

    fun test(){
        engine.start()
        door.text()
    }
}