plugins {
    id("convention.android.library")
}

android {
    namespace = "com.wkkun.base"
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

    api(project(":ScreenSupport"))
}