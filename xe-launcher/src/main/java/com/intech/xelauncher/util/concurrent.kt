package com.intech.xelauncher.util

import kotlinx.coroutines.*

fun async(init: () -> Unit) = runBlocking {
    launch(context = Dispatchers.IO){ init() }
}

fun ui(init: () -> Unit) = runBlocking {
    launch(context = Dispatchers.Main) { init() }
}