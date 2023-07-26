plugins {
    id("convention.android.library")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.wkkun.base"
}

kapt {
    correctErrorTypes = true
}

dependencies {
    api(libs.appcompat)
    api(libs.androidxCore)
    api(libs.material)
    //初始化
    api(libs.startup)
    //存储
    api(libs.mmkv)
    //图片
    api(libs.glide)
    //协程
    api(libs.bundles.coroutines)
    api(libs.lifecycleKtx)
    api(libs.livedataKtx)
    api(libs.viewmodelKtx)

    //hilt
    implementation(libs.hilt)
    kapt(libs.hiltKapt)

    api(project(":ScreenSupport"))
}