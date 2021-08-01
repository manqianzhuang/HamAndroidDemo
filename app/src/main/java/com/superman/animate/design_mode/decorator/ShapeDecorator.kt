package com.superman.animate.design_mode.decorator

/**
 * 装饰者
 */
abstract class ShapeDecorator(var decoratedShape: Shape): Shape {
    override fun draw() {
        decoratedShape.draw()
    }
}