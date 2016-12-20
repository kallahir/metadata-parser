package br.com.edsilfer.kotlin_support.extensions

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.view.View.MeasureSpec


fun View.getBitmapDrawable(): BitmapDrawable {
    val spec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
    measure(spec, spec)
    layout(0, 0, measuredWidth, measuredHeight)
    val b = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888)
    val c = Canvas(b)
    c.translate(-scrollX.toFloat(), -scrollY.toFloat())
    draw(c)
    isDrawingCacheEnabled = true
    val cacheBmp = drawingCache
    val viewBmp = cacheBmp.copy(Bitmap.Config.ARGB_8888, true)
    destroyDrawingCache()
    return BitmapDrawable(resources, viewBmp)
}

fun View.getBitmap(): Bitmap {
    isDrawingCacheEnabled = true
    val bitmap = Bitmap.createBitmap(getDrawingCache())
    isDrawingCacheEnabled = false
    return bitmap
}

fun View.locateView(): Rect? {
    val loc_int = IntArray(2)
    try {
        getLocationOnScreen(loc_int)
    } catch (npe: NullPointerException) {
        return null
    }

    val location = Rect()
    location.left = loc_int[0]
    location.top = loc_int[1]
    location.right = location.left + width
    location.bottom = location.top + height

    return location
}