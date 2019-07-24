package com.kutluoglu.comcastdemo.utils.customviews

import android.content.Context
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import com.kutluoglu.comcastdemo.R
import com.kutluoglu.comcastdemo.main.MainActivity
import com.kutluoglu.comcastdemo.utils.extensions.hideKeyboard
import com.kutluoglu.comcastdemo.utils.extensions.shouldBeVisible
import kotlinx.android.synthetic.main.view_search_result.view.*

/**
 * Created by F.K. on 2019-07-19.
 *
 */

class CustomSearchView : FrameLayout {
    private lateinit var hintText: String
    private var editTextHeight = 0
    private var searchListener: SearchListener? = null

    constructor(context: Context) : super(context) {initViews()}
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setAttrs(context, attrs)
        initViews()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        setAttrs(context, attrs)
        initViews()
    }

    fun setSearchResult(query: String) {
        setResultNotFound(true)
        searchResultTextView.text = String.format(context.getString(R.string.search_not_found, query))
    }

    fun setSearchListener(listener: SearchListener) {
        searchListener = listener
    }

    fun clearSearchBar() {
        searchEditText.setText("")
        search("")
    }

    fun setResultNotFound(visible: Boolean) {
        layoutResultNotFound.shouldBeVisible(visible)
    }

    private fun setAttrs(context: Context, attrs: AttributeSet?) {
        val typeArr = context.theme.obtainStyledAttributes(
            attrs, R.styleable.SearchResultView, 0,0
        )

        try {
            hintText = typeArr.getString(R.styleable.SearchResultView_hint) ?: ""
            editTextHeight = typeArr.getDimension(
                R.styleable.SearchResultView_editTextHeight,
                resources.getDimension(R.dimen.search_etext_heigth))
                .toInt()
        } finally {
            typeArr.recycle()
        }
    }

    private fun initViews() {
        LayoutInflater.from(context).inflate(R.layout.view_search_result, this, true)

        searchEditText.hint = hintText
        searchEditText.setSingleLine()
        setSearchEditTextHeight()
        setSearchEditTextListener()
        setEditTextOnBackKeyListener()
        layoutResultNotFound.shouldBeVisible(false)
    }

    private fun setSearchEditTextHeight() {
        searchEditText.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, editTextHeight)
    }

    private fun setSearchEditTextListener() {
        deleteBtn.shouldBeVisible(false)
        deleteBtn.setOnClickListener {
            clearSearchBar()
        }

        searchEditText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
                hideKeyboard()
                val query = searchEditText.text.toString().trim()
                if (!TextUtils.isEmpty(query)) {
                    searchListener?.searchActionResult(query)
                    return true
                }
                return false
            }
        })

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(editable: Editable?) {
                search(editable.toString().trim())
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })
        searchEditText.isFocusable = false

    }

    private fun search(query: String) {
        deleteBtn.shouldBeVisible(!TextUtils.isEmpty(query))
        searchListener?.search(query)
    }

    private fun setEditTextOnBackKeyListener() {
        searchEditText.setOnBackKeyListener(object : KeyboardAwareEditText.OnBackKeyListener {
            override fun onBackKey(): Boolean {
                if (searchEditText.hasFocus()) {
                    searchEditText.isFocusable = false
                } else {
                    (context as MainActivity).onBackPressed()
                }
                hideKeyboard()
                return true
            }
        })
    }


}