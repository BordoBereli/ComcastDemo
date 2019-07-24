package com.kutluoglu.comcastdemo.utils.extensions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.kutluoglu.comcastdemo.R
import com.kutluoglu.comcastdemo.base.BaseFragment
import com.kutluoglu.comcastdemo.utils.ProgressSpinner
import kotlinx.android.synthetic.main.app_bar_main.view.*

/**
 * Created by F.K. on 2019-07-16.
 *
 */

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachtoRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, attachtoRoot)
}

/**
 * Toolbar Extentions
 */

fun Toolbar.setAppTitle(title: String) { toolbar.title= title }

/**
 * Show Spinner Progress Dialog
 */
fun BaseFragment.showProgressSpinner(): AlertDialog = ProgressSpinner(
    this.context!!
).create()

/**
 * ImageView to set Drawable by Resource ID
 */

fun ImageView.setImageWithUrl(imageUrl: String) {
    Glide.with(context).load(imageUrl)
        .placeholder(R.mipmap.character_icon_sad_big)
        .into(this)
}

/**
 * Set any View visible or gone state
 */

fun View.shouldBeVisible(visible: Boolean) {
    visibility = if(visible) View.VISIBLE else View.GONE
}

/**
 * Show a keyboard for View.
 */
fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

/**
 * Try to hide the keyboard and returns whether it worked
 * https://stackoverflow.com/questions/1109022/close-hide-the-android-soft-keyboard
 */
fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}

/**
 * Dialog Extensions
 */

inline fun Context.showDialog(cancelable: Boolean = true, cancelableTouchOutSide: Boolean = true,
                              builderFunction: AlertDialog.Builder.() -> Any) {
    val builder = AlertDialog.Builder(this)
    builder.builderFunction()

    val dialog = builder.create()

    dialog.setCancelable(cancelable)
    dialog.setCanceledOnTouchOutside(cancelableTouchOutSide)

    dialog.show()
}

inline fun AlertDialog.Builder.positiveButton(text: String = "Okay", crossinline handleClick: (i: Int) -> Unit = {}) {
    this.setPositiveButton(text) { _, i -> handleClick(i) }
}




