package br.com.edsilfer.kotlin_support.extensions

import android.app.Service
import android.graphics.Color
import android.graphics.Typeface
import android.text.TextUtils
import android.util.TypedValue
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import br.com.edsilfer.android.messenger.service.softkeyboard.SoftKeyboard
import br.com.edsilfer.kotlin_support.model.xml.Text

fun TextView.slideHorizontal() {
    ellipsize = TextUtils.TruncateAt.MARQUEE
    marqueeRepeatLimit = -1
    isSelected = true
}

fun TextView.addSoftwareKeyboardListener(listener: SoftKeyboard.SoftKeyboardListener) {
    val im = context.getSystemService(Service.INPUT_METHOD_SERVICE) as InputMethodManager
    val softKeyboard = SoftKeyboard(rootView as ViewGroup, im)
    softKeyboard.setSoftKeyboardCallback(listener)
}

fun TextView.setStyle(style: Text) {
    typeface = Typeface.create(style.font, Typeface.NORMAL)
    setTypeface(null, style.typefaceStyle)
    setTextSize(TypedValue.COMPLEX_UNIT_SP, style.size)
    setTextColor(Color.parseColor(style.color))
}
