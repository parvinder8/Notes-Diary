package com.parvinderr.notesdiary.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


fun Context.showToast(text: String, type: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, type).show()
}

fun View.showSnackBar(text: String, type: Int = Toast.LENGTH_SHORT) {
    val snackBar = Snackbar.make(this, text, type)
    snackBar.show()
}

fun View.showKeyboard(delay: Long = 200L) {
    val context = this.context ?: return
    this.postDelayed(focusNotFound@{
        val focus = this.findFocus() ?: return@focusNotFound
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(focus, 0)
    }, delay)
}

fun View.hideKeyboard() {
    val context = this.context ?: return
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.INVISIBLE
}