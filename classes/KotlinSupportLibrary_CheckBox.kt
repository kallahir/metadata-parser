package br.com.edsilfer.kotlin_support.extensions

import android.content.res.ColorStateList
import android.support.v7.widget.AppCompatCheckBox

fun AppCompatCheckBox.setColor(color: Int) {
    val colorStateList = ColorStateList(
            arrayOf(intArrayOf(-android.R.attr.state_enabled), intArrayOf(android.R.attr.state_enabled)),
            intArrayOf(context.resources.getColor(color), context.resources.getColor(color))
    )
    supportButtonTintList = colorStateList
}