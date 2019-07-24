package com.kutluoglu.comcastdemo.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.kutluoglu.comcastdemo.R
import com.kutluoglu.comcastdemo.base.BaseDialog

/**
 * Created by F.K. on 2019-07-16.
 *
 */

class ProgressSpinner (context: Context) : BaseDialog() {
    override val dialogView: View by lazy {
        LayoutInflater.from(context).inflate(R.layout.progress_bar, null)
    }
    override val builder: AlertDialog.Builder = AlertDialog.Builder(context).setView(dialogView)
}