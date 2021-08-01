package com.superman.animate.design_mode.chain_resposbility

import com.blankj.utilcode.util.LogUtils

class InfoLogger(var level: Int) : BaseLogger(level) {

    override fun write(message: String) {
        LogUtils.i("INFO Console::Logger: $message")
    }
}