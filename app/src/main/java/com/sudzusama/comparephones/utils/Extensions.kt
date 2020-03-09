package com.sudzusama.comparephones.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.showKeyboard() {
    val imm: InputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
}

fun Activity.hideKeyboard() {
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)
}


fun View.fadeIn() {
    this.visibility = View.VISIBLE
    this.animate()
        .setDuration(250)
        .alpha(1.0f)


}

fun View.fadeOut() {
    this.animate()
        .setDuration(250)
        .alpha(0.0f)
        .withEndAction {
            this.visibility = View.GONE
        }

}