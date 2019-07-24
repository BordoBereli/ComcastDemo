package com.kutluoglu.remote

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by F.K. on 2019-07-16.
 *
 */

/**
 * Provide "get" methods to create instances of [DemoApi]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */
object ServiceFactory {
    private lateinit var BASE_URL: String
    var CONTENT_URL: String = ""

    fun getDemoApi(isDebug: Boolean, baseUrl: String, contentUrl: String) : DemoApi {
        BASE_URL = baseUrl
        CONTENT_URL = contentUrl

        val okHttpClient = getOkHttpClient(getLoggingIntercepter(isDebug))

        return getDemoApi(okHttpClient, getGson())
    }

    private fun getDemoApi(okHttpClient: OkHttpClient, gson: Gson): DemoApi {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(DemoApi::class.java)
    }

    private fun getOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun getGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .setDateFormat("dd/MM/yyyy HH:mm")
            .create()
    }

    private fun getLoggingIntercepter(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()

        logging.level = if (isDebug)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE

        return logging
    }
}