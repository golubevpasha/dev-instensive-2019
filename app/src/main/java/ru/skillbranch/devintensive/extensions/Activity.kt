package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Rect
import android.view.View
import android.view.inputmethod.InputMethodManager



fun Activity.hideKeyboard() = (getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager)
    .hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)

fun Activity.isKeyboardOpen(): Boolean {
    val rootView = findViewById<View>(android.R.id.content)
    val rect = Rect()
    rootView.getWindowVisibleDisplayFrame(rect)
    val heightDiff: Int = rootView.rootView.height - (rect.bottom - rect.top)
    return heightDiff > 100
}

fun Activity.isKeyboardClosed() = !this.isKeyboardOpen()

