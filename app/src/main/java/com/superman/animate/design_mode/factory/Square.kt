package com.superman.animate.design_mode.factory

import com.blankj.utilcode.util.LogUtils

class Square: Shape {
    override fun draw() {
      LogUtils.e("Shape: invoke Square method")
    }
}