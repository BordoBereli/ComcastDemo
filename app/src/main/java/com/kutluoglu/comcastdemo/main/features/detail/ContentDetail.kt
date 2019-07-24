package com.kutluoglu.comcastdemo.main.features.detail

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.kutluoglu.comcastdemo.R
import com.kutluoglu.comcastdemo.base.BaseFragment
import com.kutluoglu.comcastdemo.utils.extensions.hideKeyboard
import com.kutluoglu.comcastdemo.utils.extensions.inflate
import com.kutluoglu.comcastdemo.utils.extensions.setAppTitle
import com.kutluoglu.comcastdemo.utils.extensions.setImageWithUrl
import com.kutluoglu.presentation.model.ContentView
import com.kutluoglu.presentation.model.DetailView
import com.kutluoglu.presentation.resource.ResourceState
import com.kutluoglu.presentation.viewModels.detail.ContentDetailViewModel
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_content_detail.*

/**
 * Created by F.K. on 2019-07-16.
 *
 */

class ContentDetail : BaseFragment(), DetailContract {
    private lateinit var contentDetailViewModel: ContentDetailViewModel
    private lateinit var detail: DetailView
    var content: ContentView? = null
    var contentTitle: String? = null
    var isDialogOn = false
    var isTablet = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Inflate the layout for this fragment
        return container?.inflate(R.layout.fragment_content_detail)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isTablet = context?.resources?.getBoolean(R.bool.isTablet) ?: false

        if(isTablet) {
            contentTitle = arguments?.let {
                it.getString("title")
            }
            view?.hideKeyboard()
            view?.requestFocus()
        } else {
            content = arguments?.let {
                ContentDetailArgs.fromBundle(it).contentView
            }
        }
        initializeViewModel()
    }

    override fun initializeUI(detailView: DetailView) {
        detail = detailView
        setToolbarTitle()
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            detail.title.let { detail_title.text = Html.fromHtml(it, Html.FROM_HTML_MODE_LEGACY) }
            detail.description.let { detail_description.text = Html.fromHtml(it, Html.FROM_HTML_MODE_LEGACY)}
        } else {
            detail.title.let { detail_title.text = Html.fromHtml(it) }
            detail.description.let { detail_description.text = Html.fromHtml(it) }
        }
        detail.iconUrl.let { detail_imageView.setImageWithUrl(it) }
    }


    private fun setToolbarTitle() {
        act.toolbar.setAppTitle(detail.title.let { it })
    }

    override fun initializeViewModel() {
        contentDetailViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ContentDetailViewModel::class.java)

        observeDetailViewModel()
    }

    private fun observeDetailViewModel() {
        contentDetailViewModel.getContentDetailLiveData().observe(this, Observer {
            when(it.status) {
                ResourceState.LOADING -> {
                    showSpinner()
                }
                ResourceState.SUCCESS -> {
                    dismissSpinner()
                    it.data?.let { detailView ->
                        initializeUI(detailView)
                    }
                }
                ResourceState.ERROR -> {
                    dismissSpinner()
                    showError(it.message)
                }
            }
        })

        if (isTablet) {
            contentTitle?.let {
                contentDetailViewModel.getContentDetailByTitle(it)
            }
        } else {
            content?.let {
                contentDetailViewModel.getContentDetailByTitle(it.title)
            }
        }
    }

    private fun showError(message: String?) {
        if (!isDialogOn) {
            isDialogOn = true
            if (network.hasNotInternet()) {
                showDialogWithCustomAction(getString(R.string.connection_error)) {
                    val positiveButton = this.setPositiveButton("Go Back") { _, _ ->
                        if (!resources.getBoolean(R.bool.isTablet)) {
                            view?.findNavController()?.navigateUp()
                        }
                        isDialogOn = false
                    }
                    positiveButton
                }
            } else {
                showDialog("$message")
            }
        }
    }
}