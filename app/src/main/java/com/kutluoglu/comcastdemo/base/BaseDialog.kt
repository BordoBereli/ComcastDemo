package com.kutluoglu.comcastdemo.base

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.appcompat.app.AlertDialog

/**
 * Created by F.K. on 2019-07-16.
 *
 */

abstract class BaseDialog {
    abstract val dialogView: View
    abstract val builder: AlertDialog.Builder

    // Required Tools
    open var canceable: Boolean = true
    open var isBackGroundTransparent: Boolean = true
    open var msgContent: String = ""

    // Dialog
    open var dialog: AlertDialog? = null

    // Dailog Creation
    open fun create(): AlertDialog {
        dialog = builder
            .setCancelable(canceable)
            .setMessage(msgContent)
            .create()

        // For customised dailogs
        if (isBackGroundTransparent) {
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        return dialog!!
    }

    open fun onCancelListener(func: () -> Unit): AlertDialog.Builder? =
            builder.setOnCancelListener { func() }

}