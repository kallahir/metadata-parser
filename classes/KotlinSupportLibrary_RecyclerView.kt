package br.com.edsilfer.kotlin_support.extensions

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

fun RecyclerView.initListItems(
        activity: AppCompatActivity,
        layout: Int,
        onClickListener: RecyclerViewOnItemClick?,
        adp: RecyclerView.Adapter<RecyclerView.ViewHolder>): RecyclerView {
    layoutManager = LinearLayoutManager(activity, layout, false)
    adapter = adp

    if (onClickListener != null) {
        addOnItemTouchListener(onClickListener)
    }

    return this
}

fun RecyclerView.fixRecyclerViewSize(itemHeight: Int) {
    val viewHeight = itemHeight * adapter.itemCount
    layoutParams.height = viewHeight
}

fun RecyclerView.fixListViewSize(itemHeight: Int) {
    val viewHeight = itemHeight * adapter.itemCount
    layoutParams.height = viewHeight
}

class RecyclerViewOnItemClick(context: Context, private val mListener: OnItemClickListener?) : RecyclerView.OnItemTouchListener {
    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    internal var mGestureDetector: GestureDetector

    init {
        mGestureDetector = GestureDetector(context, object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return true
            }
        })
    }

    override fun onInterceptTouchEvent(view: RecyclerView, e: MotionEvent): Boolean {
        val childView = view.findChildViewUnder(e.x, e.y)
        if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
            mListener.onItemClick(childView, view.getChildLayoutPosition(childView))
        }
        return false
    }

    override fun onTouchEvent(view: RecyclerView, motionEvent: MotionEvent) {
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    }
}