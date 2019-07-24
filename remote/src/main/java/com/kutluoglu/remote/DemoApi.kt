package com.kutluoglu.remote

import com.kutluoglu.remote.model.RemoteContent
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by F.K. on 2019-07-16.
 *
 */

/**
 * Defines the abstract methods used for interacting with the Demo API
 */

interface DemoApi {
    /**
     * Get RemoteContent List
     */
    @GET
    fun getContents(@Url CONTENT_URL: String) : Single<RemoteContent>

}