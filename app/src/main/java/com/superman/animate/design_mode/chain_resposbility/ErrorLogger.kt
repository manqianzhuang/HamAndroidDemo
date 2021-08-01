package com.superman.animate.design_mode.chain_resposbility

import com.blankj.utilcode.util.LogUtils

class ErrorLogger(var level: Int) : BaseLogger(level) {

    override fun write(message: String) {
        LogUtils.e("ERROR Console::Logger: $message")
    }
}