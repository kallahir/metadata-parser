package br.com.edsilfer.kotlin_support.extensions

import android.os.Environment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import br.com.edsilfer.kotlin_support.model.Events
import br.com.edsilfer.kotlin_support.model.ISubscriber
import br.com.edsilfer.kotlin_support.service.NotificationCenter

fun Any.log(content: String, level: LogLevel = LogLevel.WARNING) {
    when (level) {
        LogLevel.ERROR -> Log.e(this.javaClass.simpleName, content)
        LogLevel.WARNING -> Log.i(this.javaClass.simpleName, content)
    }
}

fun Any.random(max: Int): Int {
    return (Math.random() * max).toInt()
}

fun Any.notifySubscribers(event: Events, payload: Any?) {
    NotificationCenter.notify(event, payload)
}

fun Any.addEventSubscriber(event: Events, subscriber: ISubscriber) {
    NotificationCenter.RegistrationManager.registerForEvent(event, subscriber)
}

fun Any.removeEventSubscriber(event: Events, subscriber: ISubscriber) {
    NotificationCenter.RegistrationManager.unregisterForEvent(event, subscriber)
}