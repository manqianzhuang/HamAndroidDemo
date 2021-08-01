package com.superman.animate.design_mode.builder

import android.util.Log
import com.blankj.utilcode.util.LogUtils

class CreateCar {

    private var door = 4    //门
    private var shell = 1   //壳
    private var wheel = 4   //轴、轮
    private var color: String? = null   //油漆
    private var name: String? = null    //名字

    private var builder: Builder

    init {
        builder = Builder()
        this.door = builder.door
        this.shell = builder.shell
        this.wheel = builder.wheel
        this.color = builder.color
        this.name = builder.name
    }

   inner class Builder {

        var door: Int = -1    //门
        var shell = -1   //壳
        var wheel = -1   //轴、轮
        var color: String? = null   //油漆
        var name: String? = null    //名字

        init {
            door = 4
            shell = 1
            wheel = 4
            color = "红色"
            name = "凯美瑞"
        }

        fun build(): CreateCar {
            LogUtils.e("创建了一辆名为$name,$color 色的小汽车")
            return CreateCar()
        }
    }


}