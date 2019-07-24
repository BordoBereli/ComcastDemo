package com.kutluoglu.comcastdemo.base

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.kutluoglu.comcastdemo.BuildConfig
import com.kutluoglu.comcastdemo.base.injection.Injectable
import com.kutluoglu.comcastdemo.main.MainActivity
import com.kutluoglu.comcastdemo.utils.NetworkUtils
import com.kutluoglu.comcastdemo.utils.extensions.positiveButton
import com.kutluoglu.comcastdemo.utils.extensions.showDialog
import com.kutluoglu.comcastdemo.utils.extensions.showProgressSpinner
import com.kutluoglu.presentation.viewModels.AppViewModelFactory
import javax.inject.Inject

open class BaseFragment : Fragment(), Injectable {
    @Inject lateinit var viewModelFactory: AppViewModelFactory
    @Inject lateinit var network: NetworkUtils

    private var spinnerProgress: AlertDialog? = null
    lateinit var act: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        act  = activity as MainActivity
    }

    fun showSpinner() {
        if(spinnerProgress == null) {
            spinnerProgress = showProgressSpinner()
        }

        spinnerProgress?.let {
            if(!it.isShowing) it.show()
        }

    }

    fun dismissSpinner() {
        spinnerProgress?.let {
            if(it.isShowing) it.dismiss()
        }
    }

    fun showDialog(customMsg: String) {
        context?.showDialog {
            setTitle(BuildConfig.AppName)
            setMessage(customMsg)
            positiveButton()
        }
    }

    fun showDialogWithCustomAction(customMsg: String, builderFunction: AlertDialog.Builder.() -> Any) {
        context?.showDialog {
            setTitle(BuildConfig.AppName)
            setMessage(customMsg)
            builderFunction()
        }
    }


    override fun onDestroy() {
        dismissSpinner()
        super.onDestroy()
    }
}
