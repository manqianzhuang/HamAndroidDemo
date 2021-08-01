package com.superman.animate.widget

import android.content.Context
import android.view.MotionEvent

abstract class BaseGestureDetector(var mContext: Context) {

    open var mGestureInProgress: Boolean = false
    open var mPreMotionEvent: MotionEvent? = null
    open var mCurrentMotionEvent: MotionEvent? = null

    fun onTouchEvent(event: MotionEvent): Boolean {
        if (mGestureInProgress) {
            handleInProgressEvent(event)
        } else {
            handleStartProgressEvent(event)
        }
        return true
    }

    abstract fun handleInProgressEvent(event: MotionEvent)

    abstract fun handleStartProgressEvent(event: MotionEvent)

    abstract fun updateStateByEvent(event: MotionEvent)

    fun resetState() {
        mPreMotionEvent?.recycle()
        mPreMotionEvent = null
        mCurrentMotionEvent?.recycle()
        mCurrentMotionEvent = null
        mGestureInProgress = false
    }

}