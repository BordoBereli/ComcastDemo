package com.kutluoglu.comcastdemo.base.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kutluoglu.comcastdemo.base.injection.ViewModelKey
import com.kutluoglu.presentation.viewModels.AppViewModelFactory
import com.kutluoglu.presentation.viewModels.content.ContentViewModel
import com.kutluoglu.presentation.viewModels.detail.ContentDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by F.K. on 2019-07-16.
 *
 */

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ContentViewModel::class)
    abstract fun bindContentViewModel(contentViewModel: ContentViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContentDetailViewModel::class)
    abstract fun bindContentDetailViewModel(detailViewModel: ContentDetailViewModel) : ViewModel


}