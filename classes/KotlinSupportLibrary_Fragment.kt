package br.com.edsilfer.kotlin_support.extensions

import android.support.v4.app.Fragment
import br.com.edsilfer.kotlin_support.service.files.SharedPreferencesUtil

fun Fragment.putProperty(key: String, value: Any) {
    SharedPreferencesUtil.putProperty(activity, key, value)
}

fun Fragment.getProperty(key: String, defaultValue: Any): Any? {
    return SharedPreferencesUtil.getProperty(activity, key, defaultValue)
}
