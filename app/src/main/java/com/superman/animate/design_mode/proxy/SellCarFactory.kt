package com.superman.animate.design_mode.proxy

import com.blankj.utilcode.util.LogUtils
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy

class SellCarFactory {

    companion object {
        fun sell(iSellCar: ISellCar): Any {
            return Proxy.newProxyInstance(
                ISellCar::class.java.classLoader,
                arrayOf(ISellCar::class.java)
            ) { proxy, method, args ->
                LogUtils.w("售前检测")
                val origin = method?.invoke(iSellCar) as Int
                LogUtils.w("原价: $origin 售后收取中介费 1000元")
                val result = origin - 1000
                LogUtils.w("交易完成, $result")
                result
            }
        }
    }
}