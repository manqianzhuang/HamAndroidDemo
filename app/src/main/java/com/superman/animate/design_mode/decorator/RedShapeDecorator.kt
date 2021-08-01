package com.superman.animate.design_mode.decorator

import com.blankj.utilcode.util.LogUtils

/**
 * 装饰者的具体实现类
 */
class RedShapeDecorator(decoratedShape: Shape): ShapeDecorator(decoratedShape) {

    override fun draw() {
        decoratedShape.draw()
        setBorder()
    }

    private fun setBorder() {
        LogUtils.e("Border Color: Red")
    }

}