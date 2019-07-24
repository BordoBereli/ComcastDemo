package com.kutluoglu.comcastdemo.main.features.content

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kutluoglu.comcastdemo.R
import com.kutluoglu.comcastdemo.utils.extensions.inflate
import com.kutluoglu.presentation.model.ContentView

/**
 * Created by F.K. on 2019-07-16.
 *
 */

class ContentRvAdapter(
    private val contentListener: (ContentView, View) -> Unit
) : RecyclerView.Adapter<ContentItemHolder>() {

    private val contentList = mutableListOf<ContentView>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentItemHolder {
        return ContentItemHolder(parent.inflate(R.layout.content_item))
    }

    override fun getItemCount() = contentList.size

    override fun onBindViewHolder(holder: ContentItemHolder, position: Int) {
        holder.bindViewData(contentList[position], contentListener)
    }

    fun setRvData(list: List<ContentView>) {
        contentList.clear()
        contentList.addAll(list)
        notifyDataSetChanged()
    }
}