package com.kutluoglu.comcastdemo.base.injection.module

import android.app.Application
import android.content.Context
import com.kutluoglu.cache.CacheImp
import com.kutluoglu.cache.DemoSharedPreference
import com.kutluoglu.cache.database.db.DemoDb
import com.kutluoglu.cache.database.mapper.DbContentDetailMapper
import com.kutluoglu.cache.database.mapper.DbContentMapper
import com.kutluoglu.comcastdemo.BuildConfig
import com.kutluoglu.comcastdemo.UiThread
import com.kutluoglu.comcastdemo.base.injection.scopes.PerApplication
import com.kutluoglu.comcastdemo.utils.NetworkUtils
import com.kutluoglu.data.DataRepository
import com.kutluoglu.data.executor.JobExecutor
import com.kutluoglu.data.mapper.ContentDetailMapper
import com.kutluoglu.data.mapper.ContentMapper
import com.kutluoglu.data.repository.Cache
import com.kutluoglu.data.repository.Remote
import com.kutluoglu.data.source.DataStoreFactory
import com.kutluoglu.domain.executor.PostExecutionThread
import com.kutluoglu.domain.executor.ThreadExecutor
import com.kutluoglu.domain.repository.ComcastRepository
import com.kutluoglu.remote.DemoApi
import com.kutluoglu.remote.RemoteImp
import com.kutluoglu.remote.ServiceFactory
import dagger.Module
import dagger.Provides

/**
 * Created by F.K. on 2019-07-16.
 *
 */

@Module(includes = [ViewModelModule::class, MainActivityModule::class])
class AppModule {
    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor) : ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @PerApplication
    internal fun provideSharedPreferences(context: Context): DemoSharedPreference {
        return DemoSharedPreference(context)
    }


    @Provides
    @PerApplication
    internal fun provideRepository(factory: DataStoreFactory,
                                            contentMapper: ContentMapper,
                                            contentDetailMapper: ContentDetailMapper
    ) : ComcastRepository {
        return DataRepository(factory, contentMapper, contentDetailMapper)
    }

    @Provides
    @PerApplication
    internal fun provideCache(demoDb: DemoDb,
                              demoSharedPreference: DemoSharedPreference,
                              dbContentMapper: DbContentMapper,
                              dbContentDetailMapper: DbContentDetailMapper
    ) : Cache {
        return CacheImp(demoDb, demoSharedPreference, dbContentMapper, dbContentDetailMapper)
    }

    @Provides
    @PerApplication
    internal fun provideDemoService(): DemoApi {
        return ServiceFactory.getDemoApi(BuildConfig.DEBUG, BuildConfig.BASE_URL, BuildConfig.CONTENT_URL)
    }

    @Provides
    @PerApplication
    internal fun provideRemote(service: DemoApi,
                                        contentMapper: com.kutluoglu.remote.mapper.ContentMapper
    ): Remote {
        return RemoteImp(service, contentMapper)
    }

    @Provides
    @PerApplication
    internal fun provideDatabase(context: Context): DemoDb {
        return DemoDb.getInstance(context)
    }

    @Provides
    @PerApplication
    internal fun provideNetworkUtils(context: Context) : NetworkUtils {
        return NetworkUtils(context)
    }

}