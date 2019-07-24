package com.kutluoglu.remote.mapper

import com.kutluoglu.data.model.DataContent
import com.kutluoglu.remote.model.RelatedTopic
import java.net.URLDecoder
import javax.inject.Inject

/**
 * Created by F.K. on 2019-07-16.
 *
 */

open class ContentMapper @Inject constructor() : EntityMapper<RelatedTopic, DataContent> {
    override fun mapFromRemote(type: RelatedTopic): DataContent {
        return DataContent(getTitle(type.firstURL), type.result, type.icon.uRL)
    }

    private fun getTitle(firstURL: String): String {
        val segments = firstURL.split("/")
        return URLDecoder.decode(segments[(segments.size - 1)].replace("_", " "), "UTF8")
    }
}