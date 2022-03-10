package com.intech.xelauncher.util

import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts

fun ComponentActivity.checkPermission(permission: String, cb: (granted: Boolean) -> Unit = {}) {
    registerForActivityResult(ActivityResultContracts.RequestPermission(), cb).launch(permission)
}