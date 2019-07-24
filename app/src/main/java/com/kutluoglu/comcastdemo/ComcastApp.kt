package com.kutluoglu.comcastdemo

import android.app.Activity
import android.app.Application
import com.kutluoglu.comcastdemo.base.injection.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by F.K. on 2019-07-16.
 *
 */

class ComcastApp : Application(), HasActivityInjector {
    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector
}