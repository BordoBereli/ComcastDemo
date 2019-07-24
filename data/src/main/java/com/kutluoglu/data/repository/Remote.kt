package com.kutluoglu.data.repository

import com.kutluoglu.data.model.DataContent
import io.reactivex.Single

/**
 * Created by F.K. on 2019-07-16.
 *
 */
interface Remote {
    /**
     * Retrieve a [List] of [DataContent] from the remote API Service
     */
    fun getContents(): Single<List<DataContent>>
}