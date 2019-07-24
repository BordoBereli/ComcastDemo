package com.kutluoglu.comcastdemo.main.features.content

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.kutluoglu.comcastdemo.R
import com.kutluoglu.presentation.model.ContentView

/**
 * Created by F.K. on 2019-07-16.
 *
 */

open class ContentItemHolder(private val contentItem: View) : RecyclerView.ViewHolder(contentItem) {
    private val title: TextView = contentItem.findViewById(R.id.content_title)

    fun bindViewData(content: ContentView, contentListener:(ContentView, View) -> Unit) {
        content.title.let { title.text = it }
        contentItem.setOnClickListener { contentListener(content, contentItem) }
    }
}