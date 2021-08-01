package com.superman.animate.handlerthread

import android.os.Handler
import android.os.HandlerThread
import com.blankj.utilcode.util.LogUtils

class HandlerThreadTest(val callback: (index: Int)-> Unit): Thread() {

    private var mHandler: Handler
    private var mHandlerThread: HandlerThread = HandlerThread("Handler-Thread-Test01")
    @Volatile var index = 0

    init {
        mHandlerThread.start()
        mHandler = Handler(mHandlerThread.looper)
    }

    override fun run() {
        while (index<5) {
            sleep(1000)
            index++
            LogUtils.e("当前线程：${currentThread().name}, 当前index=$index")
            mHandler.post {
                LogUtils.e("当前线程：${currentThread().name}, 当前index=$index")
                callback(index)
            }
        }
        mHandlerThread.quitSafely()

    }
}