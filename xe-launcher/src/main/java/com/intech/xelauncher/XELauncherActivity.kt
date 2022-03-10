package com.intech.xelauncher

import android.Manifest
import android.app.Application
import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowInsets
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import com.intech.xelauncher.ui.theme.XELauncherTheme
import com.intech.xelauncher.util.checkPermission

class XELauncherActivity : ComponentActivity() {

    private val viewModel = LauncherViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val wm = getSystemService(Context.WALLPAPER_SERVICE) as WallpaperManager

        checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE) { granted ->
            if (granted) {
                window.setBackgroundDrawable(wm.drawable)
            }
        }

        if (Build.VERSION.SDK_INT >= 30) {
            window.decorView.windowInsetsController?.show(WindowInsets.Type.statusBars())
            window.decorView.windowInsetsController?.show(WindowInsets.Type.navigationBars())
        }

        setContent {
            XELauncherTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = Color.Transparent) {

                    AppScreen(viewModel)
                }
            }
        }

        viewModel.init(this.applicationContext)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppScreen(viewModel: LauncherViewModel) {
    val apps by viewModel.installedApps.observeAsState()
    LazyVerticalGrid(cells = GridCells.Fixed(5)) {
        items(apps?.size ?: 0) { index ->
            apps?.get(index)?.let {
                Image(
                    modifier = Modifier.size(48.dp).padding(vertical = 12.dp),
                    bitmap = it.icon.toBitmap().asImageBitmap(),
                    contentDescription = "icon"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    XELauncherTheme {
        AppScreen(LauncherViewModel())
    }
}