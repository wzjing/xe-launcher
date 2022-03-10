package com.intech.xelauncher

import android.content.Context
import android.content.pm.PackageManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.intech.xelauncher.bean.AppData
import com.intech.xelauncher.util.async

class LauncherViewModel : ViewModel() {

    private val TAG = this::class.java.simpleName

    val installedApps: MutableLiveData<List<AppData>> = MutableLiveData(emptyList())


    fun init(context: Context) {
        async {
            val packages =
                context.packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
            installedApps.postValue(packages.map { appInfo ->
                AppData(
                    icon = appInfo.loadIcon(context.packageManager),
                    name = appInfo.name.orEmpty(),
                    packageName = appInfo.packageName,
                )
            })
        }
    }
}