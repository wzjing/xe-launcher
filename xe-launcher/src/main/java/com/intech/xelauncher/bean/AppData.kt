package com.intech.xelauncher.bean

import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

data class AppData(
    val icon: Drawable,
    val name: String,
    val packageName: String,
)
