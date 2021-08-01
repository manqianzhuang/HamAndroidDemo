package com.superman.animate.design_mode.chain_resposbility

//责任链模式
abstract class BaseLogger(LEVEL: Int) {

    private var nextLogger: BaseLogger? = null
    private var level: Int = -1

    companion object {
        const val debug = 0
        const val info = 1
        const val error = 2
    }

    init {
        this.level = LEVEL
    }

    abstract fun write(message: String)

    fun setNextChain(nextLogger: BaseLogger) {
        this.nextLogger = nextLogger
    }

    internal fun logMessage(level: Int, message: String) {
        if (level<=this.level) {
            write(message)
        } else {
            nextLogger?.logMessage(level, message)
        }
    }

}