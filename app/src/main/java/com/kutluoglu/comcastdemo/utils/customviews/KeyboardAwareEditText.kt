package com.kutluoglu.comcastdemo.utils.customviews

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.appcompat.widget.AppCompatEditText
import com.kutluoglu.comcastdemo.utils.extensions.showKeyboard

class KeyboardAwareEditText : AppCompatEditText {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFocusChanged(focused: Boolean, direction: Int, previouslyFocusedRect: Rect?) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect)

        if (focused) {
            showKeyboard()
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        isFocusableInTouchMode = true
        isFocusable = true
        requestFocus()
        showKeyboard()
        return super.onTouchEvent(event)
    }

    private var onBackKeyListener: OnBackKeyListener? = null

    fun setOnBackKeyListener(listener: OnBackKeyListener) {
        onBackKeyListener = listener
    }

    override fun onKeyPreIme(keyCode: Int, event: KeyEvent): Boolean {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
            val isBack = onBackKeyListener?.onBackKey() ?: false
            if(isBack) clearFocus()
            return isBack
        }

        return super.onKeyPreIme(keyCode, event)
    }

    interface OnBackKeyListener {
        fun onBackKey(): Boolean
    }

}