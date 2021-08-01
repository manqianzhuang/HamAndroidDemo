package com.superman.animate.design_mode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import com.superman.animate.R
import com.superman.animate.design_mode.builder.CreateCar
import com.superman.animate.design_mode.chain_resposbility.BaseLogger
import com.superman.animate.design_mode.chain_resposbility.LoggerManager
import com.superman.animate.design_mode.decorator.Circle
import com.superman.animate.design_mode.decorator.Rectangle
import com.superman.animate.design_mode.decorator.RedShapeDecorator
import com.superman.animate.design_mode.factory.ShapeFactory
import com.superman.animate.design_mode.factory.ShapeType
import com.superman.animate.design_mode.proxy.ISellCar
import com.superman.animate.design_mode.proxy.SellCarFactory
import com.superman.animate.design_mode.proxy.SellCarImpl
import java.util.*
import kotlin.collections.HashMap

/**
 * 设计模式DEMO运行页面
 */
class DesignModeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        //动态代理模式
        val sellCarImpl = SellCarImpl() as ISellCar
        val factory = SellCarFactory.sell(sellCarImpl) as ISellCar
        LogUtils.e("车子卖了 ${factory.sellPrice()} 元")

        //建造者模式
        val carBuilder = CreateCar().Builder().apply {
            this.color = "白色"
            this.name = "奔驰"
            build()
        }

        //装饰者模式
        val circle = Circle()
        val redCircle = RedShapeDecorator(Circle())
        val redRectangle = RedShapeDecorator(Rectangle())
        circle.draw()
        redCircle.draw()
        redRectangle.draw()

        //工厂模式 && 线程安全双锁单例
        ShapeFactory.get.getShape(ShapeType.CIRCLE).draw()
        ShapeFactory.get.getShape(ShapeType.RECTANGLE).draw()
        ShapeFactory.get.getShape(ShapeType.SQUARE).draw()

        //责任链模式
        val chain = LoggerManager.getChainOfLogger()
        chain.logMessage(BaseLogger.debug, "我是一条debug日志")
        chain.logMessage(BaseLogger.info, "我是一条info日志")
        chain.logMessage(BaseLogger.error, "我是一条error日志")

    }
}