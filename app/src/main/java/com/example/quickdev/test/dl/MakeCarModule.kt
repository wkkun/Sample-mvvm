package com.example.quickdev.test.dl

import com.example.quickdev.test.Door
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MakeCarModule {

    @Provides
    fun createDoor():Door{
        return Door()
    }
}