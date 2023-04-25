package com.parvinderr.notesdiary.utils

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
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

fun View.isVisible(): Boolean {
    return this.visibility == View.VISIBLE
}

fun View.goneWithFade(duration: Long = 300) {
    this.animate().alpha(0f).setDuration(duration).setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            super.onAnimationEnd(animation)
            this@goneWithFade.visibility = View.GONE
        }
    })
}

fun View.showWithFade(duration: Long = 300) {
    this.alpha = 0f
    this.visibility = View.VISIBLE
    this.animate().alpha(1f).setDuration(duration).setListener(null)

}