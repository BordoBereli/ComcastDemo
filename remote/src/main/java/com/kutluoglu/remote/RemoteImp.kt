package com.kutluoglu.remote

import com.kutluoglu.data.model.DataContent
import com.kutluoglu.data.repository.Remote
import com.kutluoglu.remote.ServiceFactory.CONTENT_URL
import com.kutluoglu.remote.mapper.ContentMapper
import com.kutluoglu.remote.model.RemoteContent
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-07-16.
 *
 */

/**
 * Remote implementation for retrieving Demo Api instances. This class implements the
 * [Remote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */

class RemoteImp @Inject constructor(
    private val demoApi: DemoApi,
    private val contentMapper: ContentMapper
) : Remote {
    override fun getContents(): Single<List<DataContent>> {
        return demoApi.getContents(CONTENT_URL).map { content: RemoteContent ->
            println(content.toString())
            val returnList = mutableListOf<DataContent>()
            content.relatedTopics.map {
                returnList.add(contentMapper.mapFromRemote(it))
            }

            returnList
        }
    }

}