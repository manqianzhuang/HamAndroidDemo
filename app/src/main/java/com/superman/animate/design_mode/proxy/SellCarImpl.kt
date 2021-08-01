package com.superman.animate.design_mode.proxy

import com.blankj.utilcode.util.LogUtils

class SellCarImpl : ISellCar{
    override fun sellPrice(): Int {
        LogUtils.e("我要卖汽车 10000元")
        return 10000
    }
}