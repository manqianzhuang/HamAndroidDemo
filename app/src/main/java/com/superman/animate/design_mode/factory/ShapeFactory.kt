package com.superman.animate.design_mode.factory

class ShapeFactory private constructor() {

    //volatile关键字锁定对象，当对象被某条线程重新赋值后，下一条线程访问此对象时，volatile会及时告知此对象已改变，
    @Volatile
    private lateinit var shape: Shape

    companion object {

        //饿汉式单例（线程安全）
        val instance = ShapeFactory()

        //懒汉式单例（线程非安全）
        //双重校验锁是为了防止多线程取到不同的对象（可能为null）
        val get: ShapeFactory by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ShapeFactory()
        }
    }

    fun getShape(shapeType: ShapeType): Shape {
        shape =  when(shapeType) {
            ShapeType.CIRCLE -> Circle()
            ShapeType.RECTANGLE-> Rectangle()
            ShapeType.SQUARE-> Square()
        }
        return shape
    }
}

enum class ShapeType {
    CIRCLE, RECTANGLE, SQUARE
}