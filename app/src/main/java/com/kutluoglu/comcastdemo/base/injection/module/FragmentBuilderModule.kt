package com.kutluoglu.comcastdemo.base.injection.module

import com.kutluoglu.comcastdemo.main.features.content.Contents
import com.kutluoglu.comcastdemo.main.features.detail.ContentDetail
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by F.K. on 2019-07-16.
 *
 */

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeContentFragment() : Contents

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment() : ContentDetail
 }