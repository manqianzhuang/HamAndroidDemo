package com.superman.animate.design_mode.chain_resposbility

import com.blankj.utilcode.util.LogUtils

class DebugLogger(var level: Int) : BaseLogger(level) {

    override fun write(message: String) {
        LogUtils.d("DEBUG Console::Logger: $message")
    }
}