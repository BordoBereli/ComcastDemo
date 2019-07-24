package com.kutluoglu.domain.repository

import com.kutluoglu.domain.model.Content
import com.kutluoglu.domain.model.ContentDetail
import io.reactivex.Single

/**
 * Created by F.K. on 2019-07-16.
 *
 */

/**
 * Interface defining methods for how the data layer can pass data to and from the Domain layer.
 * This is to be implemented by the data layer, setting the requirements for the
 * operations that need to be implemented
 */

interface ComcastRepository {
    /**
     * Get List of [Content]
     */
    fun getContentList() : Single<List<Content>>

    /**
     * Get [ContentDetail] by its title.
     */
    fun getContentDetailBy(title: String?) : Single<ContentDetail>
}