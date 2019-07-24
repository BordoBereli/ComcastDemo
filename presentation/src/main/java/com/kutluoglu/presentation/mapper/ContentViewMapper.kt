package com.kutluoglu.presentation.mapper

import com.kutluoglu.domain.model.Content
import com.kutluoglu.presentation.model.ContentView
import javax.inject.Inject


/**
 * Created by F.K. on 2019-07-16.
 *
 */

open class ContentViewMapper @Inject constructor() : ViewMapper<List<Content>, List<ContentView>>{
    override fun mapToView(type: List<Content>): List<ContentView> {
        val viewList = mutableListOf<ContentView>()
        type.map {
            viewList.add(ContentView(it.title, it.description, it.iconUrl))
        }
        return viewList
    }

    override fun mapFromView(type: List<ContentView>): List<Content> {
        val domainList = mutableListOf<Content>()
        type.map {
            domainList.add(Content(it.title, it.description, it.iconUrl))
        }
        return domainList
    }
}