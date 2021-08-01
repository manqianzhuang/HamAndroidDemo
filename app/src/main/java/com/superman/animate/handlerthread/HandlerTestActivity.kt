package com.superman.animate.handlerthread

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.superman.animate.R
import kotlinx.android.synthetic.main.activity_handler_test.*

class HandlerTestActivity: AppCompatActivity() {

    private var mHandler: Handler? = Handler(Looper.getMainLooper()) {
        tv_handler_test.text = "${it.obj as Int}"
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_test)
        val mHandleTestThread = HandlerThreadTest {
            val msg = Message.obtain()
            msg.obj = it
            mHandler?.sendMessage(msg)
        }
        mHandleTestThread.start()
    }


    override fun onDestroy() {
        super.onDestroy()
        mHandler = null
    }

}