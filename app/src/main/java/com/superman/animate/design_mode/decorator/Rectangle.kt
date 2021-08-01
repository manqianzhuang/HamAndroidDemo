package com.superman.animate.design_mode.decorator

import com.blankj.utilcode.util.LogUtils

/**
 * 被装饰者
 */
class Rectangle: Shape {
    override fun draw() {
        LogUtils.e("Shape: Rectangle")
    }
}