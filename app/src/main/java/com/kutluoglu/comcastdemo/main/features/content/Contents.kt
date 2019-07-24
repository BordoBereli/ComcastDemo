package com.kutluoglu.comcastdemo.main.features.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.kutluoglu.comcastdemo.BuildConfig
import com.kutluoglu.comcastdemo.R
import com.kutluoglu.comcastdemo.base.BaseFragment
import com.kutluoglu.comcastdemo.utils.customviews.SearchListener
import com.kutluoglu.comcastdemo.utils.extensions.hideKeyboard
import com.kutluoglu.comcastdemo.utils.extensions.inflate
import com.kutluoglu.comcastdemo.utils.extensions.setAppTitle
import com.kutluoglu.comcastdemo.utils.extensions.shouldBeVisible
import com.kutluoglu.presentation.model.ContentView
import com.kutluoglu.presentation.resource.ResourceState
import com.kutluoglu.presentation.viewModels.content.ContentViewModel
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.content_main.error_message
import kotlinx.android.synthetic.main.fragment_contents.*

/**
 * Created by F.K. on 2019-07-16.
 *
 */

class Contents : BaseFragment(), ContentContractor {
    private lateinit var rvAdapter: ContentRvAdapter
    private lateinit var contentViewModel: ContentViewModel
    private var isDialogOn = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Inflate the layout for this fragment
        return container?.inflate(R.layout.fragment_contents)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initializeUI()
        initializeViewModel()
        observeContentViewModel()
        setSearchView()
    }

    override fun initializeUI() {
        if(!::rvAdapter.isInitialized) {
            rvAdapter = ContentRvAdapter { content: ContentView, view: View -> contentClicked(content, view)}
        }
        setContentRv(rvAdapter)
    }

    override fun initializeViewModel() {
        contentViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ContentViewModel::class.java)
    }

    private fun observeContentViewModel() {
        contentViewModel.getContentLiveData().observe(this, Observer {
            when(it.status) {
                ResourceState.LOADING -> {
                    showSpinner()
                }
                ResourceState.SUCCESS -> {
                    dismissSpinner()
                    it.data?.let { list ->
                        rvAdapter.setRvData(list)
                        setVisibilityOfRv()
                    }
                }
                ResourceState.ERROR -> {
                    dismissSpinner()
                    it.message?.let { error ->
                        if (error.contains("search")) {
                            searchResult.setSearchResult(getQuery(error))
                            rv_contents.shouldBeVisible(false)
                        } else {
                            showError(error)
                        }
                    }

                }
            }
        })

        contentViewModel.loadContents()
    }

    private fun getQuery(error: String): String {
        val queryArr = error.split(" ")
        return if(queryArr.size >= 2) queryArr[1] else ""
    }

    private fun showError(errorMsg: String?) {
        if (!isDialogOn) {
            if (network.hasNotInternet()) {
                val connectionMessage = getString(R.string.connection_error)
                if (rvAdapter.itemCount > 0) {
                    Toast.makeText(context, connectionMessage, Toast.LENGTH_SHORT).show()
                } else {
                    isDialogOn = true
                    showDialog(connectionMessage)
                }
            } else {
                setErrorMessage(errorMsg)
            }
        }
    }

    private fun setVisibilityOfRv() {
        rv_contents.shouldBeVisible(true)
        error_message?.shouldBeVisible(false)
        searchResult.setResultNotFound(false)
    }

    private fun setErrorMessage(message: String?) {
        error_message?.text = message
        error_message?.shouldBeVisible(true)
        rv_contents.shouldBeVisible(false)
        if (resources.getBoolean(R.bool.isTablet)) {
            content_holder?.shouldBeVisible(false)
        }
    }

    override fun contentClicked(content: ContentView, view: View) {
        view.hideKeyboard()
        gotoContentDetail(content, view)
        searchResult.clearSearchBar()
    }

    private fun gotoContentDetail(content: ContentView, view: View) {
        val isTablet = context?.resources?.getBoolean(R.bool.isTablet) ?: false
        if (isTablet) {
            val navController = act.findNavController(R.id.tablet_nav_host_fragment)
            val bundle = bundleOf("title" to content.title)
            navController.navigate(R.id.contentDetail, bundle, NavOptions.Builder()
                .setPopUpTo(R.id.contentDetail, true)
                .build())
        } else {
            val action = ContentsDirections.actionContentsToContentDetail(content)
            view.findNavController().navigate(action)
        }
    }

    override fun setContentRv(adapter: ContentRvAdapter) {
        if(rv_contents.adapter == null) {
            rv_contents.setHasFixedSize(true)
            rv_contents.adapter = adapter
        }
        rv_contents.shouldBeVisible(true)
    }

    override fun setSearchView() {
        searchResult.setSearchListener(object : SearchListener {
            override fun search(query: String) {
                contentViewModel.search(query)
            }

            override fun searchActionResult(query: String) {
                contentViewModel.search(query)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        act.toolbar.setAppTitle(BuildConfig.AppName)
        view?.hideKeyboard()
    }
}