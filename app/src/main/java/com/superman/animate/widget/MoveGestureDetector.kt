package com.superman.animate.widget

import android.content.Context
import android.graphics.PointF
import android.util.Log
import android.view.MotionEvent

class MoveGestureDetector(
    mContext: Context,
    onMoveStart: (detector: MoveGestureDetector) -> Boolean,
    onMove: (detector: MoveGestureDetector)-> Boolean,
    onMoveEnd: (detector: MoveGestureDetector)-> Unit
) : BaseGestureDetector(mContext) {

    val mExtenalPointer: PointF = PointF()
    private var mCurrentPointer: PointF ?= null
    private var mPrePointer: PointF ?= null
    var moveStart = onMoveStart
    var move = onMove
    var moveEnd = onMoveEnd

    var moveX: Float = 0f
        get() = mExtenalPointer.x
        set(value) {
            field = value
        }

    var moveY: Float = 0f
        get() = mExtenalPointer.y
        set(value) {
            field = value
        }

    override fun handleInProgressEvent(event: MotionEvent) {
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_CANCEL or MotionEvent.ACTION_UP -> {
                moveEnd(this)
                resetState()
            }
            MotionEvent.ACTION_MOVE -> {
                updateStateByEvent(event)
                val update = move(this)
                if (update) {
                    mPreMotionEvent!!.recycle()
                    mPreMotionEvent = MotionEvent.obtain(event)
                }
            }
        }
    }

    override fun handleStartProgressEvent(event: MotionEvent) {
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                resetState()
                mPreMotionEvent = MotionEvent.obtain(event)
                updateStateByEvent(event)
            }
            MotionEvent.ACTION_MOVE -> {
                mGestureInProgress = moveStart(this)
            }
        }
    }

    override fun updateStateByEvent(event: MotionEvent) {
        val prev = mPreMotionEvent
        mPrePointer = calculateFocalPointer(prev!!)
        mCurrentPointer = calculateFocalPointer(event)

        val mSkipThisMoveEvent = prev.pointerCount != event.pointerCount
        mExtenalPointer.x = if (mSkipThisMoveEvent) 0f else mCurrentPointer!!.x - mPrePointer!!.x
        mExtenalPointer.y = if (mSkipThisMoveEvent) 0f else mCurrentPointer!!.y - mPrePointer!!.y
        moveX = mExtenalPointer.x
        moveY = mExtenalPointer.y
        Log.e("LargeImageView", "### ${mExtenalPointer.x}, ${mExtenalPointer.y}")
    }

    private fun calculateFocalPointer(event: MotionEvent): PointF {
        val count = event.pointerCount;
        var x = 0f
        var y = 0f
        for (i in 0 until count) {
            x += event.getX(i)
            y += event.getY(i)
        }
        x /= count;
        y /= count;

        return PointF(x, y)
    }

}

interface OnMoveGestureListener {
    fun onMoveStart(detector: MoveGestureDetector): Boolean
    fun onMove(detector: MoveGestureDetector): Boolean
    fun onMoveEnd(detector: MoveGestureDetector)
}

class SimpleGestureDetector : OnMoveGestureListener {
    override fun onMoveStart(detector: MoveGestureDetector): Boolean = true

    override fun onMove(detector: MoveGestureDetector): Boolean = false

    override fun onMoveEnd(detector: MoveGestureDetector) {
        TODO("Not yet implemented")
    }

}