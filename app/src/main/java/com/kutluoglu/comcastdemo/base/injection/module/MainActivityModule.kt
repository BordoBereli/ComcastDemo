package com.kutluoglu.comcastdemo.base.injection.module

import com.kutluoglu.comcastdemo.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module used to provide dependencies at an activity-level.
 *
 */

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun contributeMainActivity() : MainActivity
}
