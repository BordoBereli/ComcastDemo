package com.kutluoglu.comcastdemo.base.injection.component

import android.app.Application
import com.kutluoglu.comcastdemo.ComcastApp
import com.kutluoglu.comcastdemo.base.injection.module.AppModule
import com.kutluoglu.comcastdemo.base.injection.module.MainActivityModule
import com.kutluoglu.comcastdemo.base.injection.scopes.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by F.K. on 2019-07-16
 *
 */

@PerApplication
@Component(
    modules = [
        AndroidSupportInjectionModule::class, // Because of using AndroidX fragment
        AppModule::class,
        MainActivityModule::class
    ]
)

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : AppComponent
    }

    fun inject(demoApp: ComcastApp)
}