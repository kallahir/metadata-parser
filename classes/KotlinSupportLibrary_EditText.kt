package br.com.edsilfer.kotlin_support.extensions

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import br.com.edsilfer.kotlin_support.model.xml.Text

fun EditText.hideSoftKeyboard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(windowToken, 0);
}

fun EditText.setStyle(style: Text) {
    typeface = Typeface.create(style.font, Typeface.NORMAL)
    setTypeface(null, style.typefaceStyle)
    setTextSize(TypedValue.COMPLEX_UNIT_SP, style.size)
    setTextColor(Color.parseColor(style.color))
}
