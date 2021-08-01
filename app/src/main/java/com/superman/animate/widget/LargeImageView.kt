package com.superman.animate.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import java.io.IOException
import java.io.InputStream
import java.util.jar.Attributes

/**
 * 高清大图、长图的ImageView
 */
@SuppressLint("ViewConstructor")
class LargeImageView(mContext: Context, attrSet: AttributeSet) : View(mContext, attrSet) {

    private val options = BitmapFactory.Options()
    var mDetector: MoveGestureDetector ?= null
    var mDecoder: BitmapRegionDecoder ?= null
    var mImageWidth = 0
    var mImageHeight = 0
    @Volatile var mRect = Rect()

    //对象创建完成后，自动调用init函数
    init {
        options.inPreferredConfig = Bitmap.Config.RGB_565
        mDetector = MoveGestureDetector(
            mContext,
            onMoveStart = {
                Log.e("LargeImageView", "---start  ${it.moveX}, ${it.moveY}")
                return@MoveGestureDetector true
            },
            onMove = {
                val moveX = it.moveX
                val moveY = it.moveY
                Log.e("LargeImageView", "---move  $moveX, $moveY")
//                if (mImageWidth > width) {
                    mRect.offset((-moveX).toInt(), 0)
                    checkWidth()
                    invalidate()
//                }
//                if (mImageHeight > height) {
                    mRect.offset(0, (-moveY).toInt())
                    checkHeight()
                    invalidate()
//                }
                return@MoveGestureDetector false
            },
            onMoveEnd = {

            }
        )
    }

    fun setInputStream(inputStream: InputStream) {
        try {
            mDecoder = BitmapRegionDecoder.newInstance(inputStream, false)
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeStream(inputStream, null, options)
            mImageWidth = options.outWidth
            mImageHeight = options.outHeight
            requestLayout()
            invalidate()
        } catch (e: IOException) {
            print(e.message)
        } finally {
            inputStream.close()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mDetector?.onTouchEvent(event!!)
        return true
    }

    //渲染控件
    override fun onDraw(canvas: Canvas) {
        val bm = mDecoder!!.decodeRegion(mRect, options)
        canvas.drawBitmap(bm, 0f, 0f, null)
    }

    //测量控件的宽高
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth
        val height = measuredHeight
        val imageWidth = mImageWidth
        val imageHeight = mImageHeight
        mRect.left = imageWidth / 2 - width / 2
        mRect.top = imageHeight / 2 - height / 2
        mRect.right = mRect.left + width
        mRect.bottom = mRect.top + height
    }


    private fun checkWidth() {
        val rect = mRect
        val imageWidth = mImageWidth
        if (rect.right > imageWidth) {
            rect.right = imageWidth
            rect.left = imageWidth - width
        }
        if (rect.left < 0) {
            rect.left = 0
            rect.right = width
        }
    }

    private fun checkHeight() {
        val rect: Rect = mRect
        val imageHeight = mImageHeight
        if (rect.bottom > imageHeight) {
            rect.bottom = imageHeight
            rect.top = imageHeight - height
        }
        if (rect.top < 0) {
            rect.top = 0
            rect.bottom = height
        }
    }

}
