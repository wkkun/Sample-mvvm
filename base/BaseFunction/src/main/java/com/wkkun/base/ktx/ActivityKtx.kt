package com.wkkun.base.ktx

import android.app.Activity

fun Activity?.isActive(): Boolean {
    return this != null && !this.isFinishing && !this.isDestroyed
}

